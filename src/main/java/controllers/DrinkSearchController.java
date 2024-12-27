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
        App.switchScene("DrinkMenuView");
    }

    @FXML
    public void home() {
        System.out.println("Going back to the home screen.");
        App.switchScene("MainView");
    }

    @FXML
    public void search() {
        String name = nameField != null ? nameField.getText() : "";
        String ABV = ABVField != null ? ABVField.getText() : "";
        if (name.isEmpty() && ABV.isEmpty()) {
            System.out.println("Please enter a name or ABV to search for a drink.");
        } 
        else {
            if(ABV.isEmpty()) {
                System.out.println("Searching for drink with name: " + name);
                App.setDrinkResults(SearchUtils.searchByName(App.drinksTable, name));
            }
            else if(name.isEmpty()) {
                System.out.println("Searching for drink with ABV: " + ABV);
                App.setDrinkResults(SearchUtils.searchByABV(App.drinksTable, Double.parseDouble(ABV)));
            }
            else {
                System.out.println("Searching for drink with name: " + name + " and ABV: " + ABV);
                App.setDrinkResults(SearchUtils.searchByNameAndABV(App.drinksTable, name, Double.parseDouble(ABV)));
            }
            App.switchScene("/views/DrinkSearchResults");
        }
    }

    private void buttonEvents() {
        backButton.setOnAction(e -> back());
        homeButton.setOnAction(e -> home());
        searchButton.setOnAction(e -> search());
    }

}
