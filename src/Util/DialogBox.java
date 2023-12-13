package Util;

import javax.swing.*;
import java.awt.*;

public class DialogBox {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowDialog());
    }

    public static int createAndShowDialog() {
        // Set the default font for JOptionPane buttons
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));

        // Array of building options
        String[] options = {
                "Residential Building",
                "Commercial Building",
                "Industrial Building",
                "Park",
                "Power Generator",
                "School",
                "Fire Department",
                "Police Department",
                "Hospital"
        };

        // Display the option dialog
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select the building you want to build:",
                "Building Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        handleUserChoice(choice);
        return choice;
    }

    private static int handleUserChoice(int choice) {
        switch (choice) {
            case 0:
                // User selected Residential Building
                return handleResidentialBuildingConfirmation();
            case 1:
                // User selected Commercial Building
                return handleCommercialBuildingConfirmation();
            case 2:
                // User selected Industrial Building
                return handleIndustrialBuildingConfirmation();
            case 6:
                // User selected Fire Department
                return handleFireDepartmentConfirmation();
            case 7:
                // User selected Police Department
                return handlePoliceDepartmentConfirmation();
            case 8:
                // User selected Hospital
                return handleHospitalConfirmation();
            // Add cases for other building types if needed
            default:
                return -1; // Default value for invalid choice
        }
    }

    private static int handleResidentialBuildingConfirmation() {
        // Residential Building cost
        int buildCost = 5000;
        return showConfirmationDialog("Residential Building", buildCost);
    }

    private static int handleCommercialBuildingConfirmation() {
        // Commercial Building cost
        int buildCost = 7000;
        return showConfirmationDialog("Commercial Building", buildCost);
    }

    private static int handleIndustrialBuildingConfirmation() {
        // Industrial Building cost
        int buildCost = 12000;
        return showConfirmationDialog("Industrial Building", buildCost);
    }

    private static int handleFireDepartmentConfirmation() {
        // Fire Department cost
        int buildCost = 7000;
        return showConfirmationDialog("Fire Department", buildCost);
    }

    private static int handlePoliceDepartmentConfirmation() {
        // Police Department cost
        int buildCost = 8000;
        return showConfirmationDialog("Police Department", buildCost);
    }

    private static int handleHospitalConfirmation() {
        // Hospital cost
        int buildCost = 10000;
        return showConfirmationDialog("Hospital", buildCost);
    }

    private static int showConfirmationDialog(String buildingName, int buildCost) {
        // Display confirmation dialog with the specified cost
        return JOptionPane.showConfirmDialog(
                null,
                "Build a " + buildingName + "?\nCost: " + buildCost,
                buildingName + " Confirmation",
                JOptionPane.YES_NO_OPTION
        );
    }

    public static int handleParkBuildingConfirmation() {
        // Ask the user to input the green space value
        String input = JOptionPane.showInputDialog(
                null,
                "Enter the Green Space value for the Park:",
                "Park Building",
                JOptionPane.QUESTION_MESSAGE
        );

        // Convert the input to an integer
        try {
            int greenSpace = Integer.parseInt(input);
            // Return the green space value
            return greenSpace;
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            System.out.println("Invalid input for Green Space. Defaulting to 0.");
            return 0;
        }
    }
}
