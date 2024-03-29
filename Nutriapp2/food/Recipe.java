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
     * times that ingredient is used in the recipe aka the quantity
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
     * adds an ingredient and associated quantity to the recipes ingredients
     * then updates recipe info
     * 
     * @param ingredient Ingredient object
     * @param quantity   int
     */
    public void addIngredient(Ingredient ingredient, int quantity) {
        if (quantity > 0) {
            ingredients.put(ingredient, quantity);
            calories += ingredient.getCalories() * quantity;
            fat += ingredient.getFat() * quantity;
            fiber += ingredient.getFiber() * quantity;
            protein += ingredient.getProtein() * quantity;
            carbs += ingredient.getCarbs() * quantity;
        }
    }

    /**
     * removes an ingredient from recipes ingredients
     * then updates recipe info
     * 
     * @param ingredient Ingredient object
     */
    public void removeIngredient(Ingredient ingredient) {
        int quantity = ingredients.get(ingredient);
        ingredients.remove(ingredient);
        calories -= ingredient.getCalories() * quantity;
        fat -= ingredient.getFat() * quantity;
        fiber -= ingredient.getFiber() * quantity;
        protein -= ingredient.getProtein() * quantity;
        carbs -= ingredient.getCarbs() * quantity;
    }
    public String toString(){
        System.out.print(ingredients);
        //return ingredients;
        return " Name: " + name + " Instructions: " + instructions;
    }
}