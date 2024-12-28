package controllers;

import com.example.ca2drinkbeveragesystem.App;

public class IngredientMenuController {

    /**
     * Navigate to the view for displaying all ingredients.
     */
    public void viewIngredients() {
        App.switchScene("/views/IngredientListView"); // Ensure this FXML exists or implement it
    }

    /**
     * Navigate to the form for adding a new ingredient.
     */
    public void addIngredient() {
        App.switchScene("/views/IngredientForm");
    }

    /**
     * Navigate to the search feature for ingredients.
     */
    public void searchIngredients() {
        App.switchScene("/views/IngredientSearchView"); // Ensure this FXML exists or implement it
    }


    /**
     * Navigate back to the main menu.
     */
    public void home() {
        App.switchScene("/views/MainView");
    }
}

