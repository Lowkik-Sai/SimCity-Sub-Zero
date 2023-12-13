package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Economy.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel {

    private JPanel panel;
    private Point lastClickedCoordinates;
    private String[][] mapArray;
    private JFrame frame;
    private JLabel populationLabel;
    private JLabel moneyLabel;
    private Income income;
    private Capital capital;

    public GamePanel(String[][] mapArray) {
        this.mapArray = mapArray;
        this.panel = new JPanel();
        this.lastClickedCoordinates = new Point(-1, -1);
        this.capital = new Capital();
        this.income = new Income();

        initializeFrame();
        createLabels();  // Create population and money labels
        fillPanel();
        mouseTracker();
        displayPanel();  // Call displayPanel after initializing the frame
        startTimer();  // Start the timer for periodic updates
    }

    private void initializeFrame() {
        frame = new JFrame("Sim-City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void createLabels() {
        // Create labels for population and money
        populationLabel = new JLabel("Population: " + getPopulation(), SwingConstants.CENTER);
        moneyLabel = new JLabel("Money: $" + capital.getCapital(), SwingConstants.CENTER);
    }

    private void fillPanel() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        // Create a panel for the main grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(mapArray.length, mapArray[0].length));

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[0].length; j++) {
                JLabel label = new JLabel(mapArray[i][j], SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(label);
            }
        }

        // Create a panel for the labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 2));
        labelPanel.add(populationLabel);
        labelPanel.add(moneyLabel);

        // Add the main grid and labels to the main panel
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void mouseTracker() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellWidth = frame.getContentPane().getWidth() / mapArray[0].length;
                int cellHeight = frame.getContentPane().getHeight() / mapArray.length;

                int x = (int) (e.getY() / cellWidth);
                int y = (int) (e.getX() / cellHeight);
                if (y >= 8) {
                    lastClickedCoordinates.setLocation(x, y - 1);
                } else {
                    lastClickedCoordinates.setLocation(x, y);
                }

                System.out.println("Clicked at coordinates: (" + x + ", " + y + ")");
            }
        });
    }

    public void displayPanel() {
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void updatePanel(String[][] newMapArray) {
        this.mapArray = newMapArray;
        fillPanel();
    }

    public Point getLastClickedCoordinates() {
        return lastClickedCoordinates;
    }

    // Method to update money label
    public void updateMoneyLabel() {
        SwingUtilities.invokeLater(() -> {
            moneyLabel.setText("Money: $" + capital.getCapital());
            moneyLabel.revalidate();
            moneyLabel.repaint();
        });
    }

    // Example methods to get population and money (replace with your actual methods)
    private int getPopulation() {
        // Replace with your logic to get the current population
        return 10000;
    }

    // Start the timer for periodic updates
    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate time by updating the game state
                // This should be done in the EDT
                SwingUtilities.invokeLater(() -> {
                    income.calculateTotalIncome();
                    capital.updateCapital();
                    updateMoneyLabel();
                });
            }
        });
        timer.start();
    }
}
