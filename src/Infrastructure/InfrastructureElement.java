package Infrastructure;
import Economy.Capital;

public class InfrastructureElement {
    private String infraID;
    private String type;
    private int level;

    public Capital capital;

    public InfrastructureElement(String infraID, String type, int level) {
        this.infraID = infraID;
        this.type = type;
        this.level = level;
    }

    // Add any other methods or attributes common to all infrastructure elements

    public String getInfraID() {
        return infraID;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void displayInfo() {
        System.out.println("Infrastructure ID: " + infraID);
        System.out.println("Type: " + type);
        System.out.println("Level: " + level);
    }

    // Function to upgrade the infrastructure

    public int upgradeInfrastructure() {
        // This function will be overridden in sub-classes
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

    // Function to destroy the infrastructure

    public String destroyInfrastructure() {
        // This function will be overridden in sub-classes
        int destructionCost = level * 1000;
        if(capital.getCapital() - destructionCost < 0) {
            return ("Not Enough Capital Balance");
        }
        else {
            capital.addToCapital(capital.getCapital() - destructionCost);
            if(performDestruction()) {
                return("Infrastructure Destroyed");
            }
            else {
                return ("Infrastructure Not Destroyed!! Retry :)");
            }
        }
    }

    public boolean performDestruction() {
        this.level = 0;
        return true;
    }

}
