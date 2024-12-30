package controllers;

import com.example.ca2drinkbeveragesystem.App;

/**
 * Controller for the Ingredient Menu view.
 * Provides navigation options to view, add, and search ingredients, as well as return to the main menu.
 */
public class IngredientMenuController {

    /**
     * Navigates to the view for displaying all ingredients.
     * Ensures that the user can see a list of all available ingredients in the system.
     */
    public void viewIngredients() {
        App.switchScene("/views/IngredientListView"); // Switch to the Ingredient List view
    }

    /**
     * Navigates to the form for adding a new ingredient.
     * Allows the user to input details for creating a new ingredient.
     */
    public void addIngredient() {
        App.switchScene("/views/IngredientForm"); // Switch to the Ingredient Form view
    }

    /**
     * Navigates to the search feature for ingredients.
     * Enables the user to search for specific ingredients based on various criteria.
     */
    public void searchIngredients() {
        App.switchScene("/views/IngredientSearchView"); // Switch to the Ingredient Search view
    }

    /**
     * Navigates back to the main menu.
     * Returns the user to the application's main navigation screen.
     */
    public void home() {
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }
}

