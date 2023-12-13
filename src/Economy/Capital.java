package Economy;

public class Capital {
    private int capital;
    private Income income;

    public Capital() {
        this.capital = 50000;
        this.income = new Income();
    }

    public void addToCapital(int value) {
        capital += value;
    }

    public int getCapital() {
        return capital;
    }

    public void updateCapital() {
        income.calculateTotalIncome();
        int incomeEarned = income.getTotalIncome();
        addToCapital(incomeEarned);
    }

    public void deductFromCapital(int value) {
        if (hasSufficientCapital(value)) {
            capital -= value;
        } else {
            System.out.println("Insufficient funds to deduct: $" + value);
        }
    }

    public boolean hasSufficientCapital(int amount) {
        return amount <= capital;
    }
}
