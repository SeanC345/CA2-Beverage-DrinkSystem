package com.example.ca2drinkbeveragesystem;

import java.io.IOException;
import java.io.Serializable;

import data.CustomHashTable;
import data.DataPersistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Drink;
import models.Ingredient;
import models.Recipe;
import utils.CustomLinkedList;

public class App extends Application implements Serializable {

    private static Scene scene;
    public static CustomHashTable<String, Drink> drinksTable = new CustomHashTable<>(1000);
    public static CustomHashTable<String, Ingredient> ingredientsTable = new CustomHashTable<>(1000);
    public static CustomHashTable<String, Recipe> recipesTable = new CustomHashTable<>(1000); // Added for recipes
    public static Drink selectedDrink;
    public static Ingredient selectedIngredient;
    public static CustomLinkedList<Drink> drinkResults;
    public static CustomLinkedList<Ingredient> ingredientResults;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/MainView.fxml"));
        scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setResizable(false);
        stage.setTitle("Beverage System");
        stage.setScene(scene);
        stage.show();
        load();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println("Loading FXML: " + fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchScene(String fxmlFile) {
        try {
            setRoot(fxmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSelectedDrink(Drink drink) {
        selectedDrink = drink;
    }

    public static void setDrinkResults(CustomLinkedList<Drink> results) {
        drinkResults = results;
    }

    public static void setSelectedIngredient(Ingredient ingredient) {
        selectedIngredient = ingredient;
    }

    public static Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }

    public static void setSelectedIngredientResults(CustomLinkedList<Ingredient> results) {
        ingredientResults = results;
    }

    public static void load() {
        DataPersistence.loadDrinks(drinksTable, "drinksData.dat");
        DataPersistence.loadIngredients(ingredientsTable, "ingredientsData.dat");
        DataPersistence.loadRecipes(recipesTable, "recipesData.dat");

        // Restore parentDrink in recipes
        for (String drinkName : drinksTable.keySet()) {
            Drink drink = drinksTable.get(drinkName);
            Recipe recipe = recipesTable.get(drinkName);
            if (recipe != null) {
                drink.setRecipe(recipe);
                recipe.setParentDrink(drink);
            }
        }
    }
}
