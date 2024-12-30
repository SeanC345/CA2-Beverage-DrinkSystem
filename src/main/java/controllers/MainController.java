package controllers;

import com.example.ca2drinkbeveragesystem.App;

import data.DataPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the Main Menu view.
 * Provides navigation to drinks and ingredients menus, and handles application exit with data saving.
 */
public class MainController {

    // FXML elements for navigation and exit actions
    @FXML
    private Button viewDrinksButton; // Button to navigate to the Drinks Menu
    @FXML
    private Button viewIngredientsButton; // Button to navigate to the Ingredients Menu
    @FXML
    private Button exitButton; // Button to exit the application

    /**
     * Initializes the controller by setting up button event handlers.
     */
    @FXML
    public void initialize() {
        buttonEvents(); // Attach actions to buttons
    }

    /**
     * Navigates to the Drinks Menu view.
     */
    @FXML
    public void switchToDrinksView() {
        App.switchScene("/views/DrinkMenuView"); // Switch to the Drink Menu view
    }

    /**
     * Navigates to the Ingredients Menu view.
     */
    @FXML
    public void switchToIngredientsView() {
        App.switchScene("/views/IngredientMenuView"); // Switch to the Ingredient Menu view
    }

    /**
     * Exits the application after saving all data.
     */
    @FXML
    public void exit() {
        saveData(); // Save data before exiting
        System.exit(0); // Exit the application
    }

    /**
     * Saves all application data (drinks, ingredients, and recipes) to persistent storage.
     */
    public void saveData() {
        try {
            // Save drinks data
            DataPersistence.saveDrinks(App.drinksTable, "drinksData.dat");
            System.out.println("Drinks saved successfully."); // Log success for drinks

            // Save ingredients data
            DataPersistence.saveIngredients(App.ingredientsTable, "ingredientsData.dat");
            System.out.println("Ingredients saved successfully."); // Log success for ingredients

            // Save recipes data
            DataPersistence.saveRecipes(App.recipesTable, "recipesData.dat");
            System.out.println("Recipes saved successfully."); // Log success for recipes
        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage()); // Log any errors
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    /**
     * Sets up button event handlers to handle user interactions.
     */
    public void buttonEvents() {
        viewDrinksButton.setOnAction(e -> switchToDrinksView()); // Action for Drinks Menu button
        viewIngredientsButton.setOnAction(e -> switchToIngredientsView()); // Action for Ingredients Menu button
        exitButton.setOnAction(e -> exit()); // Action for Exit button
    }
}
