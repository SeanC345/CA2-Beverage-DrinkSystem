package data;

import java.io.*;
import models.Drink;
import models.Ingredient;
import models.Recipe;

/**
 * Utility class for data persistence.
 * Provides methods to save and load drinks, ingredients, and recipes using serialization.
 */
public class DataPersistence {

    /**
     * Saves all drinks to a file.
     *
     * @param drinksTable the hash table containing drinks
     * @param filename    the file to save the drinks to
     */
    public static void saveDrinks(CustomHashTable<String, Drink> drinksTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : drinksTable.keySet()) { // Iterate through all keys
                oos.writeObject(drinksTable.get(key)); // Write each drink to the file
            }
            System.out.println("Drinks saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save drinks: " + e.getMessage());
        }
    }

    /**
     * Loads drinks from a file into the hash table.
     *
     * @param drinksTable the hash table to load drinks into
     * @param filename    the file to load drinks from
     */
    public static void loadDrinks(CustomHashTable<String, Drink> drinksTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Drink drink = (Drink) ois.readObject(); // Read a drink object
                    drinksTable.put(drink.getName(), drink); // Add the drink to the hash table
                } catch (EOFException eof) {
                    break; // End of file reached
                }
            }
            System.out.println("Drinks loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load drinks: " + e.getMessage());
        }
    }

    /**
     * Saves all ingredients to a file.
     *
     * @param ingredientsTable the hash table containing ingredients
     * @param filename         the file to save the ingredients to
     */
    public static void saveIngredients(CustomHashTable<String, Ingredient> ingredientsTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : ingredientsTable.keySet()) { // Iterate through all keys
                oos.writeObject(ingredientsTable.get(key)); // Write each ingredient to the file
            }
            System.out.println("Ingredients saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save ingredients: " + e.getMessage());
        }
    }

    /**
     * Loads ingredients from a file into the hash table.
     *
     * @param ingredientsTable the hash table to load ingredients into
     * @param filename         the file to load ingredients from
     */
    public static void loadIngredients(CustomHashTable<String, Ingredient> ingredientsTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Ingredient ingredient = (Ingredient) ois.readObject(); // Read an ingredient object
                    ingredientsTable.put(ingredient.getName(), ingredient); // Add the ingredient to the hash table
                } catch (EOFException eof) {
                    break; // End of file reached
                }
            }
            System.out.println("Ingredients loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load ingredients: " + e.getMessage());
        }
    }

    /**
     * Saves all recipes to a file.
     *
     * @param recipesTable the hash table containing recipes
     * @param filename     the file to save the recipes to
     */
    public static void saveRecipes(CustomHashTable<String, Recipe> recipesTable, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (String key : recipesTable.keySet()) { // Iterate through all keys
                oos.writeObject(recipesTable.get(key)); // Write each recipe to the file
            }
            System.out.println("Recipes saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save recipes: " + e.getMessage());
        }
    }

    /**
     * Loads recipes from a file into the hash table.
     *
     * @param recipesTable the hash table to load recipes into
     * @param filename     the file to load recipes from
     */
    public static void loadRecipes(CustomHashTable<String, Recipe> recipesTable, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Recipe recipe = (Recipe) ois.readObject(); // Read a recipe object
                    recipesTable.put(recipe.getParentDrink().getName(), recipe); // Use parent drink's name as the key
                } catch (EOFException eof) {
                    break; // End of file reached
                }
            }
            System.out.println("Recipes loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load recipes: " + e.getMessage());
        }
    }
}
