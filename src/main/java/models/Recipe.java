package models;

import java.io.Serializable;

public class Recipe implements Serializable {
    private Ingredient ingredient;
    private double quantity; // Quantity in milliliters

    public Recipe(Ingredient ingredient, double quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString(){
        return ingredient.getName() + " (" + quantity + " ml)";
    }
}
