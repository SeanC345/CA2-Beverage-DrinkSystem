package controllers;

import com.example.ca2drinkbeveragesystem.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ingredient;

public class IngredientListViewController {

    @FXML
    private TableView<Ingredient> ingredientTable;
    @FXML
    private TableColumn<Ingredient, String> nameColumn;
    @FXML
    private TableColumn<Ingredient, String> descriptionColumn;
    @FXML
    private TableColumn<Ingredient, Double> abvColumn;

    /**
     * Initialize the controller by setting up the table and loading data.
     */
    @FXML
    public void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        abvColumn.setCellValueFactory(new PropertyValueFactory<>("abv"));

        // Populate the table
        loadIngredients();
    }

    /**
     * Loads all ingredients into the table from the ingredients table.
     */
    private void loadIngredients() {
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(App.ingredientsTable.valueSet());
        ingredientTable.setItems(ingredients);
    }

    /**
     * Deletes the selected ingredient from the table and updates the view.
     */
    @FXML
    public void deleteIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient != null) {
            App.ingredientsTable.remove(selectedIngredient.getName());
            loadIngredients();
            System.out.println("Deleted ingredient: " + selectedIngredient.getName());
        } else {
            System.out.println("No ingredient selected.");
        }
    }

    /**
     * Navigates back to the previous screen.
     */
    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView");
    }

    /**
     * Navigates to the main menu.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }
}

