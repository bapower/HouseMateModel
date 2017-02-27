/**
 * 
 */
package cscie97.asn2.housemate.model;

/**
 * @author Bry Power
 *
 */
public class Door extends Device {
	private boolean open = true;
	
	/**
	 * @param identifier
	 * @param type
	 */
	public Door(String identifier) {	
		super(identifier);
		super.type = DeviceType.APPLIANCE;
	}
	
	public boolean getOpen(){
    	return open;
    }
    
    public void setOpen(boolean open){
    	this.open = open;
    }
    
    public String getConfiguration () {
    	return "Open: " + open;
    }
}
