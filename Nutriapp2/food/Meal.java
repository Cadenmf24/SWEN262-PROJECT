package Nutriapp2.food;

import java.util.ArrayList;

/**
 * child class of Food that defines state and behavior for a meal, acts
 * as a composite in the composite pattern
 * 
 * @author Jackson Shortell
 */
public class Meal extends Food {

    private ArrayList<Recipe> recipes;

    public Meal(String name) {
        super(name, 0, 0, 0, 0, 0);
        recipes = new ArrayList<>();
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String name) {
        this.name = name;
    }

    /**
     * adds a recipe to the list of recipes
     * then updates meal info
     * 
     * @param recipe   Recipe object
     * @param quantity int
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        calories += recipe.getCalories();
        fat += recipe.getFat();
        fiber += recipe.getFiber();
        protein += recipe.getProtein();
        carbs += recipe.getCarbs();
    }

    /**
     * removes a recipe to the list of recipes
     * then updates meal info
     * 
     * @param recipe   Recipe object
     * @param quantity int
     */
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        calories -= recipe.getCalories();
        fat -= recipe.getFat();
        fiber -= recipe.getFiber();
        protein -= recipe.getProtein();
        carbs -= recipe.getCarbs();
    }

}