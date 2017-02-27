package cscie97.asn2.housemate.test;

import cscie97.asn2.housemate.model.CLI;


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
    		System.out.println("The housesetup.txt file not found.");
        }
	}
}
