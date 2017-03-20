/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry
 *
 */
public class Pandora implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	private String volume = "1";
	
	private String channel = "1";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Pandora(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
	
	public String getVolume(){
    	return volume;
    }
	
	public String getChannel(){
    	return channel;
    }
    
    public void setVolume(String volume){
    	this.volume = volume;
    }
    
    public void setChannel(String channel){
    	this.channel = channel;
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
    	return "Power: " + power + ", Volume: " + volume + ", Channel: " + channel;
    }

}
