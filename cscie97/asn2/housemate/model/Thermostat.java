/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Thermostat extends Device {
	private String temperature = "70";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Thermostat(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public String getTemerature(){
    	return temperature;
    }
    
    public void setTemperature(String temperature){
    	this.temperature = temperature;
    }
  
    public String getConfiguration () {
    	return "Power: " + power + ", Tempurature: " + temperature;
    }
}
