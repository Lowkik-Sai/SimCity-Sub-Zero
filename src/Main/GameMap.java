package Main;
import Main.GamePanel;
import Util.Points;
import java.awt.Point;

public class GameMap {

    private String[][] GameMap;
    private GamePanel panel;
    private Points points;
    private int timeCounter = 0;

    public GameMap(int size) {
        this.GameMap = new String[size][size];
        initializeMap();
        this.points = new Points();
    }

    public void initializeMap() {
        for(int i = 0; i <= GameMap.length - 1; i++) {
            for(int j = 0; j <= GameMap[i].length - 1; j++) {
                GameMap[i][j] = " "; // Initializing the map area to empty fields.
            }
        }
    }

    // Expand Map on user wish.
    public void expandMap() {
        // Implementation for expanding the map
    }

    // Displays the map in the gaming terminal
    public void DisplayMap() {
        int cellWidth = 3; // Adjust this value based on your preference
        for (int i = 0; i <= GameMap.length - 1; i++) {
            for (int j = 0; j <= GameMap[i].length - 1; j++) {
                // Print the content of the cell with a fixed width
                System.out.printf("%-" + cellWidth + "s", GameMap[i][j]);
                // Print a vertical line after each cell
                System.out.print("|");
            }
            // Move to the next line after each row
            System.out.println();
            // Print a horizontal line between each row
            for (int k = 0; k < GameMap[0].length * (cellWidth + 1) - 1; k++) {
                System.out.print("-");
            }
            System.out.println(); // Move to the next line after the horizontal line
        }
    }

    // Method for checking the availability of area in the map
    public boolean isAreaAvailable(int x, int y, int length, int breadth) {
        if (x < 0 || y < 0 || x + length > GameMap.length || y + breadth > GameMap[0].length) {
            return false;
        }

        // Check if the specified area is unoccupied
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                if (GameMap[x + i][y + j] != " ") {
                    // The area is already occupied
                    return false;
                }
            }
        }

        // The area is available
        return true;
    }

    // Places the built object in the game map.
    public boolean placeObject(String[][] building, int x, int y) {
        for (int i = 0; i < building.length; i++) {
            for (int j = 0; j < building[i].length; j++) {
                if (x + i < GameMap.length && y + j < GameMap[0].length) {
                    if (GameMap[x + i][y + j].equals(" ")) {
                        GameMap[x + i][y + j] = building[i][j];
                        // Add the point association for the current cell
                        points.addPoint(new Point(x + i, y + j), new Point(x, y));
                    } else {
                        // If the area is already occupied, return false
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Updates the game map if any object is destroyed.
    public boolean destroyObject(int length, int breadth, int x, int y) {
        for(int i = 0; i <= length - 1; i++) {
            for(int j = 0; j <= breadth - 1; j++) {
                GameMap[x + i][y + j] = " ";
                // Remove the point association for the current cell
                points.addPoint(new Point(x + i, y + j), null);
            }
        }
        return true;
    }

    public String[][] getMap() {
        return this.GameMap;
    }

    // Getter for the Points instance
    public Points getPoints() {
        return this.points;
    }

    public void simulateTime() {
        // Update the state of your game to simulate the passage of time
        // This is where you can implement logic for buildings aging, resource production, etc.

        // For example, let's increment a time counter in this simple demonstration
        timeCounter++;
        System.out.println("Time is now: " + timeCounter);
    }
}
