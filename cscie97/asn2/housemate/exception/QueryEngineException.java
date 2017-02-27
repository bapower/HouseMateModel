package cscie97.asn2.housemate.exception;

public class QueryEngineException extends Exception {
	/**
	 * 
	 * Exception on error, malformed queries or problems accessing the query input file.
	 * 
	 */

	// the query that resulted in an error
	private String query;
	// cause of the error
	private String message;
	
	/**
	 * 
	 * @param query
	 * @param message
	 */
	public QueryEngineException (String query, String message) {
		this.query = query;
		this.message = message;
	}
	
	/**
	 * get the query that resulted in this exception
	 * @return query
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * get the message describing why this exception occurred
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set the query associated with this exception
	 * @param query
	 */
	public void setContents(String query) {
		this.query = query;
	}
	
	/**
	 * Set the message describing why this exception occurred
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
