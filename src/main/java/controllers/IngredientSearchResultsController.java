package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ingredient;

/**
 * Controller for the Ingredient Search Results view.
 * Displays the results of ingredient searches in a table and allows the user to delete or update ingredients.
 */
public class IngredientSearchResultsController {

    // FXML elements for the table and its columns
    @FXML
    private TableView<Ingredient> ingredientTable; // Table to display the list of ingredients
    @FXML
    private TableColumn<Ingredient, String> nameCol; // Column for ingredient names
    @FXML
    private TableColumn<Ingredient, String> descriptionCol; // Column for ingredient descriptions
    @FXML
    private TableColumn<Ingredient, Double> abvCol; // Column for ingredient ABV values

    // FXML elements for navigation and actions
    @FXML
    private Button deleteButton; // Button to delete the selected ingredient
    @FXML
    private Button updateButton; // Button to update the selected ingredient
    @FXML
    private Button backButton; // Button to navigate back to the Ingredient Menu
    @FXML
    private Button homeButton; // Button to return to the Main Menu

    /**
     * Updates the table with the current list of search results.
     * Clears the table and adds the ingredients from the search results.
     */
    public void updateTable() {
        ingredientTable.getItems().clear(); // Clear the table before adding new items
        if (App.ingredientResults == null || App.ingredientResults.size() == 0) { // Check if there are any results
            System.out.println("No ingredients available to display."); // Log if no results are found
            return;
        }

        // Add each ingredient in the search results to the table view
        for (Ingredient ingredient : App.ingredientResults) {
            ingredientTable.getItems().add(ingredient);
        }
    }

    /**
     * Initializes the controller by setting up the table columns and populating the table.
     */
    @FXML
    public void initialize() {
        // Set up columns for text properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Bind name property to name column
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description")); // Bind description property to description column
        abvCol.setCellValueFactory(new PropertyValueFactory<>("abv")); // Bind ABV property to ABV column

        // Populate the table with the current search results
        updateTable();
    }

    /**
     * Deletes the selected ingredient from the system and updates the table view.
     */
    @FXML
    public void deleteIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem(); // Get the selected ingredient
        if (selectedIngredient != null) {
            App.ingredientsTable.remove(selectedIngredient.getName()); // Remove the ingredient from the ingredients table
            updateTable(); // Refresh the table view
            System.out.println("Ingredient deleted: " + selectedIngredient.getName()); // Log the deletion
        } else {
            System.out.println("Please select an ingredient to delete."); // Log if no ingredient is selected
        }
    }

    /**
     * Navigates to the form for updating the selected ingredient.
     * Allows the user to edit the ingredient's details.
     */
    @FXML
    public void updateIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem(); // Get the selected ingredient
        if (selectedIngredient != null) {
            App.setSelectedIngredient(selectedIngredient); // Set the selected ingredient in the app context
            App.switchScene("/views/IngredientForm"); // Navigate to the Ingredient Form view
        } else {
            System.out.println("Please select an ingredient to update."); // Log if no ingredient is selected
        }
    }

    /**
     * Navigates back to the Ingredient Menu view.
     */
    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView"); // Switch to the Ingredient Menu view
    }

    /**
     * Navigates to the Main Menu view.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }
}
