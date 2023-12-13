package Economy;

public class Income {
    private int totalIncome;

    public Income() {
        // Initialize totalIncome with an initial value
        this.totalIncome = 0;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void calculateTotalIncome() {
        // Replace this with your actual logic to calculate income
        // This is a placeholder, you should adjust it based on your game's mechanics
        int incomeFromCommercial = calculateCommercialIncome();
        int incomeFromIndustrial = calculateIndustrialIncome();
        int incomeFromResidential = calculateResidentialIncome();

        // Add up the different sources of income
        totalIncome = incomeFromCommercial + incomeFromIndustrial + incomeFromResidential;
    }

    private int calculateCommercialIncome() {
        // Replace with logic to calculate income from commercial buildings
        return 30;
    }

    private int calculateIndustrialIncome() {
        // Replace with logic to calculate income from industrial buildings
        return 50;
    }

    private int calculateResidentialIncome() {
        // Replace with logic to calculate income from residential buildings
        return 10;
    }
}
