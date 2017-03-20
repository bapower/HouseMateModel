/**
 * 
 */
package cscie97.asn3.housemate.model;

import cscie97.asn3.knowledge.engine.KnowledgeGraph;

/**
 * @author Bry Power
 *
 */
public class Occupant {
	final private String identifier;
	
	private String known = "true";
	
	private String awake = "true";
	
	public enum OccupantType {
		ADULT, CHILD, ANIMAL;
	}
	
	final private OccupantType type;
	
    public Occupant(String identifier, OccupantType type) {
    	KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
    	knowledgeGraph.importTriple(identifier, "awake", "true");
    	this.identifier = identifier;
    	this.type = type;
    }
    
    public String getIdentifier(){
    	return this.identifier;
    }
    
    public String getKnown(){
    	return known;
    }
    
    public String getAwake(){
    	return awake;
    }
    
    public OccupantType getType(){
    	return type;
    }
    
    public void setAwake(String awake){
    	this.awake = awake;
    }
    
    public void setKnown(String known){
    	this.known = known;
    }
    
}
