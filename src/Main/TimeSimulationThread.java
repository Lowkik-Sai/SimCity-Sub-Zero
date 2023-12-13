package Main;

public class TimeSimulationThread extends Thread {
    private final GameMap cityMap;
    private final GamePanel gamePanel;

    public TimeSimulationThread(GameMap cityMap, GamePanel gamePanel) {
        this.cityMap = cityMap;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (true) {
            // Simulate time by updating the game state
            cityMap.simulateTime();

            // Print a message indicating that time is running
            System.out.println("Time is running...");

            // Get the updated map
            String[][] map = cityMap.getMap();

            // Update the existing GamePanel with the new map
            gamePanel.updatePanel(map);
            gamePanel.displayPanel();

            try {
                // Sleep for a specific time interval
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
