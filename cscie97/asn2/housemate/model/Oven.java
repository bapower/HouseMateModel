/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Oven extends Device {
	private String temperature = "350";
	private String timer = "45";
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Oven(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public String getTemperature(){
    	return temperature;
    }
    
    public void setTemperature(String temperature){
    	this.temperature = temperature;
    }
    
    public String getTimer(){
    	return timer;
    }
    
    public void setTimer(String timer){
    	this.timer = timer;
    }
    
    public String getConfiguration () {
    	return "Power: " + power + ", Tempurature: " + temperature + ", Timer: " + timer;
    }
}
