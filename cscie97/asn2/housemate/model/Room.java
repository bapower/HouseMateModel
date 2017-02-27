/**
 * 
 */
package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Bry Power
 *
 */
public class Room {
	
	
	final private String identifier;
	
	final private int floor;
	
	public enum RoomType {
		KITCHEN, DININGROOM, BEDROOM, BATHROOM, CLOSET, LIVINGROOM, OFFICE;
	}
	
	private RoomType type;
	
	private Set<Occupant> occupants;
	
	// association for maintaining the active set of Devices
	public Map<String, Device> deviceMap = new HashMap<String, Device>();
		
    public Room(String identifier, int floor, RoomType type) {
    	this.identifier = identifier;
    	this.floor = floor;
    	this.type = type;
    }
    
    public String getIdentifier(){
    	return this.identifier;
    }
    
    public int getFloor(){
    	return floor;
    }
    
    public RoomType getType(){
    	return type;
    }
    
    public void setType(RoomType type){
    	this.type = type;
    }
    
    public void addOccupant (Occupant occupant) {
    	occupants.add(occupant);
    }
    
    public void removeOccupant (Occupant occupant) {
    	occupants.remove(occupant);
    }
    
    public String getConfiguration () {
    	return "Floor: " + floor + ", Room type: " + type + ", Devices: " + deviceMap.toString();
    }
}
