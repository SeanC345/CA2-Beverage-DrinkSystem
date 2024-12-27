package utils;

import data.CustomHashTable;
import models.Drink;

public class SearchUtils {

    // Search for drinks by name
    public static CustomLinkedList<Drink> searchByName(CustomHashTable<String, Drink> drinksTable, String name) {
        CustomLinkedList<Drink> results = new CustomLinkedList<>();
        
        for (CustomHashTable.Node<String, Drink> node : drinksTable) {
            if (node != null && node.getValue().getName().equalsIgnoreCase(name)) {
                results.add(node.getValue());
            }
        }
        
        return results;
    }

    // Search for drinks by ABV
    public static CustomLinkedList<Drink> searchByABV(CustomHashTable<String, Drink> drinksTable, double abv) {
        CustomLinkedList<Drink> results = new CustomLinkedList<>();
        
        for (CustomHashTable.Node<String, Drink> node : drinksTable) {
            if (node != null && Math.abs(node.getValue().calculateABV() - abv) < 0.01) { // Allow minor floating-point variations
                results.add(node.getValue());
            }
        }
        
        return results;
    }

    // Search for drinks by name and ABV
    public static CustomLinkedList<Drink> searchByNameAndABV(CustomHashTable<String, Drink> drinksTable, String name, double abv) {
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
