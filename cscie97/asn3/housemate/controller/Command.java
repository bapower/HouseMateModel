/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Bry Power
 *
 */
public interface Command {
	
	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void store();
}
