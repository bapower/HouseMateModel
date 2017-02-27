/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class SmokeDetector extends Device {
	
	private boolean alarmOn = false;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public SmokeDetector(String identifier) {	
		super(identifier);
		super.type = DeviceType.SENSOR;
	}
	
	public boolean getAlarmOn () {
		return alarmOn;
	}

	public void setAlarmOn (boolean alarmOn) {
		this.alarmOn = alarmOn;
	}
	
	public String getConfiguration () {
    	return "Power: " + power + ", Alarm on: " + alarmOn;
    }
}
