package Nutriapp2.GuestMode;

import java.util.List;

import Nutriapp2.food.Ingredient;

//Needs the food feature code to be pulleed so I can reference both the ingredient and database class
public class IngredientManager implements Inventory{
    private List<Ingredient> ingredients;

    public IngredientManager(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public List<Ingredient> getIngredients() {
        //System.out.println(ingredients);
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        // add an ingredient to the list
        ingredients.add(ingredient);
    }
}
