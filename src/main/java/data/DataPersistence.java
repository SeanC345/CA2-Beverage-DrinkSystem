package data;

import java.io.*;
import data.CustomHashTable;
import models.Drink;
import models.Ingredient;
import models.Recipe;

public class DataPersistence {

    // Save drinks to file
    public static void saveDrinks(CustomHashTable<String, Drink> drinksTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : drinksTable.keySet()) {
                oos.writeObject(drinksTable.get(key));
            }
            System.out.println("Drinks saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save drinks: " + e.getMessage());
        }
    }

    // Load drinks from file
    public static void loadDrinks(CustomHashTable<String, Drink> drinksTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Drink drink = (Drink) ois.readObject();
                    drinksTable.put(drink.getName(), drink);
                } catch (EOFException eof) {
                    break; // End of file
                }
            }
            System.out.println("Drinks loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load drinks: " + e.getMessage());
        }
    }

    // Similar save/load methods for Ingredients and Recipes
    public static void saveIngredients(CustomHashTable<String, Ingredient> ingredientsTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : ingredientsTable.keySet()) {
                oos.writeObject(ingredientsTable.get(key));
            }
            System.out.println("Ingredients saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save ingredients: " + e.getMessage());
        }
    }

    public static void loadIngredients(CustomHashTable<String, Ingredient> ingredientsTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Ingredient ingredient = (Ingredient) ois.readObject();
                    ingredientsTable.put(ingredient.getName(), ingredient);
                } catch (EOFException eof) {
                    break; // End of file
                }
            }
            System.out.println("Ingredients loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load ingredients: " + e.getMessage());
        }
    }

    public static void saveRecipes(CustomHashTable<String, Recipe> recipesTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : recipesTable.keySet()) {
                oos.writeObject(recipesTable.get(key));
            }
            System.out.println("Recipes saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save recipes: " + e.getMessage());
        }
    }

    public static void loadRecipes(CustomHashTable<String, Recipe> recipesTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Recipe recipe = (Recipe) ois.readObject();
                    recipesTable.put(recipe.getParentDrink().getName(), recipe); // Use parent drink's name as key
                } catch (EOFException eof) {
                    break; // End of file
                }
            }
            System.out.println("Recipes loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load recipes: " + e.getMessage());
        }
    }
}
