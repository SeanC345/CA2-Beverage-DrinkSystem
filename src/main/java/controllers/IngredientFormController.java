package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Ingredient;

public class IngredientFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField abvField;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    public void initialize() {
        buttonEvents();
    }

    public void addIngredient(String name, String description, double abv) {
        Ingredient ingredient = new Ingredient(name, description, abv);
        App.ingredientsTable.put(name, ingredient);
    }

    @FXML
    public void submit() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double abv;

        try {
            abv = Double.parseDouble(abvField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ABV value!");
            return;
        }

        if (validateFields(name, description, abv)) {
            addIngredient(name, description, abv);
            back();
        }
    }

    private boolean validateFields(String name, String description, double abv) {
        if (name.isEmpty() || description.isEmpty()) {
            System.out.println("Name and Description must be filled!");
            return false;
        }
        if (abv < 0 || abv > 100) {
            System.out.println("ABV must be between 0 and 100!");
            return false;
        }
        return true;
    }

    @FXML
    public void back() {
        App.switchScene("/views/MainView");
    }

    public void buttonEvents() {
        submitButton.setOnAction(e -> submit());
        backButton.setOnAction(e -> back());
    }   
}