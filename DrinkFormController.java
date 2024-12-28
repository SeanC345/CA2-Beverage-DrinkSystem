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

public class DrinkFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField originField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ImageView imageView;
    @FXML
    private Button uploadImageButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private ComboBox<String> ingredientBox;
    @FXML
    private Button addIngredientButton;
    @FXML
    private TextField quantityField;

    private String imagePath; // To store the path of the selected image
    private CustomLinkedList<Ingredient> chosenIngredients;

    public void initialize() {
        loadIngredients();
        chosenIngredients = new CustomLinkedList<>();
        buttonEvents();
    }


    public void loadIngredients() {
        for (Ingredient ingredient : App.ingredientsTable.valueSet()) {
            ingredientBox.getItems().add(ingredient.getName());
        }
    }

    public Ingredient getSelectedIngredient() {
        String name = ingredientBox.getValue();
        return App.ingredientsTable.get(name);
    }

    public void addIngredient() {
        Ingredient ingredient = getSelectedIngredient();
        chosenIngredients.add(ingredient);
        System.out.println("Ingredient added: " + ingredient.getName());
    }

    public void addDrink(String name, String origin, String description, String image, Recipe recipe) {
        Drink drink = new Drink(name, origin, description, image, recipe);
        App.drinksTable.put(name, drink);
    }

    @FXML
    public void submit() {
        String name = nameField.getText();
        String origin = originField.getText();
        String description = descriptionField.getText();

        double quantity = Double.parseDouble(quantityField.getText());
        Recipe recipe = new Recipe(chosenIngredients, quantity);

        if (validateFields(name, origin, description, imagePath)) {
            addDrink(name, origin, description, imagePath, recipe);
            System.out.println("Drink added: " + name);
            back();
        }
    }

    private boolean validateFields(String name, String origin, String description, String image) {
        if (name.isEmpty() || origin.isEmpty() || description.isEmpty() || image == null || image.isEmpty()) {
            System.out.println("All fields, including image, must be filled!");
            return false;
        }
        return true;
    }

    @FXML
    public void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            imageView.setImage(image);
            System.out.println("Image selected: " + imagePath);
        } else {
            System.out.println("No image selected.");
        }
    }

    @FXML
    public void back() {
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }

    private void buttonEvents() {
        addIngredientButton.setOnAction(e -> addIngredient());
        uploadImageButton.setOnAction(e -> uploadImage());
        submitButton.setOnAction(e -> submit());
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
    }
}
