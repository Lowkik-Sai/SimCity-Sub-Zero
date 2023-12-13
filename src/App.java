import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import Main.*;
import Services.*;
import Util.*;
import Buildings.*;
import Infrastructure.*;
import Infrastructure.Power.*;
import Economy.*;

public class App {
    public static void main(String[] args) throws Exception {
        int mapSize = 15;
        GameMap cityMap = new GameMap(mapSize);
        cityMap.initializeMap();
        String[][] map = new String[mapSize][mapSize];

        Points points = cityMap.getPoints();

        GamePanel gamePanel = new GamePanel(map);
        gamePanel.displayPanel();

        Map<Point, Integer> buildingFlags = new HashMap<>();

        Capital cityCapital = new Capital();

        // Create a TimeSimulationThread instance
        TimeSimulationThread timeThread = new TimeSimulationThread(cityMap, gamePanel, cityCapital);
        timeThread.start();

        while (true) {
            Point lastClicked = gamePanel.getLastClickedCoordinates();

            if (lastClicked.equals(new Point(9, 9))) {
                System.out.println("Exiting the loop. Coordinates (9, 9) clicked.");
                timeThread.interrupt();
                break;
            }

            Point initialPoint = points.getPoint(lastClicked);

            if (initialPoint == null && buildingFlags.getOrDefault(lastClicked, 0) == 0 && !(lastClicked.equals(new Point(-1, -1)))) {
                int userChoice = DialogBox.createAndShowDialog();
                Location selectedLocation = new Location((int) lastClicked.getX(), (int) lastClicked.getY());

                buildingFlags.put(lastClicked, 1);

                if (userChoice == 0) {
                    ResidentialBuilding RB = new ResidentialBuilding("R", selectedLocation, 1, cityMap, cityCapital);
                    RB.buildBuilding();
                } else if (userChoice == 1) {
                    CommercialBuilding CB = new CommercialBuilding("C", selectedLocation, 1, cityMap);
                    CB.buildBuilding();
                } else if (userChoice == 2) {
                    IndustrialBuilding IB = new IndustrialBuilding("I", selectedLocation, 1, cityMap);
                    IB.buildBuilding();
                } else if (userChoice == 3) {
                    int greenSpace = DialogBox.handleParkBuildingConfirmation();
                    Park P = new Park("P", 1, greenSpace, selectedLocation, cityMap);
                    P.buildPark();
                } else if (userChoice == 4) {
                    PowerGenerator PG = new PowerGenerator("PG", 1, 500, selectedLocation, cityMap);
                    PG.buildGenerator();
                } else if (userChoice == 5) {
                    School S = new School("Sc", 1, selectedLocation, cityMap);
                    S.buildSchool();
                } else if (userChoice == 6) {
                	FireDepartment FD = new FireDepartment("FD", 1, 200, selectedLocation, cityMap);
                	FD.buildFireDepartment();
                }
                else if(userChoice == 7) {
                	PoliceDepartment PD = new PoliceDepartment("PD", 1, 200, selectedLocation, cityMap);
                	PD.buildPoliceDepartment();
                }
                else if(userChoice == 8) {
                	Hospital H = new Hospital("H", 1, 100, selectedLocation, cityMap);
                	H.buildHospital();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
