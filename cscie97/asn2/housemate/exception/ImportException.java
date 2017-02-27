package cscie97.asn2.housemate.exception;

public class ImportException extends Exception {
	/**
	 * 
	 * Exception on error accessing or processing an input Triple File.
	 * 
	 */
	
	//the line number of generating an error
	private int lineNumber;
	//the contents of the line
	private String contents;
	//cause of the error
	private String message;
	
	public ImportException (int lineNumber, String contents, String message) {
		this.lineNumber = lineNumber;
		this.contents = contents;
		this.message = message;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public String getContents() {
		return contents;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
