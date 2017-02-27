/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Media extends Device {
	
	public enum MediaType {
		TV, MUSIC;
	}
	
	private int volume = 1;
	
	private int channel = 1;
	
	protected MediaType type;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Media(String identifier, MediaType type) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
		this.type = type;
	}
	
	public int getVolume(){
    	return volume;
    }
	
	public int getChannel(){
    	return channel;
    }
    
    public void setVolume(int volume){
    	this.volume = volume;
    }
    
    public void setChannel(int channel){
    	this.channel = channel;
    }
    
    public String getConfiguration () {
    	return "Power: " + power + ", Volume: " + volume + ", Channel: " + channel;
    }
}
