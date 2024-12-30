package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Ingredient;

/**
 * Controller for the Ingredient Form view.
 * Allows users to add new ingredients to the system by filling out a form.
 */
public class IngredientFormController {

    // FXML elements for user input and navigation
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
     * Initializes the controller by setting up button events.
     */
    public void initialize() {
        buttonEvents(); // Attach event handlers to buttons
    }

    /**
     * Adds a new ingredient to the ingredients table.
     *
     * @param name        Name of the ingredient
     * @param description Description of the ingredient
     * @param abv         Alcohol by Volume (ABV) of the ingredient
     */
    public void addIngredient(String name, String description, double abv) {
        Ingredient ingredient = new Ingredient(name, description, abv); // Create a new ingredient
        App.ingredientsTable.put(name, ingredient); // Add the ingredient to the ingredients table
    }

    /**
     * Handles the submission of the form to add a new ingredient.
     * Validates the inputs, adds the ingredient, and clears the fields upon success.
     */
    @FXML
    public void submit() {
        String name = nameField.getText(); // Get the ingredient name
        String description = descriptionField.getText(); // Get the ingredient description
        double abv;

        // Parse the ABV input and handle invalid formats
        try {
            abv = Double.parseDouble(abvField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ABV value! Please enter a number."); // Log invalid input
            return;
        }

        // Validate the fields and add the ingredient if valid
        if (validateFields(name, description, abv)) {
            addIngredient(name, description, abv); // Add the ingredient to the system
            System.out.println("Ingredient added successfully: " + name); // Log success
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
            System.out.println("Name and Description must be filled!"); // Log missing fields
            return false;
        }
        if (abv < 0 || abv > 100) {
            System.out.println("ABV must be between 0 and 100!"); // Log invalid ABV range
            return false;
        }
        return true;
    }

    /**
     * Clears the input fields to reset the form.
     */
    private void clearFields() {
        nameField.clear(); // Clear the name field
        descriptionField.clear(); // Clear the description field
        abvField.clear(); // Clear the ABV field
    }

    /**
     * Handles the action to navigate back to the previous screen.
     */
    @FXML
    public void back() {
        App.switchScene("/views/IngredientMenuView"); // Navigate to the Ingredient Menu view
    }

    /**
     * Handles the action to navigate to the main menu.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView"); // Navigate to the Main Menu view
    }

    /**
     * Sets up button events for handling user interactions.
     */
    private void buttonEvents() {
        submitButton.setOnAction(e -> submit()); // Set action for the Submit button
        backButton.setOnAction(e -> back()); // Set action for the Back button
        homeButton.setOnAction(e -> home()); // Set action for the Home button
    }
}
