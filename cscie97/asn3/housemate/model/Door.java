/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Door implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected DeviceType type;
	
	private String open = "true";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Door(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
	
	public String getOpen(){
    	return open;
    }
    
    public void setOpen(String open){
    	this.open = open;
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
    	return "Open: " + open;
    }
}
