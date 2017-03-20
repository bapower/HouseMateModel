package cscie97.asn3.housemate.test;

import cscie97.asn3.housemate.model.CLI;


/**
 * 
 * Class used for testing the program.
 *
 */

public class TestDriver {
	public static void main(String args[])  {
		String inputFile = args[0];
    	try {
    		CLI.importFile(inputFile);
        } 
    	catch (Exception exception) {
    		System.out.println(exception.getMessage());
        }
    	
	}
}
