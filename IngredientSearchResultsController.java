package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ingredient;

public class IngredientSearchResultsController {

    @FXML
    private TableView<Ingredient> ingredientTable;
    @FXML
    private TableColumn<Ingredient, String> nameCol;
    @FXML
    private TableColumn<Ingredient, String> descriptionCol;
    @FXML
    private TableColumn<Ingredient, Double> abvCol;

    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    public void updateTable() {
        ingredientTable.getItems().clear();
        if (App.ingredientResults == null || App.ingredientResults.size() == 0) {
            System.out.println("No ingredients available to display.");
            return;
        }

        for (Ingredient ingredient : App.ingredientResults) {
            ingredientTable.getItems().add(ingredient);
        }
    }

    @FXML
    public void initialize() {
        // Setting up columns for text properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        abvCol.setCellValueFactory(new PropertyValueFactory<>("abv"));

        // Update the table with current data
        updateTable();
    }

    @FXML
    public void deleteIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient != null) {
            App.ingredientsTable.remove(selectedIngredient.getName());
            updateTable();
            System.out.println("Ingredient deleted: " + selectedIngredient.getName());
        } else {
            System.out.println("Please select an ingredient to delete.");
        }
    }

    @FXML
    public void updateIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient != null) {
            App.setSelectedIngredient(selectedIngredient);
            App.switchScene("/views/IngredientForm");
        } else {
            System.out.println("Please select an ingredient to update.");
        }
    }

    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView");
    }

    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }
}
