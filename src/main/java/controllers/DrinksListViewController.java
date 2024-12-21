package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import models.Drink;

public class DrinksListViewController {
    @FXML
    private TableView<Drink> drinkTable;
    @FXML
    private TableColumn<Drink, String> nameCol;
    @FXML
    private TableColumn<Drink, String> originCol;
    @FXML
    private TableColumn<Drink, String> descriptionCol;
    @FXML
    private TableColumn<Drink, Image> imageCol;

    @FXML
    private Button recipeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;


    public void updateTable() {
        drinkTable.getItems().clear();
        for (Drink drink : App.drinksTable.valueSet()) {
            drinkTable.getItems().add(drink);
        }
    }

    @FXML
    public void initialize() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        updateTable();
    }



    @FXML
    private void viewRecipe() {

        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem();
        if (selectedDrink != null) {
            App.setSelectedDrink(selectedDrink);
            App.switchScene("RecipeView");
        }
        else {
            System.out.println("Please choose a drink to view recipe");
        }
    }

    @FXML
    private void updateDrink() {
        Drink drinkToUpdate = drinkTable.getSelectionModel().getSelectedItem();
        if (drinkToUpdate != null) {
            App.setSelectedDrink(drinkToUpdate);
            App.switchScene("/views/DrinkForm");
        }
        else {
            System.out.println("Please choose a drink to update");
        }
    }

    @FXML
    private void deleteDrink() {

        Drink drinkToDelete = drinkTable.getSelectionModel().getSelectedItem();
        if (drinkToDelete != null) {
            App.drinksTable.valueSet().remove(drinkToDelete);
            updateTable();
        }
        else {
            System.out.println("Please choose a drink to delete");
        }
    }

    @FXML
    private void back() {
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    private void home() {
        App.switchScene("/views/MainView");
    }

    @FXML
    private void buttonEvents() {
        backButton.setOnAction(event -> back());
        homeButton.setOnAction(event -> home());
        recipeButton.setOnAction(event -> viewRecipe());
        deleteButton.setOnAction(event -> deleteDrink());
        updateButton.setOnAction(event -> updateDrink());
    }

}