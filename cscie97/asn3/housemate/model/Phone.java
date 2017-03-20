/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry
 *
 */
public class Phone implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected DeviceType type;
	
	protected String power = "ON";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Phone(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
    
    public void setCall(String callInfo){
    	String[] tokens;
		tokens = callInfo.toLowerCase().split(":");
		System.out.println(String.format("calling %s with message: %s", tokens[0], tokens[1]));
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
