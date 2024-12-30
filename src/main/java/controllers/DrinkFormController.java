package controllers;

import java.io.File;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Drink;
import models.Ingredient;
import models.Recipe;
import utils.CustomLinkedList;

/**
 * Controller for the Drink Form view.
 * Handles user input for creating new drinks, including setting drink details,
 * selecting ingredients, and uploading an image.
 */
public class DrinkFormController {

    // FXML elements
    @FXML
    private TextField nameField; // Input for drink name
    @FXML
    private TextField originField; // Input for drink origin
    @FXML
    private TextField descriptionField; // Input for drink description
    @FXML
    private ImageView imageView; // Displays the uploaded drink image
    @FXML
    private Button uploadImageButton; // Button for uploading an image
    @FXML
    private Button submitButton; // Button to submit the form
    @FXML
    private Button backButton; // Button to go back to the previous menu
    @FXML
    private Button homeButton; // Button to return to the home screen
    @FXML
    private ComboBox<String> ingredientBox; // Dropdown for selecting ingredients
    @FXML
    private Button addIngredientButton; // Button to add a selected ingredient to the drink
    @FXML
    private TextField quantityField; // Input for the quantity of ingredients in the recipe

    // Path of the uploaded image file
    private String imagePath;

    // List of ingredients chosen for the recipe
    private CustomLinkedList<Ingredient> chosenIngredients;

    /**
     * Initializes the controller by loading ingredients into the dropdown
     * and setting up button event handlers.
     */
    public void initialize() {
        loadIngredients(); // Load all available ingredients into the dropdown
        chosenIngredients = new CustomLinkedList<>(); // Initialize the list of chosen ingredients
        buttonEvents(); // Set up event handlers for buttons
    }

    /**
     * Loads all ingredients from the application data and adds them to the dropdown.
     */
    public void loadIngredients() {
        for (Ingredient ingredient : App.ingredientsTable.valueSet()) {
            ingredientBox.getItems().add(ingredient.getName()); // Add each ingredient's name to the dropdown
        }
    }

    /**
     * Gets the ingredient currently selected in the dropdown.
     * @return The selected ingredient object.
     */
    public Ingredient getSelectedIngredient() {
        String name = ingredientBox.getValue(); // Get the selected ingredient name
        return App.ingredientsTable.get(name); // Retrieve the corresponding ingredient object
    }

    /**
     * Adds the currently selected ingredient to the recipe list.
     */
    public void addIngredient() {
        Ingredient ingredient = getSelectedIngredient(); // Get the selected ingredient
        chosenIngredients.add(ingredient); // Add it to the recipe list
        System.out.println("Ingredient added: " + ingredient.getName()); // Log the action
    }

    /**
     * Adds a new drink to the drinks table with the given details.
     * @param name The name of the drink.
     * @param origin The origin of the drink.
     * @param description A description of the drink.
     * @param image The URL of the drink's image.
     * @param recipe The recipe associated with the drink.
     */
    public void addDrink(String name, String origin, String description, String image, Recipe recipe) {
        Drink drink = new Drink(name, origin, description, image, recipe); // Create a new drink object
        App.drinksTable.put(name, drink); // Add it to the drinks table
    }

    /**
     * Handles the submission of the form to create a new drink.
     * Validates inputs, creates the drink, and navigates back to the previous menu.
     */
    @FXML
    public void submit() {
        String name = nameField.getText(); // Get the drink name
        String origin = originField.getText(); // Get the drink origin
        String description = descriptionField.getText(); // Get the drink description

        double quantity = Double.parseDouble(quantityField.getText()); // Parse the quantity as a double
        Recipe recipe = new Recipe(chosenIngredients, quantity); // Create a new recipe with the chosen ingredients

        if (validateFields(name, origin, description, imagePath)) { // Validate inputs
            addDrink(name, origin, description, imagePath, recipe); // Add the drink to the system
            System.out.println("Drink added: " + name); // Log the success
            back(); // Navigate back to the drink menu
        }
    }

    /**
     * Validates the input fields to ensure all required data is provided.
     * @param name The name of the drink.
     * @param origin The origin of the drink.
     * @param description The description of the drink.
     * @param image The image URL of the drink.
     * @return True if all fields are valid, false otherwise.
     */
    private boolean validateFields(String name, String origin, String description, String image) {
        if (name.isEmpty() || origin.isEmpty() || description.isEmpty() || image == null || image.isEmpty()) {
            System.out.println("All fields, including image, must be filled!"); // Log missing fields
            return false;
        }
        return true;
    }

    /**
     * Handles the image upload functionality, allowing the user to select a file.
     */
    @FXML
    public void uploadImage() {
        FileChooser fileChooser = new FileChooser(); // Create a file chooser
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        ); // Limit selection to image files
        File selectedFile = fileChooser.showOpenDialog(null); // Open the file dialog

        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString(); // Get the file path
            Image image = new Image(imagePath); // Load the image
            imageView.setImage(image); // Display the image in the ImageView
            System.out.println("Image selected: " + imagePath); // Log the success
        } else {
            System.out.println("No image selected."); // Log the cancellation
        }
    }

    /**
     * Navigates back to the drink menu view.
     */
    @FXML
    public void back() {
        App.switchScene("/views/DrinkMenuView");
    }

    /**
     * Navigates back to the main menu view.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }

    /**
     * Sets up event handlers for all buttons in the form.
     */
    private void buttonEvents() {
        addIngredientButton.setOnAction(e -> addIngredient()); // Add ingredient button
        uploadImageButton.setOnAction(e -> uploadImage()); // Upload image button
        submitButton.setOnAction(e -> submit()); // Submit button
        backButton.setOnAction(e -> back()); // Back button
        homeButton.setOnAction(e -> home()); // Home button
    }
}
