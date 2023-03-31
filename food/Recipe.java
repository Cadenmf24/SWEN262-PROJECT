package food;

import java.util.HashMap;

/**
 * child class of Food that defines state and behavior for a recipe, acts
 * as a composite in the composite pattern
 * 
 * @author Jackson Shortell
 */
public class Recipe extends Food {

    private String instructions;

    /**
     * hash map where the keys are the ingredients and the values are the how many
     * times that ingredient is used in the recipe
     */
    private HashMap<Ingredient, Integer> ingredients;

    public Recipe(String name, String instructions) {
        super(name, 0, 0, 0, 0, 0);
        instructions = this.instructions;
        ingredients = new HashMap<>();
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String name) {
        this.name = name;
    }

    /**
     * updates the calories, fat, fiber, protein, and carbs fields as changes are
     * made to the ingredients
     */
    private void updateRecipeInfo() {
        calories = 0;
        fat = 0;
        fiber = 0;
        protein = 0;
        carbs = 0;
        for (Ingredient i : ingredients.keySet()) {
            int quantity = ingredients.get(i);
            calories += i.getCalories() * quantity;
            fat += i.getFat() * quantity;
            fiber += i.getFiber() * quantity;
            protein += i.getProtein() * quantity;
            carbs += i.getCarbs() * quantity;
        }
    }

    /**
     * adds an ingredient and associated quantity to the recipes ingredients
     * then updates recipe info
     * 
     * @param ingredient Ingredient object
     * @param quantity   int
     */
    public void addIngredient(Ingredient ingredient, int quantity) {
        if (quantity > 0) {
            ingredients.put(ingredient, quantity);
            updateRecipeInfo();
        }
    }

    /**
     * removes an ingredient from recipes ingredients
     * then updates recipe info
     * 
     * @param ingredient Ingredient object
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        updateRecipeInfo();
    }

}