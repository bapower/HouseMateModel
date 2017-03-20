/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.ArrayList;
import java.util.Iterator;
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
public class Camera extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	CommandFactory commandFactory;
	
	KnowledgeGraph knowledgeGraph;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Camera(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.SENSOR;
		this.commandFactory = new CommandFactory();
		this.knowledgeGraph = KnowledgeGraph.getInstance();
		addObserver(Controller.getInstance());
	}
	
	public void setStatechange(String input) {
		String[] tokens;	
		tokens = input.toLowerCase().split("_");
		if (tokens[0].trim().equals("location")) {
			setLocationChange(tokens[1], tokens[2]);
		} else if (tokens[0].equals("awake")) {
			setAwakeChange(tokens[1], tokens[2]); 
		}
	}
	
	public void setLocationChange(String occupant, String state) {
		System.out.println(String.format("%s %s %s:%s", occupant, state, houseIdentifier, roomIdentifier));
		ArrayList<Command> commands = new ArrayList<Command>();
		if (state.equals("entered")){
			knowledgeGraph.importTriple(occupant, "in", String.format("%s:%s", houseIdentifier, roomIdentifier));
			
			ArrayList<String> lights = getDevices("Light");
			Iterator<String> lightEntries = lights.iterator();
			while (lightEntries.hasNext()) {
				String identifier = lightEntries.next();
				commands.add(commandFactory.createCommand("set light", identifier, "on", "true"));
			}
			
			ArrayList<String> thermostats = getDevices("Thermostat");
			Iterator<String> thermostatEntries = thermostats.iterator();
			while (thermostatEntries.hasNext()) {
				String identifier = thermostatEntries.next();
				commands.add(commandFactory.createCommand("set thermostat", identifier, "temperature", "72"));
			}
			Command macroCommand = commandFactory.createMacroCommand(commands);
			setChanged();
			notifyObservers(macroCommand);
		} else if (state.equals("exited")) {
			knowledgeGraph.removeTriple(occupant, "in", String.format("%s:%s", houseIdentifier, roomIdentifier));
			Set<Triple> occupants = knowledgeGraph.executeQuery("?", "in", String.format("%s:%s", houseIdentifier, roomIdentifier));
			int occupantsLeft = occupants.size();
			if (occupantsLeft < 1) {
				ArrayList<String> lights = getDevices("Light");
				Iterator<String> lightEntries = lights.iterator();
				while (lightEntries.hasNext()) {
					String identifier = lightEntries.next();
					commands.add(commandFactory.createCommand("set light", identifier, "on", "false"));
				}
				ArrayList<String> thermostats = getDevices("Thermostat");
				Iterator<String> thermostatEntries = thermostats.iterator();
				while (thermostatEntries.hasNext()) {
					String identifier = thermostatEntries.next();
					commands.add(commandFactory.createCommand("set thermostat", identifier, "temperature", "65"));
				}
				Command macroCommand = commandFactory.createMacroCommand(commands);
				setChanged();
				notifyObservers(macroCommand);
			} else {
				System.out.println("There are still other occupants in the room who need light and heat");
			}
		}
	}
	
	public void setAwakeChange(String occupant, String state) {
		System.out.println(String.format("%s awake %s in %s:%s", occupant, state, houseIdentifier, roomIdentifier));
		knowledgeGraph.importTriple(occupant, "awake", state);
		Command command;
		if(state.equals("false")) {
			Set<Triple> occupants = knowledgeGraph.executeQuery("?", "in", String.format("%s:%s", houseIdentifier, roomIdentifier));
			int occupantsInRoom = occupants.size();
			if (occupantsInRoom < 2) {
				ArrayList<String> lights = getDevices("Light");
				Iterator<String> lightEntries = lights.iterator();
				while (lightEntries.hasNext()) {
					String identifier = lightEntries.next();
					command = commandFactory.createCommand("set light", identifier, "on", "false");
					setChanged();
					notifyObservers(command);
				}
			} else {
				Iterator<Triple> entries = occupants.iterator();
				boolean otherOccupantsAwake = false;
				while (entries.hasNext()) {
					Set<Triple> occupantsAwakeInRoom = knowledgeGraph.executeQuery(entries.next().getSubject(), "awake", "true");
					if(occupantsAwakeInRoom.size() > 0) {
						otherOccupantsAwake = true;
					}
				}
				if(!otherOccupantsAwake) {
					ArrayList<String> lights = getDevices("Light");
					Iterator<String> lightEntries = lights.iterator();
					while (lightEntries.hasNext()) {
						String identifier = lightEntries.next();
						command = commandFactory.createCommand("set light", identifier, "on", "false");
						setChanged();
						notifyObservers(command);
					}
				} else {
					System.out.println("Other people are awake in the room and need light");
				}
			}
		} else if(state.equals("true")) {
			ArrayList<String> lights = getDevices("Light");
			Iterator<String> lightEntries = lights.iterator();
			while (lightEntries.hasNext()) {
				String identifier = lightEntries.next();
				command = commandFactory.createCommand("set light", identifier, "on", "true");
				setChanged();
				notifyObservers(command);
			}
		}
		
	}

	public void processStateChange(Occupant occupant, String awake) {
		occupant.setAwake(awake);
	}
	
	private ArrayList<String> getDevices(String type) {	
		type = type.substring(0, 1).toUpperCase() + type.substring(1);
		ArrayList<String> deviceIdentifiers = new ArrayList<String>();
		House house = ModelAPI.houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);

		Iterator<Entry<String, Device>> entries = room.deviceMap.entrySet().iterator();
		while (entries.hasNext()) {
			Device device = entries.next().getValue();
			if (device.getClass().getSimpleName().equals(type)) {
				String identifier = device.getIdentifier();
				String houseIdentifier = device.getHouse();
				String roomIdentifier = device.getRoom();
				String deviceIdentifier = String.format("%s:%s:%s", houseIdentifier, roomIdentifier, identifier);
				deviceIdentifiers.add(deviceIdentifier);
			}
		}
		return deviceIdentifiers;
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
    	return "Power: " + power;
	 }
}
