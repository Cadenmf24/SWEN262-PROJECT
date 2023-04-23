package Database;

import food.Ingredient;

public class IngredientConverter {
    
    public static Ingredient convertStringToIngredient(String ingredientString) {
        // Split the string into its parts
        String[] parts = ingredientString.split(",");
        //System.out.println("In here"+ ""+ parts[0]);
        // Extract the name, calories, protein, fat, carbs, and fiber from the parts
        String name = parts[0];
        double calories = Double.parseDouble(parts[1]);
        double protein  = Double.parseDouble(parts[2]);
        double fat = Double.parseDouble(parts[3]);
        double carbs = Double.parseDouble(parts[4]);
        double fiber = Double.parseDouble(parts[5]);
        // Create a new Ingredient object with the extracted data
        Ingredient ingredient = new Ingredient(name, (int) calories, (int)protein, (int) fat, (int) carbs, (int) fiber);
        //System.out.println(ingredient.getCalories()+ "\n");
        return ingredient;
    }
}
