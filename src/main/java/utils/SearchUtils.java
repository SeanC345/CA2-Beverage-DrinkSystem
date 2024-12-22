package utils;

import models.Drink;
import models.Ingredient;

public class SearchUtils {
    // Shell Sort implementation for sorting by recipe
    public static void sortDrinksByRecipe(CustomLinkedList<Drink> drinks) {
        int n = drinks.size(); // Get the size of the list
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) { // Gap starts at half the size of the list and is halved each iteration
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) { // Iterate over elements in the list, starting from the first element after the gap
                Drink temp = drinks.get(i); // Temporarily store the current element
                int j;
                // Shift earlier gap-sorted elements up until the correct location for the current element is found
                for (j = i; j >= gap && compareByRecipe(drinks.get(j - gap), temp) > 0; j -= gap) {
                    drinks.set(j, drinks.get(j - gap)); // Move the element at j-gap to position j
                }
                drinks.set(j, temp); // Place the temporary element in its correct position
            }
        }
    }

    // helper method
    private static int compareByRecipe(Drink d1, Drink d2) {
        return d1.getRecipes().size() - d2.getRecipes().size();
    }

    // Shell Sort implementation for sorting by name
    public static void sortDrinksByName(CustomLinkedList<Drink> drinks) {
        int n = drinks.size(); // Get the size of the list
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) { // Gap starts at half the size of the list and is halved each iteration
            // Perform a gaped insertion sort for this gap size
            for (int i = gap; i < n; i++) { // Iterate over elements in the list, starting from the first element after the gap
                Drink temp = drinks.get(i); // Temporarily store the current element
                int j;
                // Shift earlier gap-sorted elements up until the correct location for the current element is found
                for (j = i; j >= gap && drinks.get(j - gap).getName().compareTo(temp.getName()) > 0; j -= gap) {
                    drinks.set(j, drinks.get(j - gap)); // Move the element at j-gap to position j
                }
                drinks.set(j, temp); // Place the temporary element in its correct position
            }
        }
    }


    // Shell Sort implementation for sorting by ABV
    public static void sortDrinksByABV(CustomLinkedList<Drink> drinks) {
        int n = drinks.size(); // Get the size of the list
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) { // Gap starts at half the size of the list and is halved each iteration
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) { // Iterate over elements in the list, starting from the first element after the gap
                Drink temp = drinks.get(i); // Temporarily store the current element
                int j;
                // Shift earlier gap-sorted elements up until the correct location for the current element is found
                for (j = i; j >= gap && drinks.get(j - gap).calculateABV() > temp.calculateABV(); j -= gap) {
                    drinks.set(j, drinks.get(j - gap)); // Move the element at j-gap to position j
                }
                drinks.set(j, temp); // Place the temporary element in its correct position
            }
        }
    }

    // Shell Sort implementation for sorting ingredients by name
    public static void sortIngredientsByName(CustomLinkedList<Ingredient> ingredients) {
        int n = ingredients.size(); // Get the size of the list
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) { // Gap starts at half the size of the list and is halved each iteration
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) { // Iterate over elements in the list, starting from the first element after the gap
                Ingredient temp = ingredients.get(i); // Temporarily store the current element
                int j;
                // Shift earlier gap-sorted elements up until the correct location for the current element is found
                for (j = i; j >= gap && ingredients.get(j - gap).getName().compareTo(temp.getName()) > 0; j -= gap) {
                    ingredients.set(j, ingredients.get(j - gap)); // Move the element at j-gap to position j
                }
                ingredients.set(j, temp); // Place the temporary element in its correct position
            }
        }
    }

    public static CustomLinkedList<Drink> searchDrinksByName(CustomLinkedList<Drink> drinks, String keyword) {
        CustomLinkedList<Drink> result = new CustomLinkedList<>();
        for (Drink drink : drinks) {
            if (drink.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(drink);
            }
        }
        return result;
    }
}

