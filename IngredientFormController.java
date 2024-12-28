package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Ingredient;

public class IngredientFormController {
    @FXML
    private TextField nameField;           // Input field for the ingredient name
    @FXML
    private TextField descriptionField;    // Input field for the ingredient description
    @FXML
    private TextField abvField;            // Input field for the ingredient ABV
    @FXML
    private Button submitButton;           // Button to submit the form
    @FXML
    private Button backButton;             // Button to go back to the previous screen
    @FXML
    private Button homeButton;             // Button to return to the main menu

    /**
     * Initialize the controller by setting up button events.
     */
    public void initialize() {
        buttonEvents();
    }

    /**
     * Adds a new ingredient to the ingredients table.
     *
     * @param name        Name of the ingredient
     * @param description Description of the ingredient
     * @param abv         Alcohol by Volume (ABV) of the ingredient
     */
    public void addIngredient(String name, String description, double abv) {
        Ingredient ingredient = new Ingredient(name, description, abv);
        App.ingredientsTable.put(name, ingredient);
    }

    /**
     * Handles the submission of the form.
     */
    @FXML
    public void submit() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double abv;

        try {
            abv = Double.parseDouble(abvField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ABV value! Please enter a number.");
            return;
        }

        if (validateFields(name, description, abv)) {
            addIngredient(name, description, abv);
            System.out.println("Ingredient added successfully: " + name);
            clearFields(); // Clear fields after successful submission
        }
    }

    /**
     * Validates the input fields to ensure they are correctly filled.
     *
     * @param name        Name of the ingredient
     * @param description Description of the ingredient
     * @param abv         Alcohol by Volume (ABV) of the ingredient
     * @return True if all fields are valid, false otherwise
     */
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

    /**
     * Clears the input fields.
     */
    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        abvField.clear();
    }

    /**
     * Handles the action to navigate back to the previous screen.
     */
    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView");
    }

    /**
     * Handles the action to navigate to the main menu.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }

    /**
     * Sets up button events for handling user interactions.
     */
    private void buttonEvents() {
        submitButton.setOnAction(e -> submit());
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
    }
}
