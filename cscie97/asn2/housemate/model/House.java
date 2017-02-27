/**
 * 
 */
package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bry Power
 *
 */

public class House {
	
	final private String identifier;
	
	private int numFloors = 1;
	
	// association for maintaining the active set of Rooms
	public Map<String, Room> roomMap = new HashMap<String, Room>();
	
	// association for maintaining the active set of Occupants
	public Map<String, Occupant> occupantMap = new HashMap<String, Occupant>();
	
    public House(String identifier) {
    	this.identifier = identifier;
    }
    
    public String getIdentifier(){
    	return this.identifier;
    }
    
    public int getNumFloors(){
    	return numFloors;
    }

    public String getConfiguration () {
    	return "Floors: " + numFloors + ", Rooms: " + roomMap.toString() + ", Occupants: " + occupantMap.toString();
    }
}
