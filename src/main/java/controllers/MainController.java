package controllers;

import com.example.ca2drinkbeveragesystem.App;

import data.DataPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.ca2drinkbeveragesystem.App.recipesTable;

public class MainController {
    @FXML
    private Button viewDrinksButton;
    @FXML
    private Button viewIngredientsButton;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        buttonEvents();
    }

    @FXML
    public void switchToDrinksView() {
        App.switchScene("/views/DrinkMenuView");
    }

    @FXML
    public void switchToIngredientsView() {
        App.switchScene("/views/IngredientMenuView");
    }

    @FXML
    public void exit() {
        saveData();
        System.exit(0);
    }

    public void saveData() {
        try {
            // Save drinks
            DataPersistence.saveDrinks(App.drinksTable, "drinksData.dat");
            System.out.println("Drinks saved successfully.");

            // Save ingredients
            DataPersistence.saveIngredients(App.ingredientsTable, "ingredientsData.dat");
            System.out.println("Ingredients saved successfully.");

            // Save recipes
            DataPersistence.saveRecipes(App.recipesTable, "recipesData.dat");
            System.out.println("Recipes saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void buttonEvents() {
        viewDrinksButton.setOnAction(e -> switchToDrinksView());
        viewIngredientsButton.setOnAction(e -> switchToIngredientsView());
        exitButton.setOnAction(e -> exit());
    }
}