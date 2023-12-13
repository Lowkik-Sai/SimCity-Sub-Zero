package Services;
import Economy.Capital;
import Buildings.ResidentialBuilding;

// Acts as a blue print for the sub classes
public abstract class Service{
	// Attributes
    private String serviceID;
    protected int level;
    private String type;
    public Capital capital;
    public ResidentialBuilding RB;

    // Constructor
    public Service(String serviceID, int level, String type) { 
        this.serviceID = serviceID;
        this.level = level;
        this.type = type;
    }

    // Abstract method for upgrading a service if all the conditions are met
    public abstract int upgradeService();
    
    
    // Abstract method for checking the prerequisites for upgrading a service
    public abstract void performUpgrade();
    
   
    // Abstract method for deleting objects and resetting its data
    public abstract void destroyService();
    
    
    // Abstract method for clean removal of objects from the game map
    public abstract boolean performDestruction();
}
