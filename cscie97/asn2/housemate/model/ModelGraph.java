/**
 * 
 */
package cscie97.asn2.housemate.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cscie97.asn2.housemate.model.Media.MediaType;
import cscie97.asn2.housemate.model.Occupant.OccupantType;
import cscie97.asn2.housemate.model.Room.RoomType;

/**
 * @author Bry Power
 *
 */
public class ModelGraph {

	// association for maintaining the active set of Houses 
	private static Map<String, House> houseMap = new HashMap<String, House>();
	
	// association for maintaining the active set of Occupants
	private static Map<String, Occupant> occupantMap = new HashMap<String, Occupant>();
	
	// singleton instance of this class
	private static ModelGraph instance = null;
	
	
	/**
	 * This method returns a reference to the single static 
	 * instance of the KnowledgeGraph
	 * @return knowledgeGraph instance
	 */
	protected ModelGraph() {
	      // instantiation is protected
	}
	
	/**
	 *  instantiate or get the singleton instance of this class or 
	 * @return KnowledgeGraph instance
	 */
	public static ModelGraph getInstance() {
	      if(instance == null) {
	         instance = new ModelGraph();
	      }
	      return instance;
    }
	
	public void defineHouse (String identifier) {
		System.out.println("defining " + identifier);
		House house = new House(identifier);
		houseMap.put(identifier.toLowerCase(), house);
	}
	
	public void defineRoom (String identifier, String floor, String type, String houseIdentifier) {
		System.out.println("defining " + identifier);
		Room room = new Room(identifier, Integer.parseInt(floor), RoomType.valueOf(type.toUpperCase()));
		House house = houseMap.get(houseIdentifier);
		house.roomMap.put(identifier, room);
	}
	
	public void defineOccupant (String identifier, String type) {
		System.out.println("defining " + identifier);
		Occupant occupant = new Occupant(identifier, OccupantType.valueOf(type.toUpperCase()));
		occupantMap.put(identifier, occupant);
	}
	
	public void addOccupant (String identifier, String houseIdentifier) {
		System.out.println("defining " + identifier);
		Occupant occupant = occupantMap.get(identifier);
		House house = houseMap.get(houseIdentifier);
		house.occupantMap.put(identifier, occupant);
	}
	
	public void defineDevice (String identifier, String type, String houseRoomIdentifier) {
		System.out.println("defining " + identifier);
		String[] tokens = houseRoomIdentifier.split(":");
    	String houseIdentifier  = tokens[0];
    	String roomIdentifier = tokens[1];
		House house = houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);
			switch (type.toLowerCase()) {
	            case "smoke_detector":  
	            	SmokeDetector smokeDetector = new SmokeDetector(identifier);
	            	room.deviceMap.put(identifier, smokeDetector);
	            	break;
	            case "camera":  
	            	Camera camera = new Camera(identifier);
	            	room.deviceMap.put(identifier, camera);
	            	break;
	            case "ava": 
	            	Ava ava = new Ava(identifier);
	            	room.deviceMap.put(identifier, ava);
	            	break; 
	            case "door": 
	            	Door door = new Door(identifier);
	            	room.deviceMap.put(identifier, door);
	            	break; 
	            case "tv": 
	            	Media tv = new Media(identifier,  MediaType.TV);
	            	room.deviceMap.put(identifier, tv);
	            	break;
	            case "pandora": 
	            	Media pandora = new Media(identifier,  MediaType.MUSIC);
	            	room.deviceMap.put(identifier, pandora);
	            	break;
	            case "oven": 
	            	Oven oven = new Oven(identifier);
	            	room.deviceMap.put(identifier, oven);
	            	break;
	            case "refrigerator": 
	            	Refrigerator refrigerator = new Refrigerator(identifier);
	            	room.deviceMap.put(identifier, refrigerator);
	            	break;
	            case "thermostat": 
	            	Thermostat thermostat = new Thermostat(identifier);
	            	room.deviceMap.put(identifier, thermostat);
	            	break;
	            case "window": 
	            	Window window = new Window(identifier);
	            	room.deviceMap.put(identifier, window);
	            	break;
	            default: 
	                    break;
			}	
	}
	//set  appliance house1:kitchen1:oven1 status power value ON
	public void setDevice (String houseRoomDeviceIdentifier, String status, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		System.out.println("setting " + houseRoomDeviceIdentifier);
		String[] tokens = houseRoomDeviceIdentifier.split(":");
    	String houseIdentifier  = tokens[0];
    	String roomIdentifier = tokens[1];
    	String deviceIdentifier = tokens[2];
    	
    	House house = houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);
		Device device = room.deviceMap.get(deviceIdentifier);
	
		status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
		System.out.println("status " + status);
		Method method = null;
		try {
			method = device.getClass().getMethod("set" + status, value.getClass());
		} catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
		method.invoke(device, value);
	}
	
	public void showDevice (String houseRoomDeviceIdentifier, String status) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		System.out.println("showing " + houseRoomDeviceIdentifier);
		String[] tokens = houseRoomDeviceIdentifier.split(":");
    	String houseIdentifier  = tokens[0];
    	String roomIdentifier = tokens[1];
    	String deviceIdentifier = tokens[2];
    	
    	House house = houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);
		Device device = room.deviceMap.get(deviceIdentifier);
		
		status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
		Method method = null;
		try {
			method = device.getClass().getMethod("get" + status);
		} catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
		System.out.println(method.invoke(device));
	}
			
	public void showDevice (String houseRoomDeviceIdentifier) {
		System.out.println("showing " + houseRoomDeviceIdentifier);
		String[] tokens = houseRoomDeviceIdentifier.split(":");
    	String houseIdentifier  = tokens[0];
    	String roomIdentifier = tokens[1];
    	String deviceIdentifier = tokens[2];
    	
    	House house = houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);
		Device device = room.deviceMap.get(deviceIdentifier);
		
		System.out.println(device.getConfiguration());
	}
	
	public void showHouseConfiguration (String houseIdentifier) {	
		System.out.println("showing " + houseIdentifier + " config");
    	House house = houseMap.get(houseIdentifier);
		System.out.println(house.getConfiguration());
	}
	
	public void showRoomConfiguration (String houseRoomIdentifier) {	
		System.out.println("showing " + houseRoomIdentifier + " config");
		String[] tokens = houseRoomIdentifier.split(":");
    	String houseIdentifier  = tokens[0];
    	String roomIdentifier = tokens[1];
    	
    	House house = houseMap.get(houseIdentifier);
		Room room = house.roomMap.get(roomIdentifier);
		System.out.println(room.toString());
		System.out.println(room.getConfiguration());
	}
	
	public void showConfiguration () {
		System.out.println("showing all config");
		System.out.println("Houses: " + houseMap.toString() + ", Occupants: " + occupantMap.toString());
	}
	
}
