package food;

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
     * updates the calories, fat, fiber, protein, and carbs fields as changes are
     * made to the recipes
     */
    private void updateMealInfo() {
        calories = 0;
        fat = 0;
        fiber = 0;
        protein = 0;
        carbs = 0;
        for (Recipe r : recipes) {
            calories += r.getCalories();
            fat += r.getFat();
            fiber += r.getFiber();
            protein += r.getProtein();
            carbs += r.getCarbs();
        }
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
        updateMealInfo();
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
        updateMealInfo();
    }

}