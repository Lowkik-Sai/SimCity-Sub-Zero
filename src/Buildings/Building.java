package Buildings;
import Util.Location;
import Economy.Capital;


public abstract class Building {
    private String id;
    private Location location;
    private String type;
    private int level;

    public Capital capital;

    // Overridden by the sub class functions.
	public Boolean buildBuilding(){
		return (Boolean) null;
    }

    public int upgradeCost(int level){
        switch (level) {
            case 1:
                return 100;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 750;
            case 5:
                return 1000;

            default:
                return 0;
        }
    }

    public void UpgradeBuilding(String building_id, int level){
        int cost = upgradeCost(level);
        if (capital.hasSufficientCapital(cost)){
            capital.addToCapital(capital.getCapital()-cost);
            this.level += 1;
        }
        else{
            System.err.println("Not enough Money!");
        }
    }

    public void DestroyBuilding(String building_id) {
        this.location = null;
    }

    // Getter and setter methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
