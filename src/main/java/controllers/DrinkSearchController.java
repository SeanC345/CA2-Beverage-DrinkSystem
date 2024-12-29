package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SearchUtils;

public class DrinkSearchController {
    
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ABVField;

    @FXML
    public void initialize() {
        buttonEvents();
    }

    @FXML
    public void back() {
        System.out.println("Going back to the previous screen.");
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void home() {
        System.out.println("Going back to the home screen.");
        App.switchScene("/views/MainView");
    }

    @FXML
    public void search() {
        String name = nameField != null ? nameField.getText().trim() : "";
        String abvText = ABVField != null ? ABVField.getText().trim() : "";

        // Check if both fields are empty
        if (name.isEmpty() && abvText.isEmpty()) {
            System.out.println("Please enter a name or ABV to search for a drink.");
            return;
        }

        // Parse ABV input if provided
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
            System.out.println("Searching for drink with name: " + name);
            App.setDrinkResults(SearchUtils.searchDrinksByName(App.drinksTable, name));
        } else if (name.isEmpty()) { // Only ABV filter
            System.out.println("Searching for drink with ABV: " + abv);
            App.setDrinkResults(SearchUtils.searchDrinksByABV(App.drinksTable, abv));
        } else { // Both name and ABV filter
            System.out.println("Searching for drink with name: " + name + " and ABV: " + abv);
            App.setDrinkResults(SearchUtils.searchDrinksByNameAndABV(App.drinksTable, name, abv));
        }

        // Navigate to the search results view
        App.switchScene("/views/DrinkSearchResults");
    }


    private void buttonEvents() {
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
        searchButton.setOnAction(e -> search());
    }

}
