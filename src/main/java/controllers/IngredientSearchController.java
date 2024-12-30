package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SearchUtils;

/**
 * Controller for the Ingredient Search view.
 * Allows users to search for ingredients by name or maximum ABV (Alcohol by Volume).
 */
public class IngredientSearchController {

    // FXML elements for user input and navigation
    @FXML
    private Button backButton; // Button to navigate back to the Ingredient Menu
    @FXML
    private Button homeButton; // Button to return to the Main Menu
    @FXML
    private Button searchButton; // Button to execute the search
    @FXML
    private TextField nameField; // Input field for the ingredient name
    @FXML
    private TextField maxAbvField; // Input field for the maximum ABV of ingredients

    /**
     * Initializes the controller by setting up button event handlers.
     */
    @FXML
    public void initialize() {
        buttonEvents(); // Attach actions to buttons
    }

    /**
     * Navigates back to the Ingredient Menu view.
     */
    @FXML
    public void back() {
        System.out.println("Going back to the previous screen."); // Log navigation
        App.switchScene("/views/IngredientMenuView"); // Switch to the Ingredient Menu view
    }

    /**
     * Navigates to the Main Menu view.
     */
    @FXML
    public void home() {
        System.out.println("Going back to the home screen."); // Log navigation
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }

    /**
     * Executes a search for ingredients based on the provided name and/or maximum ABV.
     * Validates inputs and navigates to the search results view upon success.
     */
    @FXML
    public void search() {
        // Retrieve input values from the text fields
        String name = nameField != null ? nameField.getText().trim() : ""; // Get the name input
        String abvText = maxAbvField != null ? maxAbvField.getText().trim() : ""; // Get the ABV input

        // Check if both fields are empty
        if (name.isEmpty() && abvText.isEmpty()) {
            System.out.println("Please enter a name or ABV to search for an ingredient."); // Log missing input
            return;
        }

        // Initialize the ABV filter variable
        double abv = -1; // Use -1 to indicate no ABV filter
        if (!abvText.isEmpty()) {
            try {
                abv = Double.parseDouble(abvText); // Parse the ABV input
                if (abv < 0 || abv > 100) { // Validate the ABV range
                    System.out.println("ABV must be between 0 and 100."); // Log invalid range
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ABV value! Please enter a valid number."); // Log invalid input
                return;
            }
        }

        // Perform the search based on input criteria
        if (abv == -1) { // If no ABV filter is applied
            System.out.println("Searching for ingredient with name: " + name); // Log search action
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByName(App.ingredientsTable, name)); // Search by name
        } else if (name.isEmpty()) { // If only ABV filter is applied
            System.out.println("Searching for ingredient with ABV: " + abv); // Log search action
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByABV(App.ingredientsTable, abv)); // Search by ABV
        } else { // If both name and ABV filters are applied
            System.out.println("Searching for ingredient with name: " + name + " and ABV: " + abv); // Log search action
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByNameAndABV(App.ingredientsTable, name, abv)); // Search by name and ABV
        }

        // Navigate to the search results view
        App.switchScene("/views/IngredientSearchResults");
    }

    /**
     * Sets up event handlers for the buttons to handle user interactions.
     */
    private void buttonEvents() {
        backButton.setOnAction(e -> back()); // Attach action to Back button
        homeButton.setOnAction(e -> home()); // Attach action to Home button
        searchButton.setOnAction(e -> search()); // Attach action to Search button
    }
}
