package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Drink;


public class DrinkFormController {
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField originField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField imageField;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    public void initialize() {
        // Code to initialize the controller
    }

    public void addDrink(String name, String origin, String description, String image) {
        Drink drink = new Drink(name, origin, description, image);
        App.drinksTable.put(name, drink);
    }

    public void submit() {
        String name = nameField.getText();
        String origin = originField.getText();
        String description = descriptionField.getText();
        String image = imageField.getText();

        addDrink(name, origin, description, image);
        back();
    }

    public void back() {
        App.switchScene("views/DrinkMenu");
    }

    public void home() {
        App.switchScene("/views/MainView");
    }

    public void buttonEvents() {
        submitButton.setOnAction(e -> submit());
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
    }
}
