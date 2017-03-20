/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.util.Observable;

/**
 * @author Bry Power
 *
 */
public class Light extends Observable implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected DeviceType type;
	
	private String on = "true";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Light(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
	
	public String getOn(){
    	return on;
    }
    
    public void setOn(String on){
    	this.on = on;
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
    	return "On: " + on;
    }

}
