package models;

public class Recipe {
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
}
