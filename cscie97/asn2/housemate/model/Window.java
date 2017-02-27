/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry
 *
 */
public class Window extends Device {
	private boolean open = true;
	private boolean blindsOpen = true;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Window(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public boolean getOpen(){
    	return open;
    }
    
    public void setOpen(boolean open){
    	this.open = open;
    }
    
    public boolean getBlindsOpen(){
    	return blindsOpen;
    }
    
    public void setBlindsOpen(boolean blindsOpen){
    	this.blindsOpen = blindsOpen;
    }
    
    public String getConfiguration () {
    	return "Open: " + open + ", Blinds open: " + blindsOpen;
    }
}
