package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Drink;

import java.io.File;

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

    private String imagePath; // To store the path of the selected image

    public void initialize() {
        buttonEvents();
    }

    public void addDrink(String name, String origin, String description, String image) {
        Drink drink = new Drink(name, origin, description, image);
        App.drinksTable.put(name, drink);
    }

    @FXML
    public void submit() {
        String name = nameField.getText();
        String origin = originField.getText();
        String description = descriptionField.getText();

        if (validateFields(name, origin, description, imagePath)) {
            addDrink(name, origin, description, imagePath);
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
<<<<<<< HEAD
        App.switchScene("views/DrinkMenu");
=======
        App.switchScene("/views/DrinkMenuView");
>>>>>>> ea7a0f975e0f06049f795a5e5e67c54e88e570ab
    }

    @FXML
    public void home() {
        App.switchScene("/views/MainView");
    }

    private void buttonEvents() {
        uploadImageButton.setOnAction(e -> uploadImage());
        submitButton.setOnAction(e -> submit());
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
    }
}
