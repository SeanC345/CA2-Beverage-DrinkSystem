package utils;

import data.CustomHashTable;
import models.Drink;
import models.Ingredient;

public class SearchUtils {

    // ======================
    // Drink Search Methods
    // ======================

    // Search for drinks by name
    public static CustomLinkedList<Drink> searchDrinksByName(CustomHashTable<String, Drink> drinksTable, String name) {
        CustomLinkedList<Drink> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Drink> node : drinksTable) {
            if (node != null && node.getValue().getName().equalsIgnoreCase(name)) {
                results.add(node.getValue());
            }
        }

        return results;
    }

    // Search for drinks by ABV
    public static CustomLinkedList<Drink> searchDrinksByABV(CustomHashTable<String, Drink> drinksTable, double abv) {
        CustomLinkedList<Drink> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Drink> node : drinksTable) {
            if (node != null && Math.abs(node.getValue().calculateABV() - abv) < 0.01) { // Allow minor floating-point variations
                results.add(node.getValue());
            }
        }

        return results;
    }

    // Search for drinks by name and ABV
    public static CustomLinkedList<Drink> searchDrinksByNameAndABV(CustomHashTable<String, Drink> drinksTable, String name, double abv) {
        CustomLinkedList<Drink> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Drink> node : drinksTable) {
            if (node != null
                    && node.getValue().getName().equalsIgnoreCase(name)
                    && Math.abs(node.getValue().calculateABV() - abv) < 0.01) {
                results.add(node.getValue());
            }
        }

        return results;
    }

    // ==========================
    // Ingredient Search Methods
    // ==========================

    // Search for ingredients by name
    public static CustomLinkedList<Ingredient> searchIngredientsByName(CustomHashTable<String, Ingredient> ingredientsTable, String name) {
        CustomLinkedList<Ingredient> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Ingredient> node : ingredientsTable) {
            if (node != null && node.getValue().getName().equalsIgnoreCase(name)) {
                results.add(node.getValue());
            }
        }

        return results;
    }

    // Search for ingredients by ABV
    public static CustomLinkedList<Ingredient> searchIngredientsByABV(CustomHashTable<String, Ingredient> ingredientsTable, double maxAbv) {
        CustomLinkedList<Ingredient> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Ingredient> node : ingredientsTable) {
            if (node != null && node.getValue().getAbv() <= maxAbv) { // Include all with ABV <= maxAbv
                results.add(node.getValue());
            }
        }

        return results;
    }

    // Search for ingredients by name and ABV
    public static CustomLinkedList<Ingredient> searchIngredientsByNameAndABV(CustomHashTable<String, Ingredient> ingredientsTable, String name, double abv) {
        CustomLinkedList<Ingredient> results = new CustomLinkedList<>();

        for (CustomHashTable.Node<String, Ingredient> node : ingredientsTable) {
            if (node != null
                    && node.getValue().getName().equalsIgnoreCase(name)
                    && Math.abs(node.getValue().getAbv() - abv) < 0.01) {
                results.add(node.getValue());
            }
        }

        return results;
    }
}
