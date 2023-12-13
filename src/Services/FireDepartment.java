package Services;
import Util.Location;
import Main.GameMap;

public class FireDepartment extends Service {
    private int fireFightingCoverage;
    private int boostFireFightingCoverage;
    private Location location;
    private GameMap GameMap;

    public FireDepartment(String serviceID, int level, int fireFightingCoverage, Location location, GameMap gameMap) {
        super(serviceID, level, "FireDepartment");
        this.fireFightingCoverage = fireFightingCoverage;
        this.boostFireFightingCoverage = 5; 
        this.location = location;
        this.GameMap = gameMap;
    }

    // Set location coordinates of the fire department
    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }

    @Override
    public void performUpgrade() {
        this.fireFightingCoverage += boostFireFightingCoverage; 
        int status = upgradeService();
        if (status == 0) {
        	System.out.println("Not Enough Capital Balance!!");
        } else if (status == -1) {
        	System.out.println("Service Already at maximum level");
        }
        System.out.println("Fire Department Upgraded :)");
    }

    public int buildFireDepartment() {
        int side = 2; // Calculating the length of the side of the fire department

        // Checks whether the area is available
        if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
        	System.out.println("Selected Area is not available !!");
        	return 0;
        }

        String[][] fireDepartment = new String[side][side]; // Declaring a new fire department using size


        for(int i = 0; i <= side - 1; i++) {
        	for(int j = 0; j <= side - 1; j++) {
        		fireDepartment[i][j] = "F";
        	}
        }

        if (GameMap.placeObject(fireDepartment, location.getX(), location.getY())) {
        	System.out.println("Fire Department Builded :)");
        	return 1;
        } else {
        	System.out.println("Retry !!");
        	return 0;
        }
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
    
    

    // Implementation of performDestruction() for proper deletion of FireDepartment Object
    @Override
    public boolean performDestruction() {
        this.level = 0;
        int size = 2;
        if (GameMap.destroyObject(size, size, location.getX(), location.getY())) {
            return true;
        }
        return false;
    }
}
