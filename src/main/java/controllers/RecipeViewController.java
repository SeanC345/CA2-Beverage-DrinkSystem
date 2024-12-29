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

public class RecipeViewController {

    @FXML
    private Label drinkNameLabel;
    @FXML
    private TableView<Ingredient> recipeTable;
    @FXML
    private TableColumn<Ingredient, String> nameCol;
    @FXML
    private TableColumn<Ingredient, String> descriptionCol;
    @FXML
    private TableColumn<Ingredient, String> ABVCol;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    private CustomLinkedList<Ingredient> ingredients;



    public void updateTable() {
        recipeTable.getItems().clear();
        if (ingredients == null) {
            System.out.println("No drinks available to display.");
            return;
        }

        for (Ingredient ingredient : ingredients) {
            recipeTable.getItems().add(ingredient);
        }

    }

    @FXML
    public void initialize() {
        // Build a table of ingredients from the selected drink
        ingredients = App.selectedDrink.getRecipe().getIngredients();
        // Add drink name to header
        drinkNameLabel.setText(App.selectedDrink.getName());
        // Setting up columns with each ingredient field
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ABVCol.setCellValueFactory(new PropertyValueFactory<>("abv"));

        updateTable();
    };

    @FXML
    public void back() {
        App.selectedDrink = null;
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void home() {
        App.selectedDrink = null;
        App.switchScene("/views/MainView");
    }

    @FXML
    public void buttonEvents() {
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
    }

}
