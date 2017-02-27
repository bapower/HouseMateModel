/**
 * 
 */
package cscie97.asn2.housemate.model;

import java.util.List;

/**
 * @author Bry Power
 *
 */
public class Refrigerator extends Device {
	private String temp = "40";
	
	private List<String> contents;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Refrigerator(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public String getTemp(){
    	return temp;
    }
    
    public void setTemp(String temp){
    	this.temp = temp;
    }
    
    public void addContents (String item) {
    	contents.add(item);
    }
    
    public void removeContents (String item) {
    	contents.remove(item);
    }
    
    public String getConfiguration () {
    	return "Power: " + power + ", Tempurature: " + temp + ", Contents: " + contents.toString();
    }
}
