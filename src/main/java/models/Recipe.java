package models;

import java.io.Serializable;

import utils.CustomLinkedList;

public class Recipe implements Serializable {
    private CustomLinkedList<Ingredient> ingredients;
    private double quantity; // Quantity in milliliters

    public Recipe(CustomLinkedList<Ingredient> ingredients, double quantity) {
        this.ingredients = ingredients;
        this.quantity = quantity;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public CustomLinkedList<Ingredient> getIngredients(){
        return ingredients;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString(){
        return ingredients.toString() + " (" + quantity + " ml)";
    }
}
