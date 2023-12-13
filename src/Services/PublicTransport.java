package Services;
import Util.Location;
import Main.GameMap;


public class PublicTransport extends Service {
	// Attributes
    private int capacity;
    private Location location;
    private int length;
    private int breadth;
    private int boostValue;
    private int boostPercent;
    private GameMap GameMap;

    // Constructor
    public PublicTransport(String serviceID, int level, int x, int y, int length, int breadth, GameMap gameMap) {
        super(serviceID, level, "PublicTransport");
        this.capacity = (int)(length * breadth)/100; // Default capacity
        this.location.setLocation(x, y);
        this.length = length;
        this.breadth = breadth;
        this.boostValue = 10;
        this.boostPercent = 10;
        this.GameMap = gameMap;
    }
    
    
    // buildTransport() method for building road transport on the gamemap
    public boolean buildTransport() {
    	
    	if(!(GameMap.isAreaAvailable(location.getX(),location.getY(), length, breadth))) {
    		return false;
    	}
    	
    	String[][] road = new String[length][breadth]; // Declaring a new 2d road array.
    	for(int i = 0; i <= road.length - 1; i++) { // Initializing the road to empty fields.
    		for(int j = 0; j <= road[0].length - 1; j++) {
    			road[i][j] = " ";
    		}
    	}
    	
    	// Fill the outline cells of the array with '+' and inside with 'R'.
    	for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                if (i == 0 || i == length - 1 || j == 0 || j == breadth - 1) {
                    road[i][j] = " +";
                } else {
                	if(i % 2 == 0) {
                		road[i][j] = " ";
                	}
                	else {
                        road[i][j] = " - ";     		
                	}
                }
            }
        }
    	
    	//Place the road in the game map.
    	if(GameMap.placeObject(road, location.getX(), location.getY())) {
    		return true;
    	}
    	return false;
    }
    
    
    // Implementation of performUpgrade() method inherited from Service class
    @Override
    public String performUpgrade() {
    	if(capacity > RB.getPopulation() * 0.01) {
    		return ("Present Road transport is sufficient for the present population");
    	}
    	this.length += this.boostValue;
    	this.breadth += this.boostValue;
    	int status = upgradeService();
    	if(status == 0) {
    		return ("Not Enough Capital Balance!!");
    	}
    	else if(status == -1){
    		return ("Service already at maximum level :)");
    	}
    	else if(buildTransport()) {
    		RB.boostHappiness(this.boostPercent);
    		return ("Road Upgraded :)");
    	}
    	else {
    		return ("Selected area is already occupied!!");
    	}
    }
    
    
    // Implementation of upgradeService() method from Service class
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
    
    
    // Implementation of destroyService() for clean deletion of Road from map
    @Override
    public String destroyService() {
    	int destructionCost = level * 1000;
    	if(capital.getCapital() - destructionCost < 0) {
    		return ("Not Enough Capital Balance");
    	}
    	else {
    		capital.addToCapital(capital.getCapital() - destructionCost);
    		if(performDestruction()) {
    			return("Service Destroyed");
    		}
    		else {
    			return ("Service Not Destroyed!! Retry :)");
    		}
    	}
    }
    
    
    // Implementation of performDestruction() for deleting the object completely
    @Override
    public boolean performDestruction() {
    	if(GameMap.destroyObject(this.length, this.breadth, location.getX(), location.getY())) {
    		return true;
    	}
    	return false;
    }

   
}