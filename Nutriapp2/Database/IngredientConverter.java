package Nutriapp2.Database;

import Nutriapp2.food.Ingredient;

public class IngredientConverter {
    
    public static Ingredient convertStringToIngredient(String ingredientString) {
        // Split the string into its parts
        String[] parts = ingredientString.split(",");
        
        // Extract the name, quantity, and unit from the parts
        //int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int calories = Integer.parseInt(parts[2]);
        int protein  = Integer.parseInt(parts[3]);
        int fat = Integer.parseInt(parts[4]);
        int carbs = Integer.parseInt(parts[5]);
        int fiber = Integer.parseInt(parts[6]);
        
        // Create a new Ingredient object with the extracted data
        Ingredient ingredient = new Ingredient(name, calories, protein, fat, carbs, fiber);
        
        return ingredient;
    }
}
