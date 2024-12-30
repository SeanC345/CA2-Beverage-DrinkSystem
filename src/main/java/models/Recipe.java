package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import utils.CustomLinkedList;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;

    private CustomLinkedList<Ingredient> ingredients;
    private double quantity; // Quantity in milliliters
    private transient Drink parentDrink; // Completely transient

    // Constructor
    public Recipe(CustomLinkedList<Ingredient> ingredients, double quantity) {
        this.ingredients = ingredients;
        this.quantity = quantity;
    }

    // Add ingredient to the recipe
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // Getters
    public CustomLinkedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getQuantity() {
        return quantity;
    }

    public Drink getParentDrink() {
        return parentDrink;
    }

    // Setters
    public void setParentDrink(Drink parentDrink) {
        this.parentDrink = parentDrink;
    }

    @Override
    public String toString() {
        return ingredients.toString() + " (" + quantity + " ml)";
    }

    // Custom Serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Serialize non-transient fields
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Deserialize non-transient fields
        parentDrink = null; // Always restored later via `App.load`
    }
}
