package Nutriapp2.GuestMode;
import java.util.List;

public class IngredientManagerProxy implements Inventory{
    private IngredientManager ingredientManager;

    public IngredientManagerProxy(List<String> ingredients) {
        this.ingredientManager = new IngredientManager(ingredients);
    }

    @Override
    public List<String> getIngredients() {
        return ingredientManager.getIngredients();
    }

    // This method is restricted for guest users
    public void addIngredient(String ingredient) throws IllegalAccessException {
        throw new IllegalAccessException("Guest users cannot add ingredients");
    }
}
