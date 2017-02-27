/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Thermostat extends Device {
	private String temp = "70";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Thermostat(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public String getTemp(){
    	return temp;
    }
    
    public void setTemp(String temp){
    	this.temp = temp;
    }
  
    public String getConfiguration () {
    	return "Power: " + power + ", Tempurature: " + temp;
    }
}
