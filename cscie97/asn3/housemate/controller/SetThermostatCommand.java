/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import cscie97.asn3.housemate.model.ModelAPI;

/**
 * @author Bry
 *
 */
public class SetThermostatCommand implements Command {
	
	private String identifier;
	
	private String value;
	
	ModelAPI API = ModelAPI.getInstance();
	
	public SetThermostatCommand (String identifier, String value) {
		this.identifier = identifier;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#execute()
	 */
	@Override
	public void execute() {
		try {
			store();
			API.setDevice(identifier, "temperature", value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.out.println(e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#store()
	 */
	@Override
	public void store() {
		try(
			FileOutputStream fos = new FileOutputStream("command_log.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		) {
			oos.writeObject(this);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}
