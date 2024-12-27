package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Drink;

public class DrinkSearchResultsController {
    @FXML
    private TableView<Drink> drinkTable;
    @FXML
    private TableColumn<Drink, String> nameCol;
    @FXML
    private TableColumn<Drink, String> originCol;
    @FXML
    private TableColumn<Drink, String> descriptionCol;
    @FXML
    private TableColumn<Drink, String> imageCol;

    @FXML
    private Button recipeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    public void updateTable() {
        drinkTable.getItems().clear();
        if (App.drinkResults == null || App.drinkResults.size() == 0) {
            System.out.println("No drinks available to display.");
            return;
        }

        for (Drink drink : App.drinkResults) {
            drinkTable.getItems().add(drink);
        }
    }

    @FXML
    public void initialize() {
        // Setting up columns for text properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Custom cell factory for image column
        imageCol.setCellFactory(column -> new TableCell<Drink, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);

                if (empty || imageUrl == null || imageUrl.isEmpty()) {
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(imageUrl, 50, 50, true, true)); // 50x50 for thumbnail size
                    setGraphic(imageView);
                }
            }
        });

        // Binding the image URL property to the column
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        // Update the table with current data
        updateTable();
    }

    @FXML
    public void viewRecipe() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem();
        if (selectedDrink != null) {
            App.setSelectedDrink(selectedDrink);
            App.switchScene("/views/RecipeView");
        } else {
            System.out.println("Please choose a drink to view the recipe");
        }
    }

    @FXML
    public void deleteDrink() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem();
        if (selectedDrink != null) {
            App.drinksTable.remove(selectedDrink.getName());
            updateTable();
        } else {
            System.out.println("Please select a drink to delete");
        }
    }

    @FXML
    public void updateDrink() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem();
        if (selectedDrink != null) {
            App.setSelectedDrink(selectedDrink);
            App.switchScene("/views/DrinkForm");
        } else {
            System.out.println("Please select a drink to update");
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
}