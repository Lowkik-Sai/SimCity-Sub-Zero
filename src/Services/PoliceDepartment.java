package Services;
import Util.Location;
import Main.GameMap;

public class PoliceDepartment extends Service {
    private int PoliceCoverage;
    private int boostPoliceCoverage;
    private Location location;
    private GameMap GameMap;

    public PoliceDepartment(String serviceID, int level, int PoliceCoverage, Location location,  GameMap gameMap) {
        super(serviceID, level, "PoliceDepartment");
        this.PoliceCoverage = PoliceCoverage;
        this.boostPoliceCoverage = 5; // Default boost value for Police coverage
        this.location = location;
        this.GameMap = gameMap;
    }

    // Set location coordinates of the Police department
    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }

    @Override
    public void performUpgrade() {
        this.PoliceCoverage += boostPoliceCoverage; // Customizable boost value for Police coverage
        int status = upgradeService();
        if (status == 0) {
        	System.out.println("Not Enough Capital Balance!!");
        } else if (status == -1) {
        	System.out.println("Service Already at maximum level");
        } else if (buildPoliceDepartment()) {
        	System.out.println("Police Department Upgraded :)");// Updating the Police department after Police coverage is updated
        } else {
        	System.out.println("Selected area is already occupied by an object!!");
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
    
   

    public boolean buildPoliceDepartment() {
        int side = 2; // Calculating the length of the side of the Police department

        // Checks whether the area is available
        if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
            return false;
        }

        String[][] PoliceDepartment = new String[side][side]; // Declaring a new Police department using size

        for(int i = 0; i <= side - 1; i++) {
        	for(int j = 0; j <= side - 1; j++) {
        		PoliceDepartment[i][j] = "P";
        	}
        }

        if (GameMap.placeObject(PoliceDepartment, location.getX(), location.getY())) {
            return true;
        } else {
            return false;
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
        if (GameMap.destroyObject(size, size, location.getX(), location.getY())) {
            return true;
        }
        return false;
    }
}
