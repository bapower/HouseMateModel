/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry
 *
 */
public class Window implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected DeviceType type;
	
	private boolean open = true;
	
	private boolean blindsOpen = true;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Window(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
	
	public boolean getOpen(){
    	return open;
    }
    
    public void setOpen(boolean open){
    	this.open = open;
    }
    
    public boolean getBlindsOpen(){
    	return blindsOpen;
    }
    
    public void setBlindsOpen(boolean blindsOpen){
    	this.blindsOpen = blindsOpen;
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
    	return "Open: " + open + ", Blinds open: " + blindsOpen;
    }
}
