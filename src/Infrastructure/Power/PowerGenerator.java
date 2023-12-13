package Infrastructure.Power;
import Util.Location;
import Main.GameMap;


public class PowerGenerator extends Power {
    private static int noOfGenerators = 0;
    private int supply; // in kW
    private Location location;
    private GameMap GameMap;

    public PowerGenerator(String infraID, int level, int demand, Location location, GameMap gameMap) {
        super(infraID, level, demand);
        this.location = location; //Building at that certain location
        this.supply = calculateSupply(demand); //Supply of the power generator
        this.GameMap = gameMap;
        PowerGenerator.noOfGenerators++;
    }

    public int calculateSupply(int demand) {
        int supply = (int) (demand * 1.2)+50; //Extra 50kW
        return supply;
    }


    public boolean buildGenerator() {

        int side = 1; // default
        String[][] powerHouse = new String[side][side]; // Declaring new powerHouse using size

        // Checks whether area is available
        if(!(GameMap.isAreaAvailable(location.getX(),location.getY(), side, side))) {
            return false;
        }


        int electric = 0x00002301;
        
        powerHouse[0][0] = Character.toString(electric);
       

        if(GameMap.placeObject(powerHouse,location.getX(),location.getY())) {
            return true;
        }
        return false;

    }

    @Override
    public boolean performDestruction() {
        int side = 1;
        if (GameMap.destroyObject( side, side, location.getX(), location.getY())) {
            return true;
        }
        return false;
    }


    // Override displayInfo to include generator-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Total Power Supply Storage: " + this.supply + " MW");
    }

    // Function to update power supply
    public void updateSupply(int newSupply) {
        this.supply = newSupply;
        System.out.println("Power Supply Storage Updated to: " + newSupply + " MW");
    }

    // Function to build power supply.
    public String upgradeGenerator(int supply) {
        this.supply += supply;;
        int status = super.upgradeInfrastructure();
        if(status == 0) {
            return ("Not Enough Capital Balance!!");
        }
        else if(status == -1) {
            return ("Power Infrastructure is already at maximum level..");
        }
        return ("Power House Upgraded :)");
    }
    

    // Function to destroy power supply
    public boolean destroyGenerator() {
        int side = 1; // default
        if(GameMap.destroyObject(side, side, location.getX(),location.getY())) {
            return true;
        }
        return false;

    }

    @Override
    public void expandPowerSupply(int expandSupply) {
        if(this.supply>super.getDemand()+expandSupply){
            super.expandPowerSupply(expandSupply);
        }else{
            this.upgradeGenerator(expandSupply);
            this.expandPowerSupply(expandSupply);
        }
    }


}