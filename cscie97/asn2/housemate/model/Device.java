/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Device {
	final protected String identifier;
	
	protected String power = "ON";
	
	enum DeviceType {
		SENSOR, APPLIANCE;
	}
	
	protected DeviceType type;
	
    public Device(String identifier) {
    	this.identifier = identifier;;
    }
    
    public String getIdentifier(){
    	return this.identifier;
    }
    
    public String getPower(){
    	return power;
    }
    
    public void setPower(String power){
    	this.power = power;
    }
    
    public DeviceType getType(){
    	return type;
    }
    
    public String getConfiguration () {
    	return "Power: " + power;
    }
}
