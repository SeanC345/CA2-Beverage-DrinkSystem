package models;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private String description;
    private double abv; // Alcohol By Volume

    public Ingredient(String name, String description, double abv) {
        this.name = name;
        this.description = description;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAbv() {
        return abv;
    }
}
