package Buildings;
import Main.GameMap;
import Util.Location;

public class IndustrialBuilding extends Building{
    private int income;
    private GameMap GameMap;
    private Location location;
    private String id;
    private int level;

    public IndustrialBuilding(String id, Location location, int level, GameMap gameMap) {
      this.id = id;
      this.location = location;
      this.level = level;
      this.GameMap = gameMap;
    }
    
    @Override
    public Boolean buildBuilding() {
    int side = 1;

    // Checks whether the area is available
    if (!(GameMap.isAreaAvailable(location.getX(), location.getY(), side, side))) {
        return false;
    }

    String[][] industrialBuilding = new String[side][side]; // Declaring a new industrial building using size

    
    //int Industry = (int) 1F300;
    for (int i = 0; i < industrialBuilding.length; i++) {
        for (int j = 0; j < industrialBuilding[i].length; j++) {
            industrialBuilding[i][j] = "L";
        }
    }

    if (GameMap.placeObject(industrialBuilding, location.getX(), location.getY())) {
        return true;
    } else {
        return false;
    }
}


    @Override
    public String getType() {
        return "I";
    }

}
