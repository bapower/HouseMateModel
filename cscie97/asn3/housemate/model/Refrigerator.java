/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import java.util.Map.Entry;

import cscie97.asn3.housemate.controller.Command;
import cscie97.asn3.housemate.controller.CommandFactory;
import cscie97.asn3.housemate.controller.Controller;

/**
 * @author Bry Power
 *
 */
public class Refrigerator extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	private String temperature = "40";
	
	private String beerCount;
	
	CommandFactory commandFactory;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Refrigerator(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
		this.beerCount = "10";
		this.commandFactory = new CommandFactory();
		addObserver(Controller.getInstance());
	}
	
	public String getTemperature(){
    	return temperature;
    }
    
    public void setTemperature(String temperature){
    	this.temperature = temperature;
    }
    
    public String getPower(){
    	return power;
    }
    
    public void setPower(String power){
    	this.power = power;
    }
    
    public void setTakebeers(String beers){
    	int beersLeft = Integer.parseInt(beerCount) - Integer.parseInt(beers);
    	if(beersLeft >= 0) {
    		beerCount = Integer.toString(beersLeft);
    	} else {
    		beerCount = "0";
    	}
    	if (beersLeft < 4){
    		Scanner scanner = new Scanner(System.in);
    		System.out.print(String.format("%s:%s:%s says: there are only %s beers left. "
    				+ "Do you want to order more? Type yes or no: ", houseIdentifier, roomIdentifier, identifier, beerCount));
    		String answer = scanner.next();
    		scanner.close();
    		Command command;
    		if (answer.equals("yes")) {
    			ArrayList<String> phones = getDevices("Phone");
    			Iterator<String> entries = phones.iterator();
    			if(entries.hasNext()) {
    				String identifier = entries.next();
    				command = commandFactory.createCommand("phone call", identifier, "call", 
    					String.format("1-800-beer-store:%s is out low on beer. Please send more.", houseIdentifier));
    				
    				setChanged();
					notifyObservers(command);
    			} else {
    				System.out.println("There are no phones in the house to call for more beer");
    			}
    		} else {
    			System.out.println("No more beer will be ordered");
    		}
    	}
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
    	return "Power: " + power + ", Tempurature: " + temperature + ", Contents: " + beerCount.toString();
    }
}
