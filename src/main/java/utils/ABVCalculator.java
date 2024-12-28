package utils;
import models.Drink;
import models.Ingredient;
import models.Recipe;

public class ABVCalculator {
    public static Double calculateABV(Drink drink) {
        Recipe recipe = drink.getRecipe();
        CustomLinkedList<Ingredient> ingredients = recipe.getIngredients();
        double totalAlcoholContent = 0.0; // (ABV * Quantity)
        double totalVolume = 0.0; // Sum of all quantities
        // Loop through all ingredients in recipe
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i); // Get the recipe at index i
            double quantity = recipe.getQuantity();
            // Add to the total alcohol content if ABV > 0
            totalAlcoholContent += ingredient.getABV() * quantity;
            //Add to the total volume
            totalVolume += quantity;
        }
        // Avoid division by zero
        if ( totalVolume == 0 ) {
            return 0.0;
        }

        return totalAlcoholContent / totalVolume;
    }
}
