package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DrinkMenuController {

    @FXML
    private Button addDrinkButton;
    @FXML
    private Button viewDrinksButton;
    @FXML
    private Button searchDrinkButton;
    @FXML
    private Button homeButton;

    @FXML
    public void initialize() {
        // Code to initialize the controller
    }
    
    @FXML
    public void addDrink() {
        App.selectedDrink = null;
        App.switchScene("/views/DrinkForm");
    }

    @FXML
    public void viewDrinks() {
        App.switchScene("/views/DrinksListView");
    }

    @FXML
    public void searchDrink() {
        App.switchScene("/views/SearchDrink");
    }

    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }

    @FXML
    public void buttonEvents() {
        addDrinkButton.setOnAction(e -> addDrink());
        // deleteDrinkButton.setOnAction(e -> deleteDrink());
        // updateDrinkButton.setOnAction(e -> updateDrink());
        viewDrinksButton.setOnAction(e -> viewDrinks());
        searchDrinkButton.setOnAction(e -> searchDrink());
        homeButton.setOnAction(e -> home());
    }
}
