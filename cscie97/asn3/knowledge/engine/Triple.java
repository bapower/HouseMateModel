/**
 * 
 */
package cscie97.asn3.knowledge.engine;

import java.time.Instant;

/**
 * @author Bry
 *
 */
public class Triple {

	final private String identifier; 	
	final private String subject;
	final private String predicate;
	final private String object;
	final private long createDate; 
	
	public Triple(String identifier) {
		 if(identifier == null){
			 throw new IllegalArgumentException("Invalid Triple.");
		 }
		 
		 this.identifier = identifier;
		 String[] tokens;
		 tokens = identifier.split("\\s+");
		 subject = tokens[0];
		 predicate = tokens[1];
		 object = tokens[2];
		 this.createDate = Instant.now().getEpochSecond(); 
	}
	
	/**
	 * Method to return the Triple identifier.
	 * @return String
	 */
	public String getSubject(){
		return this.subject;
	}
	
	/**
	 * Method to return the Triple identifier.
	 * @return String
	 */
	public String getPredicate(){
		return this.predicate;
	}
	
	/**
	 * Method to return the Triple identifier.
	 * @return String
	 */
	public String getObject(){
		return this.object;
	}
	
	/**
	 * Method to return the Triple identifier.
	 * @return String
	 */
	public String getIdentifier(){
		return this.identifier;
	}
	
	/**
	 * Method to return the Triple createDate.
	 * @return long
	 */
	public long getCreateDate(){
		return this.createDate;
	}
}
