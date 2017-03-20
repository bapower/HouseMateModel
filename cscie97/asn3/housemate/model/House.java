/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Bry Power
 *
 */

public class House {
	
	final private String identifier;
	
	private String numFloors = "1";
	
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
    
    public String getNumFloors(){
    	return numFloors;
    }
    
    public void setNumFloors(String numFloors){
    	this.numFloors= numFloors;
    }

    public String getConfiguration () {
    	Set<String> rooms = roomMap.keySet();
    	String roomString = "";
    	for (String room : rooms) {
    		  roomString +=  room + ", ";
    	}
    	
    	Set<String> occupants = occupantMap.keySet();
    	String occupantString = "";
    	for (String occupant : occupants) {
    		  occupantString += occupant + ", ";
    	} 
    	
    	return "Floors: " + numFloors + ", Rooms: " + roomString + ", Occupants: " + occupantString;
    }
}
