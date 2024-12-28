package models;

import java.io.Serializable;

import utils.ABVCalculator;

public class Drink implements Serializable {
    private String name;
    private String origin;
    private String description;
    private String imageUrl;
    private Recipe recipe;

    public Drink(String name, String origin, String description, String imageUrl, Recipe recipe) {
        this.name = name;
        this.origin = origin;
        this.description = description;
        this.imageUrl = imageUrl;
        this.recipe = recipe;
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

    public Recipe getRecipe(){
        return recipe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double calculateABV() {
        return ABVCalculator.calculateABV(this);
    }

    @Override
    public String toString(){
        return name + " (" + origin + "): " + description;
    }
}
