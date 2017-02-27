/**
 * 
 */
package cscie97.asn2.housemate.model;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Bry Power
 *
 */
public class Ava extends Device {
	/**
	 * @param identifier
	 * @param type
	 */
	public Ava(String identifier) {	
		super(identifier);
		super.type = DeviceType.SENSOR;
	}
	
	@SuppressWarnings("unused")
	private void processCommand (String command) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		CLI.processCommand(command);
	}
}
