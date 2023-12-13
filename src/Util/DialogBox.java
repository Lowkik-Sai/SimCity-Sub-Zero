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
                "School"
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
            // Add cases for other building types if needed
            default:
                return -1; // Default value for invalid choice
        }
    }

    private static int handleResidentialBuildingConfirmation() {
        // Residential Building cost
        int buildCost = 5000;

        // Display confirmation dialog for Residential Building with cost
        int confirmChoice = JOptionPane.showConfirmDialog(
                null,
                "Build a Residential Building?\nCost: " + buildCost,
                "Residential Building Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        return confirmChoice;
    }

    private static int handleCommercialBuildingConfirmation() {
        // Commercial Building cost
        int buildCost = 7000;

        // Display confirmation dialog for Commercial Building with cost
        int confirmChoice = JOptionPane.showConfirmDialog(
                null,
                "Build a Commercial Building?\nCost: " + buildCost,
                "Commercial Building Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        return confirmChoice;
    }

    private static int handleIndustrialBuildingConfirmation() {
        // Industrial Building cost
        int buildCost = 12000;

        // Display confirmation dialog for Industrial Building with cost
        int confirmChoice = JOptionPane.showConfirmDialog(
                null,
                "Build an Industrial Building?\nCost: " + buildCost,
                "Industrial Building Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        return confirmChoice;
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
