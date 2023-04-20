package Nutriapp2.food;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Ingredient, Integer> ingredients;
    
    public Stock() {
        ingredients = new HashMap<>();
    }
    
    public void addIngredient(Ingredient ingredient, int quantity) {
        int currentQuantity = ingredients.getOrDefault(ingredient, 0);
        ingredients.put(ingredient, currentQuantity + quantity);
    }
    
    public void removeIngredient(Ingredient ingredient, int quantity) throws InsufficientQuantityException {
        int currentQuantity = ingredients.getOrDefault(ingredient, 0);
        if (currentQuantity < quantity) {
            throw new InsufficientQuantityException("Not enough " + ingredient + " in stock");
        }
        ingredients.put(ingredient, currentQuantity - quantity);
    }
}