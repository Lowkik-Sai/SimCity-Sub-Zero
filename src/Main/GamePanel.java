package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel {

    private JPanel panel;
    private Point lastClickedCoordinates;
    private String[][] mapArray;
    private JFrame frame;

    public GamePanel(String[][] mapArray) {
        this.mapArray = mapArray;
        this.panel = new JPanel();
        this.lastClickedCoordinates = new Point(-1, -1);

        initializeFrame();
        fillPanel();
        mouseTracker();
        displayPanel();  // Call displayPanel after initializing the frame
    }

    private void initializeFrame() {
        frame = new JFrame("Sim-City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void fillPanel() {
        panel.removeAll();
        panel.setLayout(new GridLayout(mapArray.length, mapArray[0].length));

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[0].length; j++) {
                JLabel label = new JLabel(mapArray[i][j], SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
            }
        }

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
                if(y >= 8){
                    lastClickedCoordinates.setLocation(x, y - 1);
                }
                else{
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
}
