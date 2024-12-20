package models;

import utils.ABVCalculator;
import utils.CustomLinkedList;

import java.io.Serializable;

public class Drink implements Serializable {
    private String name;
    private String origin;
    private String description;
    private String imageUrl;
    private CustomLinkedList<Recipe> recipes;

    public Drink(String name, String origin, String description, String imageUrl) {
        this.name = name;
        this.origin = origin;
        this.description = description;
        this.imageUrl = imageUrl;
        this.recipes = new CustomLinkedList<>();
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public CustomLinkedList<Recipe> getRecipes(){
        return recipes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double calculateABV() {
        return ABVCalculator.calculateABV(this, recipes);
    }

    @Override
    public String toString(){
        return name + " (" + origin + "): " + description;
    }
}
