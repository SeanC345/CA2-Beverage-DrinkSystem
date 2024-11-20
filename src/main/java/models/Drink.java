package models;

import utils.ABVCalculator;

public class Drink {
    private String name;
    private String origin;
    private String description;
    private String imageUrl;

    public Drink(String name, String origin, String description, String imageUrl) {
        this.name = name;
        this.origin = origin;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public double calculateABV() {
        return ABVCalculator.calculateABV(this)
    }
}
