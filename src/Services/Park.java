// Park.java
package Services;

import Util.Location;
import Main.GameMap;

public class Park extends Service {
	// Attributes
    private int boostPercentage;
    private int greenSpace;
    private int boostgreenSpace;
    private Location location;
    private GameMap GameMap;
    

    // Constructor..
    public Park(String serviceID, int level, int greenSpace, Location location, GameMap gameMap) {
        super(serviceID, level, "Park");
        this.boostPercentage = 10;   // Default boost percentage
        this.greenSpace = greenSpace;
        this.boostgreenSpace = 1; // Default boost value for green space
        this.location = new Location(location.getX(), location.getY());
        this.GameMap = gameMap;
    }

    
    // Set location coordinates of the park
    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }
    

    // Setter method for boostPercentage
    public void setBoostPercentage(int boostPercentage) {
        this.boostPercentage = boostPercentage;
    }
    
    
    // buildPark() method for building park on the game map
    public boolean buildPark() {
        int side = (int) Math.sqrt(this.greenSpace);
        String[][] park = new String[side][side];

        // Checks whether the area is available
        if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
            return false;
        }

        for (int i = 0; i <= park.length - 1; i++) {
            for (int j = 0; j <= park[i].length - 1; j++) {
                park[i][j] = " ";
            }
        }

        int grass = 0x0001F33F;
        for (int i = 0; i <= side - 1; i++) {
            for (int j = 0; j <= side - 1; j++) {
                if (i == 0 || i == side - 1 || j == 0 || j == side - 1) {
                    if (i < park.length && j < park[i].length) {
                        park[i][j] = " +";
                    }
                } else {
                    park[i][j] = Character.toString(grass);
                }
            }
        }

        // Call placeObject and update the GameMap directly
        if (GameMap.placeObject(park, location.getX(), location.getY())) {
            return true;
        }

        return false;
    }
    

    
    // Implementation of upgradeService() inherited from Service Class
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
    

    // Implementation of performUpgrade() inherited from Service Class
    @Override
    public String performUpgrade() {

        this.greenSpace += boostgreenSpace; // Customizable boost value for health care capacity
        int status = upgradeService();
        if(status == 0) {
        	return ("Not Enough Capital Balance!!");
        }
        else if(status == -1){
        	return ("Service Already at maximum level");
        }
        // Boosting the happiness of the population once park is upgraded
        this.RB.boostHappiness(boostPercentage);
        return ("Park Upgraded :)");
    }
    
    
    // Implementation of destroyService() for clean deletion of park object
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
    
    
    // Implementation of performDestruction() for proper deletion of the park object
    @Override
    public boolean performDestruction() {
        int size = (int) Math.sqrt(greenSpace);
        if (GameMap.destroyObject(size, size, location.getX(), location.getY())) {
            return true;
        }
        return false;
    }
}
