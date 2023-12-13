package Services;
import Util.Location;
import Main.GameMap;


public class Hospital extends Service {
    private int healthcareCapacity;
    private int boostHealthcareCapacity;
    private Location location;
    private GameMap GameMap;

    public Hospital(String serviceID, int level, int healthcareCapacity, Location location, GameMap gameMap) {
        super(serviceID, level, "Hospital");
        this.healthcareCapacity = healthcareCapacity;
        this.boostHealthcareCapacity = 10; // Default boost value for health care capacity
        this.location = location;
        this.GameMap = gameMap;
    }

    // Set location coordinates of the hospital
    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }

    @Override
    public void performUpgrade() {

        this.healthcareCapacity += boostHealthcareCapacity; // Customizable boost value for health care capacity
        int status = upgradeService();
        if(status == 0) {
        	System.out.println("Not Enough Capital Balance!!");
        }
        else if(status == -1){
        	System.out.println("Service Already at maximum level");
        }
        System.out.println("Hospital Upgraded :)");
    }
    
    
    @Override
    public int upgradeService() {
        if(this.level < 5) {
        	int upgradeCost = level * 1000;
        	if(capital.getCapital() - upgradeCost < 0) {
        		return 0;
        	}
        	capital.addToCapital(capital.getCapital() - upgradeCost);
        	this.level++;
        	return 1;
        }
        else {
        	return -1;
        }
    }
    

    public int buildHospital() {
    	
        int side = 2; // Calculating the length of the side of the hospital
    	
        // Checks whether area is available
        if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
        	System.out.println("Selected Location not available!!");
        	return 0;
        }


        String[][] hospital = new String[side][side]; // Declaring a new hospital using size
        

        // Filling the hospital inside area with 'H'.
        for(int i = 0; i <= side - 1; i++) {
        	for(int j = 0; j <= side - 1; j++) {
        		hospital[i][j] = "H";
        	}
        }
        

        if(GameMap.placeObject(hospital, location.getX(), location.getY())) {
        	System.out.println("Hospital Built :)");
        	return 1;
        }
        else {
        	System.out.println("Retry !!");
        	return 0;
        }
    }
    
    
    @Override
    public void destroyService() {
    	int destructionCost = level * 1000;
    	if(capital.getCapital() - destructionCost < 0) {
    		System.out.println("Not Enough Capital Balance");
    	}
    	else {
    		capital.addToCapital(capital.getCapital() - destructionCost);
    		if(performDestruction()) {
    			System.out.println("Service Destroyed");
    		}
    		else {
    			System.out.println("Service Not Destroyed!! Retry :)");
    		}
    	}
    }
    
    

    @Override
    public boolean performDestruction() {
        this.level = 0;
        int size = 2;
        if(GameMap.destroyObject(size, size, location.getX(), location.getY())) {
        	return true;
        }
        return false;
    }
}