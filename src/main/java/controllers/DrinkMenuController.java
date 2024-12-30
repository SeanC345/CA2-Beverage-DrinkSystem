package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the Drink Menu view.
 * Provides navigation options for adding drinks, viewing the drinks list,
 * searching for drinks, and returning to the home screen.
 */
public class DrinkMenuController {

    // FXML elements
    @FXML
    private Button addDrinkButton; // Button to navigate to the Add Drink form
    @FXML
    private Button viewDrinksButton; // Button to view the list of drinks
    @FXML
    private Button searchDrinkButton; // Button to search for drinks
    @FXML
    private Button homeButton; // Button to return to the main menu

    /**
     * Initializes the controller by setting up button event handlers.
     */
    @FXML
    public void initialize() {
        buttonEvents(); // Configure button actions
    }

    /**
     * Navigates to the Add Drink form.
     */
    @FXML
    public void addDrink() {
        App.switchScene("/views/DrinkForm"); // Switches the scene to the Drink Form view
    }

    /**
     * Navigates to the Drinks List view.
     */
    @FXML
    public void viewDrinks() {
        App.switchScene("/views/DrinksListView"); // Switches the scene to the Drinks List view
    }

    /**
     * Navigates to the Drink Search view.
     */
    @FXML
    public void searchDrink() {
        App.switchScene("/views/DrinkSearchView"); // Switches the scene to the Drink Search view
    }

    /**
     * Navigates back to the main menu view.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView"); // Switches the scene to the Main View
    }

    /**
     * Sets up event handlers for all buttons in the menu.
     */
    public void buttonEvents() {
        addDrinkButton.setOnAction(e -> addDrink()); // Add Drink button action
        viewDrinksButton.setOnAction(e -> viewDrinks()); // View Drinks button action
        searchDrinkButton.setOnAction(e -> searchDrink()); // Search Drink button action
        homeButton.setOnAction(e -> home()); // Home button action
    }
}
