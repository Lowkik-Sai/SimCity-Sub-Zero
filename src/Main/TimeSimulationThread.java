package Main;

import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Economy.*;
import Main.GamePanel;

public class TimeSimulationThread extends Thread {
    private final GameMap cityMap;
    private final GamePanel gamePanel;
    private final Capital capital;

    public TimeSimulationThread(GameMap cityMap, GamePanel gamePanel, Capital capital) {
        this.cityMap = cityMap;
        this.gamePanel = gamePanel;
        this.capital = capital;
    }

    @Override
    public void run() {
        // Create the frame and displayPanel outside the loop
        SwingUtilities.invokeLater(() -> gamePanel.displayPanel());

        // Use a single Timer for time simulation and GUI updates
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate time by updating the game state
                cityMap.simulateTime();

                // Calculate and update income
                capital.updateCapital();

                // Update the money label
                SwingUtilities.invokeLater(() -> {
                    gamePanel.updateMoneyLabel();
                    System.out.println(capital.getCapital());
                });

                // Print a message indicating that time is running
                System.out.println("Time is running...");

                // Get the updated map
                String[][] map = cityMap.getMap();

                // Update the existing GamePanel with the new map
                SwingUtilities.invokeLater(() -> gamePanel.updatePanel(map));
            }
        });

        // Start the timer
        timer.start();

        // Keep checking for the last clicked coordinates in a loop until (9, 9) is clicked
        while (true) {
            Point lastClicked = gamePanel.getLastClickedCoordinates();

            if (lastClicked.equals(new Point(9, 9))) {
                System.out.println("Exiting the loop. Coordinates (9, 9) clicked.");
                timer.stop(); // Stop the timer before exiting
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
