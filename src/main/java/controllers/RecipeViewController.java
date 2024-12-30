package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ingredient;
import utils.CustomLinkedList;

/**
 * Controller for the Recipe View.
 * Displays the recipe of the selected drink, including its ingredients and their details.
 */
public class RecipeViewController {

    // FXML elements for the recipe display and navigation
    @FXML
    private Label drinkNameLabel; // Label to display the name of the selected drink
    @FXML
    private Label quantityLabel; // Label to display the quantity of the selected drink
    @FXML
    private TableView<Ingredient> recipeTable; // Table to display the list of ingredients
    @FXML
    private TableColumn<Ingredient, String> nameCol; // Column for ingredient names
    @FXML
    private TableColumn<Ingredient, String> descriptionCol; // Column for ingredient descriptions
    @FXML
    private TableColumn<Ingredient, String> ABVCol; // Column for ingredient ABV values
    @FXML
    private Button backButton; // Button to navigate back to the previous screen
    @FXML
    private Button homeButton; // Button to return to the main menu

    // List of ingredients used in the recipe
    private CustomLinkedList<Ingredient> ingredients;

    /**
     * Updates the table with the ingredients of the selected drink.
     * Clears the table and adds the ingredients from the drink's recipe.
     */
    public void updateTable() {
        recipeTable.getItems().clear(); // Clear the table before adding new items
        if (ingredients == null) { // Check if there are ingredients available
            System.out.println("No drinks available to display."); // Log if no ingredients are found
            return;
        }

        // Add each ingredient to the table
        for (Ingredient ingredient : ingredients) {
            recipeTable.getItems().add(ingredient);
        }
    }

    /**
     * Initializes the controller by populating the table and setting up columns.
     * Retrieves the selected drink's recipe and updates the view.
     */
    @FXML
    public void initialize() {
        // Get the list of ingredients from the selected drink's recipe
        ingredients = App.selectedDrink.getRecipe().getIngredients();

        // Display the name of the selected drink
        drinkNameLabel.setText(App.selectedDrink.getName());
        // Display the quantity of the selected drink
        quantityLabel.setText("Quantity " + App.selectedDrink.getRecipe().getQuantity() + " ml");
        // Bind table columns to the ingredient properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Bind name property
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description")); // Bind description property
        ABVCol.setCellValueFactory(new PropertyValueFactory<>("abv")); // Bind ABV property

        // Populate the table with ingredients
        updateTable();
    }

    /**
     * Navigates back to the Drink Menu view and clears the selected drink.
     */
    @FXML
    public void back() {
        App.selectedDrink = null; // Clear the selected drink
        App.switchScene("/views/DrinkMenuView"); // Switch to the Drink Menu view
    }

    /**
     * Navigates to the Main Menu view and clears the selected drink.
     */
    @FXML
    public void home() {
        App.selectedDrink = null; // Clear the selected drink
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }

    /**
     * Sets up button event handlers for navigation.
     */
    @FXML
    public void buttonEvents() {
        backButton.setOnAction(e -> back()); // Attach action to Back button
        homeButton.setOnAction(e -> home()); // Attach action to Home button
    }
}
