package Database;
import java.util.ArrayList;

import food.Ingredient;

public class Database {
    private ArrayList<Ingredient> ingredients;

    public Database() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> searchIngredients(String searchTerm) {
        ArrayList<Ingredient> matchingIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingIngredients.add(ingredient);
            }
        }
        return matchingIngredients;
    }
}
