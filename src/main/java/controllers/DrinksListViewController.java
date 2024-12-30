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

/**
 * Controller for the Drinks List view.
 * Displays all available drinks in a table and provides options to view recipes, delete, or update drinks.
 */
public class DrinksListViewController {

    // FXML elements for the table and its columns
    @FXML
    private TableView<Drink> drinkTable; // Table to display the list of drinks
    @FXML
    private TableColumn<Drink, String> nameCol; // Column for drink names
    @FXML
    private TableColumn<Drink, String> originCol; // Column for drink origins
    @FXML
    private TableColumn<Drink, String> descriptionCol; // Column for drink descriptions
    @FXML
    private TableColumn<Drink, String> imageCol; // Column for drink images

    // FXML elements for navigation and actions
    @FXML
    private Button recipeButton; // Button to view the recipe of the selected drink
    @FXML
    private Button deleteButton; // Button to delete the selected drink
    @FXML
    private Button updateButton; // Button to update the selected drink
    @FXML
    private Button backButton; // Button to navigate back to the previous menu
    @FXML
    private Button homeButton; // Button to return to the main menu

    /**
     * Updates the table with the current list of drinks from the application's drink table.
     */
    public void updateTable() {
        drinkTable.getItems().clear(); // Clear the table before adding new items
        if (App.drinksTable == null || App.drinksTable.size() == 0) { // Check if there are any drinks
            System.out.println("No drinks available to display."); // Log if no drinks are found
            return;
        }

        // Add each drink in the drinks table to the table view
        for (Drink drink : App.drinksTable.valueSet()) {
            drinkTable.getItems().add(drink);
        }
    }

    /**
     * Initializes the controller by setting up table columns and updating the table.
     */
    @FXML
    public void initialize() {
        // Set up columns for text properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Bind name property to name column
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin")); // Bind origin property to origin column
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description")); // Bind description property to description column

        // Custom cell factory for displaying images in the image column
        imageCol.setCellFactory(column -> new TableCell<Drink, String>() {
            private final ImageView imageView = new ImageView(); // ImageView for displaying images

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);

                if (empty || imageUrl == null || imageUrl.isEmpty()) {
                    setGraphic(null); // Set empty graphic if no image URL is available
                } else {
                    imageView.setImage(new Image(imageUrl, 50, 50, true, true)); // Display a scaled-down image
                    setGraphic(imageView); // Set the ImageView as the cell's graphic
                }
            }
        });

        // Bind the image URL property to the image column
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        // Populate the table with the current data
        updateTable();
    }

    /**
     * Navigates to the recipe view for the selected drink.
     */
    @FXML
    public void viewRecipe() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem(); // Get the selected drink
        if (selectedDrink != null) {
            App.setSelectedDrink(selectedDrink); // Set the selected drink in the app context
            App.switchScene("/views/RecipeView"); // Navigate to the Recipe view
        } else {
            System.out.println("Please choose a drink to view the recipe"); // Log if no drink is selected
        }
    }

    /**
     * Deletes the selected drink from the system and updates the table.
     */
    @FXML
    public void deleteDrink() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem(); // Get the selected drink
        if (selectedDrink != null) {
            App.drinksTable.remove(selectedDrink.getName()); // Remove the drink from the drinks table
            updateTable(); // Refresh the table
        } else {
            System.out.println("Please select a drink to delete"); // Log if no drink is selected
        }
    }

    /**
     * Navigates to the update form for the selected drink.
     */
    @FXML
    public void updateDrink() {
        Drink selectedDrink = drinkTable.getSelectionModel().getSelectedItem(); // Get the selected drink
        if (selectedDrink != null) {
            App.setSelectedDrink(selectedDrink); // Set the selected drink in the app context
            App.switchScene("/views/DrinkForm"); // Navigate to the Drink Form view
        } else {
            System.out.println("Please select a drink to update"); // Log if no drink is selected
        }
    }

    /**
     * Navigates back to the Drink Menu view.
     */
    @FXML
    public void back() {
        App.switchScene("/views/DrinkMenuView"); // Switch to the Drink Menu view
    }

    /**
     * Navigates back to the Main Menu view.
     */
    @FXML
    public void home() {
        App.switchScene("/views/MainView"); // Switch to the Main Menu view
    }
}
