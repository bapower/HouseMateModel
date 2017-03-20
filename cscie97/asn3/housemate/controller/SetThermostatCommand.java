/**
 * 
 */
package cscie97.asn3.housemate.controller;

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#load()
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
