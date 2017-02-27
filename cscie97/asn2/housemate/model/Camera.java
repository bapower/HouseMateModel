/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Camera extends Device {
	/**
	 * @param identifier
	 * @param type
	 */
	public Camera(String identifier) {	
		super(identifier);
		super.type = DeviceType.SENSOR;
	}
	
	@SuppressWarnings("unused")
	private void processRoomChange (Occupant occupant, Room room) {
		Room oldRoom = occupant.getRoom();
		room.addOccupant(occupant);
		oldRoom.removeOccupant(occupant);
		occupant.setRoom(room);
	}

	public void processStateChange (Occupant occupant, boolean awake) {
		occupant.setAwake(awake);
	}
}
