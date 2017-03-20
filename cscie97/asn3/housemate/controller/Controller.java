/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Bry
 *
 */
public class Controller implements Observer {
	
	// singleton instance of this class
	private static Controller instance = null;
	
	
	/**
	 * This method returns a reference to the single static 
	 * instance of the Controller
	 * @return Controller instance
	 */
	protected Controller() {
	      // instantiation is protected
	}
	
	/**
	 *  instantiate or get the singleton instance of this class or 
	 * @return Controller instance
	 */
	public static Controller getInstance() {
	      if(instance == null) {
	         instance = new Controller();
	      }
	      return instance;
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Command) {
			try {
				((Command) arg).execute();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException();
		}

	}

} 
