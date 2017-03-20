/**
 * 
 */
package cscie97.asn3.housemate.controller;

import java.util.ArrayList;

/**
 * @author Bry Power
 *
 */
public class CommandFactory {
	
	public Command createCommand(String type, String identifier, String status, String value) {
		switch (type.toLowerCase()) {
	        case "set light":  
	        	SetLightCommand setLightCommand = new SetLightCommand(identifier, value);
	        	return setLightCommand;
	        case "set door":  
	        	SetDoorCommand setDoorCommand = new SetDoorCommand(identifier, value);
	        	return setDoorCommand;
	        case "set device":  
	        	SetDeviceCommand setDeviceCommand = new SetDeviceCommand(identifier, status, value);
	        	return setDeviceCommand;
	        case "set thermostat":
	        	SetThermostatCommand setThermostatCommand = new SetThermostatCommand(identifier, value);
	        	return setThermostatCommand;
	        case "show occupant":  
	        	ShowOccupantCommand showOccupantCommand = new ShowOccupantCommand(identifier, value);
	        	return showOccupantCommand;
	        case "ava alert":  
	        	SendAvaAlertCommand sendAvaAlertCommand = new SendAvaAlertCommand(identifier, value);
	        	return sendAvaAlertCommand;
	        case "phone call":
	        	PhoneCallCommand phoneCallCommand = new PhoneCallCommand(identifier, value);
	        	return phoneCallCommand;
	        case "set oven power":
	        	SetOvenPowerCommand setOvenPowerCommand= new SetOvenPowerCommand(identifier, value);
	        	return setOvenPowerCommand;
		}
		return null;
	}
	
	public Command createMacroCommand(ArrayList<Command> commands) {
		return new MacroCommand(commands);
	}

}
