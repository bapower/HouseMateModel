/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Map.Entry;

import cscie97.asn3.housemate.controller.Command;
import cscie97.asn3.housemate.controller.CommandFactory;
import cscie97.asn3.housemate.controller.Controller;

/**
 * @author Bry Power
 *
 */
public class Oven extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	private String temperature = "350";
	
	private String timer = "45";
	
	CommandFactory commandFactory;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Oven(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
		this.commandFactory = new CommandFactory();
		addObserver(Controller.getInstance());
	}
	
	public String getTemperature(){
    	return temperature;
    }
    
    public void setTemperature(String temperature){
    	this.temperature = temperature;
    }
    
    public String getTimer(){
    	return timer;
    }
    
    public void setTimer(String timer){
    	this.timer = timer;
    	if (timer.equals("0")) {
    		ArrayList<Command> commands = new ArrayList<Command>();
			commands.add(commandFactory.createCommand("set oven power",
					String.format("%s:%s:%s", houseIdentifier, roomIdentifier, identifier), "power", "off"));
			ArrayList<String> avas = getDevices("Ava");
			Iterator<String> entries = avas.iterator();
			while (entries.hasNext()) {
				String identifier = entries.next();
				commands.add(commandFactory.createCommand("ava alert", identifier, "alert", 
					"The food is done cooking in the oven"));
			}
			Command macroCommand = commandFactory.createMacroCommand(commands);
			setChanged();
			notifyObservers(macroCommand);
    	}
    }
    
    private ArrayList<String> getDevices(String type) {	
		type = type.substring(0, 1).toUpperCase() + type.substring(1);
		ArrayList<String> deviceIdentifiers = new ArrayList<String>();
		House house = ModelAPI.houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);

		Iterator<Entry<String, Device>> entries = room.deviceMap.entrySet().iterator();
		if(entries.hasNext()){
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
		} else {
			System.out.println("There are no Avas in this room");
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
    	return "Power: " + power + ", Tempurature: " + temperature + ", Timer: " + timer;
    }
}
