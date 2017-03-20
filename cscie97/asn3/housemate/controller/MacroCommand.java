/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Bry
 *
 */
public class MacroCommand implements Command {
	
	ArrayList<Command> commands;
	
	public MacroCommand (ArrayList<Command> commands) {
		this.commands = commands;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#execute()
	 */
	@Override
	public void execute() {
		Iterator<Command> entries = commands.iterator();
		while (entries.hasNext()) {
			try {
				entries.next().execute();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#store()
	 */
	@Override
	public void store() {
		// TODO Auto-generated method stub

	}
}
