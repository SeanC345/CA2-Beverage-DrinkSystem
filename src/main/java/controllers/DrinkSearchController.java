package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SearchUtils;

/**
 * Controller for the Drink Search view.
 * Allows the user to search for drinks by name and/or alcohol content (ABV),
 * and navigate between screens.
 */
public class DrinkSearchController {

    // FXML elements
    @FXML
    private Button backButton; // Button to navigate back to the Drink Menu
    @FXML
    private Button homeButton; // Button to return to the Main Menu
    @FXML
    private Button searchButton; // Button to execute the search
    @FXML
    private TextField nameField; // Input field for the drink's name
    @FXML
    private TextField ABVField; // Input field for the drink's ABV

    /**
     * Initializes the controller by setting up button event handlers.
     */
    @FXML
    public void initialize() {
        buttonEvents(); // Configure button actions
    }

    /**
     * Navigates back to the Drink Menu view.
     */
    @FXML
    public void back() {
        System.out.println("Going back to the previous screen."); // Log the action
        App.switchScene("/views/DrinkMenuView"); // Switch to the Drink Menu view
    }

    /**
     * Navigates back to the Main Menu view.
     */
    @FXML
    public void home() {
        System.out.println("Going back to the home screen."); // Log the action
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }

    /**
     * Executes a search for drinks based on the provided name and/or ABV.
     * Validates inputs and navigates to the search results view.
     */
    @FXML
    public void search() {
        String name = nameField != null ? nameField.getText().trim() : ""; // Get the entered name or default to an empty string
        String abvText = ABVField != null ? ABVField.getText().trim() : ""; // Get the entered ABV or default to an empty string

        // Check if both fields are empty
        if (name.isEmpty() && abvText.isEmpty()) {
            System.out.println("Please enter a name or ABV to search for a drink."); // Log missing input
            return;
        }

        // Parse ABV input if provided
        double abv = -1; // Use -1 to indicate no ABV filter
        if (!abvText.isEmpty()) {
            try {
                abv = Double.parseDouble(abvText); // Convert the ABV input to a double
                if (abv < 0 || abv > 100) {
                    System.out.println("ABV must be between 0 and 100."); // Log invalid ABV range
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ABV value! Please enter a valid number."); // Log invalid number format
                return;
            }
        }

        // Perform the search based on input criteria
        if (abv == -1) { // If no ABV filter is applied
            System.out.println("Searching for drink with name: " + name); // Log the search action
            App.setDrinkResults(SearchUtils.searchDrinksByName(App.drinksTable, name)); // Search by name
        } else if (name.isEmpty()) { // If only ABV filter is applied
            System.out.println("Searching for drink with ABV: " + abv); // Log the search action
            App.setDrinkResults(SearchUtils.searchDrinksByABV(App.drinksTable, abv)); // Search by ABV
        } else { // If both name and ABV filters are applied
            System.out.println("Searching for drink with name: " + name + " and ABV: " + abv); // Log the search action
            App.setDrinkResults(SearchUtils.searchDrinksByNameAndABV(App.drinksTable, name, abv)); // Search by name and ABV
        }

        // Navigate to the search results view
        App.switchScene("/views/DrinkSearchResults");
    }

    /**
     * Configures event handlers for the buttons.
     */
    private void buttonEvents() {
        backButton.setOnAction(e -> back()); // Back button action
        homeButton.setOnAction(e -> home()); // Home button action
        searchButton.setOnAction(e -> search()); // Search button action
    }
}
