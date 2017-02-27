/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Occupant {
	final private String identifier;
	
	private boolean known = true;
	
	private boolean awake = true;
	
	private Room room;
	
	public enum OccupantType {
		ADULT, CHILD, ANIMAL;
	}
	
	final private OccupantType type;
	
    public Occupant(String identifier, OccupantType type) {
    	this.identifier = identifier;
    	this.type = type;
    }
    
    public String getIdentifier(){
    	return this.identifier;
    }
    
    public boolean getKnown(){
    	return known;
    }
    
    public boolean getAwake(){
    	return awake;
    }
    
    public OccupantType getType(){
    	return type;
    }
    
    public void setAwake(boolean awake){
    	this.awake = awake;
    }
    
    public void setKnown(boolean known){
    	this.known = known;
    }
    
    public Room getRoom(){
    	return room;
    }
    
    public void setRoom(Room room){
    	this.room = room;
    }
}
