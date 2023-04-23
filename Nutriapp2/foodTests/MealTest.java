package .foodTests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import food.Ingredient;
import food.Meal;
import food.Recipe;

@Testable
/**
 * testing file for the Meal class
 * 
 * @author Jackson Shortell
 */
public class MealTest {

    @Test
    /**
     * updateMealInfo is called indirectly each time the recipes are changed
     */
    void testUpdateMealInfoWithAdd() {
        // setup
        Recipe recipe1 = new Recipe("test recipe1", "test instructions");
        Recipe recipe2 = new Recipe("test recipe2", "test instructions");
        Ingredient ingredient1 = new Ingredient("test ingredient1", 125, 64, 27, 8, 1);
        Ingredient ingredient2 = new Ingredient("test ingredient2", 25, 16, 9, 4, 1);
        Ingredient ingredient3 = new Ingredient("test ingredient3", 5, 4, 3, 2, 1);
        recipe1.addIngredient(ingredient1, 1);
        recipe1.addIngredient(ingredient2, 2);
        recipe1.addIngredient(ingredient3, 3);
        recipe2.addIngredient(ingredient1, 1);
        recipe2.addIngredient(ingredient2, 2);
        recipe2.addIngredient(ingredient3, 3);
        int[] actual = new int[5];
        int[] expected = new int[] { 380, 216, 108, 44, 12 };
        Meal meal = new Meal("test meal");
        // invoke
        meal.addRecipe(recipe1);
        meal.addRecipe(recipe2);
        // analyze
        actual[0] = meal.getCalories();
        actual[1] = meal.getFat();
        actual[2] = meal.getProtein();
        actual[3] = meal.getFiber();
        actual[4] = meal.getCarbs();
        assertArrayEquals(expected, actual);
    }

    @Test
    /**
     * updateMealInfo is called indirectly each time the recipes are changed
     */
    void testUpdateMealInfoWithRemove() {
        // setup
        Recipe recipe1 = new Recipe("test recipe1", "test instructions");
        Recipe recipe2 = new Recipe("test recipe2", "test instructions");
        Ingredient ingredient1 = new Ingredient("test ingredient1", 125, 64, 27, 8, 1);
        Ingredient ingredient2 = new Ingredient("test ingredient2", 25, 16, 9, 4, 1);
        Ingredient ingredient3 = new Ingredient("test ingredient3", 5, 4, 3, 2, 1);
        recipe1.addIngredient(ingredient1, 1);
        recipe1.addIngredient(ingredient2, 2);
        recipe1.addIngredient(ingredient3, 3);
        recipe2.addIngredient(ingredient1, 1);
        recipe2.addIngredient(ingredient2, 2);
        recipe2.addIngredient(ingredient3, 3);
        int[] actual = new int[5];
        int[] expected = new int[] { 190, 108, 54, 22, 6 };
        Meal meal = new Meal("test meal");
        // invoke
        meal.addRecipe(recipe1);
        meal.addRecipe(recipe2);
        meal.removeRecipe(recipe1);
        // analyze
        actual[0] = meal.getCalories();
        actual[1] = meal.getFat();
        actual[2] = meal.getProtein();
        actual[3] = meal.getFiber();
        actual[4] = meal.getCarbs();
        assertArrayEquals(expected, actual);
    }
}
