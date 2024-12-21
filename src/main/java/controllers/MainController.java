package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button viewDrinksButton;
    @FXML
    private Button viewIngredientsButton;
    @FXML
    private Button exitButton;

    @FXML
    public void exit() {
        System.exit(0);
        save();
    }

    @FXML
    public void save() {
        // Code to save data
    }

    @FXML
    public void load() {
        // Code to load data
    }

    @FXML
    public void initialize() {
        load();
    }

    @FXML
    public void switchToDrinksView() {
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void switchToIngredientsView() {
        App.switchScene("/views/IngredientsView");
    }


    @FXML
    public void buttonEvents() {
        viewDrinksButton.setOnAction(e -> switchToDrinksView());
        viewIngredientsButton.setOnAction(e -> switchToIngredientsView());
        exitButton.setOnAction(e -> exit());
    }
}