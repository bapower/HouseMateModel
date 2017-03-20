/**
 * 
 */
package cscie97.asn3.housemate.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cscie97.asn3.housemate.exception.ImportException;

/**
 * @author Bry Power
 *
 */
public class CLI {
	public static void importFile(String fileName) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ImportException {
    	File file = new File(fileName);
    	if(!file.exists()){
    		ImportException importException = new ImportException(0, "", "Input file "+fileName+" does not exist");
			throw importException;

    	} else {
	    	Pattern extensionPattern = Pattern.compile("([^\\s]+(\\.(?i)(txt))$)");
	    	Matcher match = extensionPattern.matcher(fileName);
	            if(!match.matches()){
	            	ImportException importException = new ImportException(0, "", "Input file "+fileName+" does not exist");
	    			throw importException;
	            }
    	}
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	inputLine = inputLine.trim();
	            if (inputLine.isEmpty() || inputLine.contains("#")) {
	                continue;
	            }
	            processCommand(inputLine);
            }
        }
        catch (FileNotFoundException exception) {
        	System.out.println(exception.getMessage());
        }
        catch (IOException exception) {
        	System.out.println(exception.getMessage());
        }
    	
	}
	
	public static void processCommand (String command) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		String[] tokens;
		tokens = command.split("\\s+");
		ModelAPI modelGraph = ModelAPI.getInstance();

		if (tokens[0].equals("define") && tokens[1].equals("house")) {
			modelGraph.defineHouse(tokens[2]);
		}
		else if (tokens[0].equals("define") && tokens[1].equals("occupant")) {
			modelGraph.defineOccupant(tokens[2],tokens[4]);
		}
	
		else if (tokens[0].equals("add") && tokens[1].equals("occupant") && tokens[3].equals("to_house")) {
			modelGraph.addOccupant(tokens[2], tokens[4]);
		}
		else if (tokens[0].equals("define") && tokens[1].equals("room")) {

			if (tokens.length == 9 && tokens[3].equals("floor") && tokens[5].equals("type") && tokens[7].equals("house")){
				modelGraph.defineRoom(tokens[2],tokens[4],tokens[6],tokens[8]);
			}
		}
		else if (tokens[0].equals("define") && tokens[1].equals("sensor") || tokens[0].equals("define") && tokens[1].equals("appliance")){
			modelGraph.defineDevice(tokens[2],tokens[4],tokens[6]);
		}
		
		else if (tokens[0].equals("set") && (tokens[1].equals("appliance") && tokens.length > 6)) {
			modelGraph.setDevice(tokens[2],tokens[4],tokens[6]);
		}
		else if (tokens[0].equals("show")) {
			if(tokens.length == 3){
				modelGraph.showDevice(tokens[2]);
			}	
			else if (tokens[1].equals("configuration") && tokens.length == 2l){
				modelGraph.showConfiguration();
			}
			else if (tokens[1].equals("configuration") && tokens[2].equals("house")){
				modelGraph.showHouseConfiguration(tokens[3]);
			}
			else if (tokens[1].equals("configuration") && tokens[2].equals("room")){
				modelGraph.showRoomConfiguration(tokens[3]);
			}
		}
			
	}
	
}
