package Buildings;
import Util.Location;
import Main.GameMap;

public class ResidentialBuilding extends Building{
	
	private int Population;
	private int Happiness;
    private GameMap GameMap;
    private Location location;
    private String id;
    private int level;
    private double boostpercentage;

    public ResidentialBuilding(String id, Location location, int level, GameMap gameMap) {
        this.id = id;
        this.location = location;
        this.level = level;
        this.GameMap = gameMap;
        this.boostpercentage = .1;
    }

    @Override
    public Boolean buildBuilding() {
        int side = 1;

        // Checks whether the area is available
        if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
            return false;
        }

        // Create a new map object
        String[][] buildingMap = new String[side][side];

        // Declare the type of building on the map
        buildingMap[0][0] = "R";

        // Place the building on the map
        if (GameMap.placeObject(buildingMap, location.getX(), location.getY())) {
            // Additional logic if needed
            boostHappiness(boostpercentage);
            return true;
        } else {
            return false;
        }
    }


    
    public void payTaxes(int tax_amount) {
    	if(capital.getCapital() - tax_amount < 0) {
    		capital.addToCapital(capital.getCapital() - tax_amount);
    	}
    	else {
    		System.err.println("Not Enough Capital Balance");
    	}
    }
    
    public int getHappiness() {
    	return this.Happiness;
    }
    
    public int getPopulation() {
    	return this.Population;
    }
    
    public void boostHappiness(double boostpercent) {
    	this.Happiness += boostpercent;
    }

    @Override
    public String getType() {
        return "R";
    }
}