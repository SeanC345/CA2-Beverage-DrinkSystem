package controllers;

import com.example.ca2drinkbeveragesystem.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    private void saveData() {
        data.DataPersistence.saveData(App.drinksTable, "drinksData.dat");
        data.DataPersistence.saveData(App.ingredientsTable, "ingredientsData.dat");
        
        // Ingredient ingredient1 = new Ingredient("ingredient1", "ingredient1", 1.0);
        // Ingredient ingredient2 = new Ingredient("ingredient2", "ingredient2", 2.0);
        // CustomLinkedList<Ingredient> recipeList = new CustomLinkedList<>();
        // recipeList.add(ingredient1);
        // recipeList.add(ingredient2);
        // Recipe recipe = new Recipe(recipeList, 500);
        // Drink drink1 = new Drink("drink1", "drink1", "drink1", "drink1", recipe);

        // App.testTable1.put("abc", drink1);
        // App.testTable2.put("def", ingredient1);
        // App.testTable2.put("ghi", ingredient2);
        // data.DataPersistence.saveData(App.testTable1, "test1.dat");
        // data.DataPersistence.saveData(App.testTable2, "test2.dat");
    }

    public void buttonEvents() {
        viewDrinksButton.setOnAction(e -> switchToDrinksView());
        viewIngredientsButton.setOnAction(e -> switchToIngredientsView());
        exitButton.setOnAction(e -> exit());
    }
}