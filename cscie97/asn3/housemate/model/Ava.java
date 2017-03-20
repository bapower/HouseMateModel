/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;
import cscie97.asn3.housemate.controller.Command;
import cscie97.asn3.housemate.controller.CommandFactory;
import cscie97.asn3.housemate.controller.Controller;

/**
 * @author Bry Power
 *
 */
public class Ava extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	CommandFactory commandFactory;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Ava(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
		this.commandFactory = new CommandFactory();
		addObserver(Controller.getInstance());
	}
	
	public void processVoiceCommand(String voiceCommand) {
		String[] tokens;
		tokens = voiceCommand.toLowerCase().split("_");
		Command command;
		if (tokens[0].trim().equals("lights")) {
			ArrayList<String> lights = getDevices("Light");
			Iterator<String> entries = lights.iterator();
			while (entries.hasNext()) {
				String identifier = entries.next();
				if (tokens[1].trim().equals("on")) {
					command = commandFactory.createCommand("set light", identifier, "on", "true");
					setChanged();
					notifyObservers(command);
				} else if (tokens[1].trim().equals("off")) {
					command = commandFactory.createCommand("set light", identifier, "on", "false");
					setChanged();
					notifyObservers(command);
				} else {
					setAlert("I did not understant what you said.");
				}
			}		
		} else if (tokens[1].trim().equals("door")) {
			ArrayList<String> doors = getDevices("Door");
			Iterator<String> entries = doors.iterator();
			while (entries.hasNext()) {
				String identifier = entries.next();
				if (tokens[0].trim().equals("open")) {
					command = commandFactory.createCommand("set door", identifier, "open", "true");
					setChanged();
					notifyObservers(command);
				} else if (tokens[0].trim().equals("close")) {
					command = commandFactory.createCommand("set door", identifier, "open", "false");
					setChanged();
					notifyObservers(command);
				} else {
					setAlert("I did not understant what you said.");
				}
			}		
		} else if(tokens[0].trim().equals("set")) {
			ArrayList<String> devices = getDevices(tokens[1]);
			Iterator<String> entries = devices.iterator();
			while (entries.hasNext()) {
				String identifier = entries.next();
					command = commandFactory.createCommand("set device", identifier, tokens[2], tokens[3]);
					setChanged();
					notifyObservers(command);
			}		
		} else if(tokens[0].trim().equals("where")) {
			command = commandFactory.createCommand("show occupant", tokens[2], "location", 
					String.format("%s:%s:%s", houseIdentifier, roomIdentifier, identifier));
			setChanged();
			notifyObservers(command);
		}
		
		else {
			setAlert("I did not understant what you said.");
		}
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
		if (deviceIdentifiers.isEmpty()) {
			setAlert("There isn't a " + type + " in this room");
		} 
		return deviceIdentifiers;
	}
	
	public void setVoiceinput (String value) {
		processVoiceCommand(value);
	}
	
	public void setAlert (String message) {
		System.out.println(String.format("%s:%s:%s", houseIdentifier, roomIdentifier, identifier) 
				+ " says: " + message);
	}
	
	public String getIdentifier(){
    	return this.identifier;
    }
    
    public String getPower(){
    	return power;
    }
    
    public void setPower(String power){
    	this.power = power;
    }
    
    public DeviceType getType(){
    	return type;
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
