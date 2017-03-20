/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Thermostat implements Device {
	
	final protected String identifier;
	
	final protected String roomIdentifier;
	
	final protected String houseIdentifier;
	
	protected String power = "ON";
	
	protected DeviceType type;
	
	private String temperature = "70";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Thermostat(String identifier, String room, String house) {	
		this.identifier = identifier;
		this.roomIdentifier = room;
		this.houseIdentifier = house;
		this.type = DeviceType.APPLIANCE;
	}
	
	public String getTemerature(){
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
    	return "Power: " + power + ", Tempurature: " + temperature;
    }
}
