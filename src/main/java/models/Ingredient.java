package models;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L; // Added for serialization compatibility

    private String name;
    private String description;
    private double abv; // Alcohol By Volume

    // Constructor
    public Ingredient(String name, String description, double abv) {
        this.name = name;
        this.description = description;
        this.abv = abv;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAbv() {
        return abv;
    }

    // Override toString for better representation
    @Override
    public String toString() {
        return name + " (ABV: " + abv + "%): " + description;
    }
}
