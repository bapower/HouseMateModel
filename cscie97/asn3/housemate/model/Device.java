/**
 * 
 */
package cscie97.asn3.housemate.model;

/**
 * @author Bry Power
 *
 */
public interface Device {
	
	enum DeviceType {
		SENSOR, APPLIANCE;
	}
	
	public String getConfiguration();
	
	public String getIdentifier();
	
	public String getHouse();
	
	public String getRoom();
}
