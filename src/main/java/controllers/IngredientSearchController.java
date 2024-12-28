package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SearchUtils;

public class IngredientSearchController {

    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField maxAbvField; // Updated to match fx:id in FXML

    @FXML
    public void initialize() {
        buttonEvents();
    }

    @FXML
    public void back() {
        System.out.println("Going back to the previous screen.");
        App.switchScene("/views/IngredientMenuView");
    }

    @FXML
    public void home() {
        System.out.println("Going back to the home screen.");
        App.switchScene("/views/MainView");
    }

    @FXML
    public void search() {
        // Retrieve input values from the text fields
        String name = nameField != null ? nameField.getText().trim() : "";
        String abvText = maxAbvField != null ? maxAbvField.getText().trim() : ""; // Updated field reference

        // Check if both fields are empty
        if (name.isEmpty() && abvText.isEmpty()) {
            System.out.println("Please enter a name or ABV to search for an ingredient.");
            return;
        }

        // Initialize variables for filtering
        double abv = -1; // Use -1 to indicate no ABV filter
        if (!abvText.isEmpty()) {
            try {
                abv = Double.parseDouble(abvText);
                if (abv < 0 || abv > 100) {
                    System.out.println("ABV must be between 0 and 100.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ABV value! Please enter a valid number.");
                return;
            }
        }

        // Perform the search
        if (abv == -1) { // No ABV filter
            System.out.println("Searching for ingredient with name: " + name);
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByName(App.ingredientsTable, name));
        } else if (name.isEmpty()) { // Only ABV filter
            System.out.println("Searching for ingredient with ABV: " + abv);
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByABV(App.ingredientsTable, abv));
        } else { // Both name and ABV filter
            System.out.println("Searching for ingredient with name: " + name + " and ABV: " + abv);
            App.setSelectedIngredientResults(SearchUtils.searchIngredientsByNameAndABV(App.ingredientsTable, name, abv));
        }

        // Navigate to the search results view
        App.switchScene("/views/IngredientSearchResults");
    }

    private void buttonEvents() {
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
        searchButton.setOnAction(e -> search());
    }
}
