package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import utils.ABVCalculator;
import utils.CustomLinkedList;

public class Drink implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String origin;
    private String description;
    private String imageUrl;
    private transient Recipe recipe; // Mark transient to avoid recursion during serialization

    // Constructor
    public Drink(String name, String origin, String description, String imageUrl, Recipe recipe) {
        this.name = name;
        this.origin = origin;
        this.description = description;
        this.imageUrl = imageUrl;
        setRecipe(recipe); // Use setter for consistency
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double calculateABV() {
        return ABVCalculator.calculateABV(this);
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        if (recipe != null) {
            recipe.setParentDrink(this); // Ensure back-reference is set
        }
    }

    @Override
    public String toString() {
        return name + " (" + origin + "): " + description;
    }

    // Custom Serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Serialize non-transient fields
        if (recipe != null) {
            oos.writeObject(recipe.getIngredients()); // Serialize ingredients
            oos.writeDouble(recipe.getQuantity());    // Serialize quantity
        } else {
            oos.writeObject(null); // Serialize null if recipe is missing
            oos.writeDouble(0.0);  // Default quantity
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Deserialize non-transient fields
        CustomLinkedList<Ingredient> ingredients = (CustomLinkedList<Ingredient>) ois.readObject();
        double quantity = ois.readDouble();

        // Rebuild the recipe, if applicable
        if (ingredients != null) {
            recipe = new Recipe(ingredients, quantity);
            recipe.setParentDrink(this); // Restore back-reference
        } else {
            recipe = null;
        }
    }
}

