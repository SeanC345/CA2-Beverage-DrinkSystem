package controllers;

import com.example.ca2drinkbeveragesystem.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ingredient;

/**
 * Controller for the Ingredient List view.
 * Displays a list of all ingredients in a table and allows the user to delete ingredients.
 */
public class IngredientListViewController {

    // FXML elements for the table and its columns
    @FXML
    private TableView<Ingredient> ingredientTable; // Table to display the list of ingredients
    @FXML
    private TableColumn<Ingredient, String> nameColumn; // Column for ingredient names
    @FXML
    private TableColumn<Ingredient, String> descriptionColumn; // Column for ingredient descriptions
    @FXML
    private TableColumn<Ingredient, Double> abvColumn; // Column for ingredient ABV values

    /**
     * Initializes the controller by setting up table columns and loading data into the table.
     */
    @FXML
    public void initialize() {
        // Bind table columns to the properties of the Ingredient class
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Bind name property
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description")); // Bind description property
        abvColumn.setCellValueFactory(new PropertyValueFactory<>("abv")); // Bind ABV property

        // Populate the table with ingredients
        loadIngredients();
    }

    /**
     * Loads all ingredients from the application's ingredient table into the table view.
     */
    private void loadIngredients() {
        // Convert the set of ingredients into an observable list and set it to the table
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(App.ingredientsTable.valueSet());
        ingredientTable.setItems(ingredients); // Update the table with the list of ingredients
    }

    /**
     * Deletes the selected ingredient from the system and updates the table view.
     */
    @FXML
    public void deleteIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem(); // Get the selected ingredient
        if (selectedIngredient != null) {
            App.ingredientsTable.remove(selectedIngredient.getName()); // Remove the ingredient from the ingredients table
            loadIngredients(); // Refresh the table view
            System.out.println("Deleted ingredient: " + selectedIngredient.getName()); // Log the deletion
        } else {
            System.out.println("No ingredient selected."); // Log if no ingredient is selected
        }
    }

    /**
     * Navigates back to the previous screen.
     */
    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView"); // Switch to the Ingredient Menu view
    }

    /**
     * Navigates to the main menu.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }
}
