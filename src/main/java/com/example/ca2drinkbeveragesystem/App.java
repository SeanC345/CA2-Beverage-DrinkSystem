package com.example.ca2drinkbeveragesystem;

import java.io.IOException;
import java.io.Serializable;

import data.CustomHashTable;
import data.DataPersistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Drink;
import models.Ingredient;
import models.Recipe;
import utils.CustomLinkedList;

/**
 * Main application class for the Beverage System.
 * Manages the initialization of data and GUI, and serves as the entry point for the program.
 */
public class App extends Application implements Serializable {

    // JavaFX scene to manage UI views
    private static Scene scene;

    // Static hash tables for storing drinks, ingredients, and recipes
    public static CustomHashTable<String, Drink> drinksTable = new CustomHashTable<>(1000);
    public static CustomHashTable<String, Ingredient> ingredientsTable = new CustomHashTable<>(1000);
    public static CustomHashTable<String, Recipe> recipesTable = new CustomHashTable<>(1000); // Added for recipes

    // Variables to hold selected drink and ingredient during navigation
    public static Drink selectedDrink;
    public static Ingredient selectedIngredient;

    // Custom linked lists for storing search results
    public static CustomLinkedList<Drink> drinkResults;
    public static CustomLinkedList<Ingredient> ingredientResults;

    /**
     * Main method that serves as the entry point of the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(); // Launches the JavaFX application
    }

    /**
     * Initializes and displays the primary JavaFX stage with the main view.
     * @param stage The primary stage for this application.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/MainView.fxml"));
        scene = new Scene(fxmlLoader.load(), 1000, 800); // Sets the scene size
        stage.setResizable(false); // Disables resizing of the window
        stage.setTitle("Beverage System"); // Sets the title of the application
        stage.setScene(scene); // Attaches the scene to the stage
        stage.show(); // Displays the stage
        load(); // Loads data from persistent storage
    }

    /**
     * Updates the root node of the scene to switch between different views.
     * @param fxml The FXML file to load as the new root.
     * @throws IOException if the FXML file cannot be loaded.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml)); // Updates the root node
    }

    /**
     * Loads an FXML file and returns the corresponding parent node.
     * @param fxml The FXML file to load.
     * @return The parent node defined in the FXML file.
     * @throws IOException if the FXML file cannot be loaded.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println("Loading FXML: " + fxml); // Logs the FXML file being loaded
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load(); // Loads and returns the FXML content
    }

    /**
     * Switches the current scene to a new FXML file.
     * @param fxmlFile The FXML file to load as the new scene.
     */
    public static void switchScene(String fxmlFile) {
        try {
            setRoot(fxmlFile); // Calls setRoot to update the scene
        } catch (IOException e) {
            e.printStackTrace(); // Prints the stack trace if loading fails
        }
    }

    /**
     * Sets the currently selected drink for navigation purposes.
     * @param drink The drink to set as selected.
     */
    public static void setSelectedDrink(Drink drink) {
        selectedDrink = drink;
    }

    /**
     * Sets the search results for drinks.
     * @param results The linked list of drinks resulting from a search.
     */
    public static void setDrinkResults(CustomLinkedList<Drink> results) {
        drinkResults = results;
    }

    /**
     * Sets the currently selected ingredient for navigation purposes.
     * @param ingredient The ingredient to set as selected.
     */
    public static void setSelectedIngredient(Ingredient ingredient) {
        selectedIngredient = ingredient;
    }

    /**
     * Gets the currently selected ingredient.
     * @return The selected ingredient.
     */
    public static Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }

    /**
     * Sets the search results for ingredients.
     * @param results The linked list of ingredients resulting from a search.
     */
    public static void setSelectedIngredientResults(CustomLinkedList<Ingredient> results) {
        ingredientResults = results;
    }

    /**
     * Loads data from persistent storage and restores parent-child relationships between drinks and recipes.
     */
    public static void load() {
        // Load drinks, ingredients, and recipes from their respective data files
        DataPersistence.loadDrinks(drinksTable, "drinksData.dat");
        DataPersistence.loadIngredients(ingredientsTable, "ingredientsData.dat");
        DataPersistence.loadRecipes(recipesTable, "recipesData.dat");

        // Restore parentDrink references in recipes
        for (String drinkName : drinksTable.keySet()) {
            Drink drink = drinksTable.get(drinkName); // Retrieve the drink
            Recipe recipe = recipesTable.get(drinkName); // Retrieve the associated recipe
            if (recipe != null) {
                drink.setRecipe(recipe); // Link the recipe to the drink
                recipe.setParentDrink(drink); // Link the drink as the recipe's parent
            }
        }
    }
}
