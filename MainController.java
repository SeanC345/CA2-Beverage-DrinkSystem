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
    public void initialize() {
        buttonEvents();
    }

    @FXML
    public void switchToDrinksView() {
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void switchToIngredientsView() {
        App.switchScene("/views/IngredientMenuView");
    }

    @FXML
    public void exit() {
        saveData();
        System.exit(0);
    }

    private void saveData() {
        data.DataPersistence.saveData(App.drinksTable, "drinksData.dat");
        data.DataPersistence.saveData(App.ingredientsTable, "ingredientsData.dat");
    }

    public void buttonEvents() {
        viewDrinksButton.setOnAction(e -> switchToDrinksView());
        viewIngredientsButton.setOnAction(e -> switchToIngredientsView());
        exitButton.setOnAction(e -> exit());
    }
}