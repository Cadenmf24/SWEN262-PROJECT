package Nutriapp2.foodTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import food.Ingredient;
import food.Recipe;

@Testable
/**
 * testing file for the Recipe class
 * 
 * @author Jackson Shortell
 */
public class RecipeTest {

    @Test
    void testAddIngredientSuccessfully() {
        // setup
        Recipe recipe = new Recipe("test recipe", "test instructions");
        recipe.addIngredient(new Ingredient("test ingredient", 10, 2, 3, 0, 5), 2); // quantity is greater than 0 and is
                                                                                    // therefore added
        int expected = 1;
        // invoke
        int actual = recipe.getIngredients().size();
        // analyze
        assertEquals(actual, expected);
    }

    @Test
    void testAddIngredientUnsuccessfully() {
        // setup
        Recipe recipe = new Recipe("test recipe", "test instructions");
        recipe.addIngredient(new Ingredient("test ingredient", 10, 2, 3, 0, 5), 0); // quantity is less or equal than 0
                                                                                    // and is therefore not added
        int expected = 0;
        // invoke
        int actual = recipe.getIngredients().size();
        // analyze
        assertEquals(actual, expected);
    }

    @Test
    void testRemoveIngredient() {
        // setup
        Recipe recipe = new Recipe("test recipe", "test instructions");
        Ingredient ingredient1 = new Ingredient("test ingredient1", 10, 2, 3, 0, 5);
        Ingredient ingredient2 = new Ingredient("test ingredient2", 10, 2, 3, 0, 5);
        recipe.addIngredient(ingredient1, 2);
        recipe.addIngredient(ingredient2, 4);
        int expected = 1;
        // invoke
        recipe.removeIngredient(ingredient2);
        int actual = recipe.getIngredients().size();
        // analyze
        assertEquals(actual, expected);
    }

    @Test
    /**
     * updateRecipeInfo is called indirectly each time the ingredients are changed
     */
    void testUpdateRecipeInfoWithAdd() {
        // setup
        Recipe recipe = new Recipe("test recipe", "test instructions");
        Ingredient ingredient1 = new Ingredient("test ingredient1", 125, 64, 27, 8, 1);
        Ingredient ingredient2 = new Ingredient("test ingredient2", 25, 16, 9, 4, 1);
        Ingredient ingredient3 = new Ingredient("test ingredient3", 5, 4, 3, 2, 1);
        int[] actual = new int[5];
        int[] expected = new int[] { 190, 108, 54, 22, 6 };
        // invoke
        recipe.addIngredient(ingredient1, 1);
        recipe.addIngredient(ingredient2, 2);
        recipe.addIngredient(ingredient3, 3);
        // analyze
        actual[0] = recipe.getCalories();
        actual[1] = recipe.getFat();
        actual[2] = recipe.getProtein();
        actual[3] = recipe.getFiber();
        actual[4] = recipe.getCarbs();
        assertArrayEquals(expected, actual);
    }

    @Test
    /**
     * updateRecipeInfo is called indirectly each time the ingredients are changed
     */
    void testUpdateRecipeInfoWithRemove() {
        // setup
        Recipe recipe = new Recipe("test recipe", "test instructions");
        Ingredient ingredient1 = new Ingredient("test ingredient1", 125, 64, 27, 8, 1);
        Ingredient ingredient2 = new Ingredient("test ingredient2", 25, 16, 9, 4, 1);
        Ingredient ingredient3 = new Ingredient("test ingredient3", 5, 4, 3, 2, 1);
        int[] actual = new int[5];
        int[] expected = new int[] { 65, 44, 27, 14, 5 };
        // invoke
        recipe.addIngredient(ingredient1, 1);
        recipe.addIngredient(ingredient2, 2);
        recipe.addIngredient(ingredient3, 3);
        recipe.removeIngredient(ingredient1);
        // analyze
        actual[0] = recipe.getCalories();
        actual[1] = recipe.getFat();
        actual[2] = recipe.getProtein();
        actual[3] = recipe.getFiber();
        actual[4] = recipe.getCarbs();
        assertArrayEquals(expected, actual);
    }

}