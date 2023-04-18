package Nutriapp2.GuestMode;

import java.util.List;

//Needs the food feature code to be pulleed so I can reference both the ingredient and database class
public class IngredientManager implements Inventory{
    private List<String> ingredients;

    public IngredientManager(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        // add an ingredient to the list
        ingredients.add(ingredient);
    }
}
