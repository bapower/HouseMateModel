/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.Map.Entry;

import cscie97.asn3.housemate.controller.Command;
import cscie97.asn3.housemate.controller.CommandFactory;
import cscie97.asn3.housemate.controller.Controller;
import cscie97.asn3.knowledge.engine.KnowledgeGraph;
import cscie97.asn3.knowledge.engine.Triple;

/**
 * @author Bry Power
 *
 */
public class SmokeDetector extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	private String alarm = "OFF";
	
	KnowledgeGraph knowledgeGraph;
	
	CommandFactory commandFactory;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public SmokeDetector(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
		this.commandFactory = new CommandFactory();
		this.knowledgeGraph = KnowledgeGraph.getInstance();
		addObserver(Controller.getInstance());
	}
	
	public void setFiremode(String value) {
		System.out.println(String.format("set fire mode to " + value));
		ArrayList<Command> commands = new ArrayList<Command>();
		if(value.equals("on")) {
			if(occupantsInHouse()) {
				commands.addAll(turnOnLights());
				commands.addAll(sendAvaAlerts());
			}
			commands.addAll(call911());
		}
		Command macroCommand = commandFactory.createMacroCommand(commands);
		setChanged();
		notifyObservers(macroCommand);
	}
	
	private ArrayList<Command> turnOnLights() {
		ArrayList<String> lights = getDevices("Light");
		Iterator<String> entries = lights.iterator();
		ArrayList<Command> commands = new ArrayList<Command>();
		while (entries.hasNext()) {
			String identifier = entries.next();
			commands.add(commandFactory.createCommand("set light", identifier, "on", "true"));
		}
		return commands;
	}
	
	private ArrayList<Command> sendAvaAlerts() {
		ArrayList<String> avas = getDevices("Ava");
		Iterator<String> entries = avas.iterator();
		ArrayList<Command> commands = new ArrayList<Command>();
		while (entries.hasNext()) {
			String identifier = entries.next();
			int floor = getFloor(identifier);
			if(floor == 1) {
				commands.add(commandFactory.createCommand("ava alert", identifier, "alert", 
					"There is a fire. Leave the House. Exit through the window."));
			} else {
				commands.add(commandFactory.createCommand("ava alert", identifier, "alert", 
						"There is a fire. Leave the House."));
			}
		}
		return commands;
	}
	
	private ArrayList<Command> call911() {
		ArrayList<String> phones = getDevices("Phone");
		Iterator<String> entries = phones.iterator();
		ArrayList<Command> commands = new ArrayList<Command>();
		if(entries.hasNext()) {
			String identifier = entries.next();
			commands.add(commandFactory.createCommand("phone call", identifier, "call", 
					String.format("911:Help! There is a fire at %s", houseIdentifier)));
		} else {
			System.out.println("There is no phone in the house to call 911 with.");
		}
		return commands;
	}
	
	private ArrayList<String> getDevices(String type) {	
		type = type.substring(0, 1).toUpperCase() + type.substring(1);
		ArrayList<String> deviceIdentifiers = new ArrayList<String>();
		House house = ModelAPI.houseMap.get(houseIdentifier);
		Map<String, Room> rooms = house.roomMap;
		
		Iterator<Entry<String, Room>> roomEntries = rooms.entrySet().iterator();
		while (roomEntries.hasNext()) {
			Room room = roomEntries.next().getValue();
			Map<String, Device> devices = room.deviceMap;
			Iterator<Entry<String, Device>> deviceEntries = devices.entrySet().iterator();
			while (deviceEntries.hasNext()) {
				Device device = deviceEntries.next().getValue();
				if (device.getClass().getSimpleName().equals(type)) {
					String identifier = device.getIdentifier();
					String houseIdentifier = device.getHouse();
					String roomIdentifier = device.getRoom();
					String deviceIdentifier = String.format("%s:%s:%s", houseIdentifier, roomIdentifier, identifier);
					deviceIdentifiers.add(deviceIdentifier);
				}
			}
		}
		return deviceIdentifiers;
	}
	
	private int getFloor(String identifier) {
		String[] tokens;
		tokens = identifier.toLowerCase().split(":");
		House house = ModelAPI.houseMap.get(tokens[0]);
		Room room = house.roomMap.get(tokens[1]);
		return room.getFloor();
	}
	
	private boolean occupantsInHouse() {
		House house = ModelAPI.houseMap.get(houseIdentifier);
		Map<String, Room> rooms = house.roomMap;
		boolean occupantsInHouse = false;
		
		Iterator<Entry<String, Room>> roomEntries = rooms.entrySet().iterator();
		while (roomEntries.hasNext()) {
			Room room = roomEntries.next().getValue();
			Set<Triple> occupants = knowledgeGraph.executeQuery("?", "in", String.format("%s:%s", houseIdentifier, room.getIdentifier()));
			if (occupants.size() > 0) {
				occupantsInHouse = true;
			}
		}
		return occupantsInHouse;
	}
	
	public String getAlarm () {
		return alarm;
	}

	public void setAlarmOn (String alarm) {
		this.alarm = alarm;
	}
	
    public String getPower(){
    	return power;
    }
    
    public void setPower(String power){
    	this.power = power;
    }
	
	public String getIdentifier(){
    	return this.identifier;
    }
	
    public String getHouse(){
    	return houseIdentifier;
    }
    
    public String getRoom(){
    	return roomIdentifier;
    }
	
	public String getConfiguration () {
    	return "Power: " + power + ", Alarm on: " + alarm;
    }
}
