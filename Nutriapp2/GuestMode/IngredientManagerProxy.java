package GuestMode;
import java.util.List;

import food.Ingredient;

public class IngredientManagerProxy implements Inventory{
    private IngredientManager ingredientManager;

    public IngredientManagerProxy(List<Ingredient> ingredients) {
        this.ingredientManager = new IngredientManager(ingredients);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientManager.getIngredients();
    }

    // This method is restricted for guest users
    public void addIngredient(String ingredient) throws IllegalAccessException {
        throw new IllegalAccessException("Guest users cannot add ingredients");
    }
}
