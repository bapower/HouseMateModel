/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import cscie97.asn3.housemate.model.ModelAPI;
import cscie97.asn3.knowledge.engine.KnowledgeGraph;
import cscie97.asn3.knowledge.engine.Triple;

/**
 * @author Bry
 *
 */
public class ShowOccupantCommand implements Command {

	private String identifier;
	
	KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
	
	ModelAPI API = ModelAPI.getInstance();
	
	String avaIdentifier;
	
	public ShowOccupantCommand (String identifier, String avaIdentifier) {
		this.identifier = identifier;
		this.avaIdentifier = avaIdentifier;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.housemate.controller.Command#execute()
	 */
	@Override
	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		store();
		Set<Triple> triples = knowledgeGraph.executeQuery(identifier, "in", "?");
		if(triples.size() > 0) {
			Iterator<Triple> entries = triples.iterator();
			while (entries.hasNext()) {
				API.setDevice(avaIdentifier, "alert", entries.next().getIdentifier());
			}	
		} else {
			API.setDevice(avaIdentifier, "alert", identifier + " was not found");
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
