package Nutriapp2.Command;

import Nutriapp2.food.Ingredient;

public class AddIngredientCommand implements Command {
    private Stock stock;
    private Ingredient ingredient;
    
    public AddIngredientCommand(Stock stock, Ingredient ingredient) {
        this.stock = stock;
        this.ingredient = ingredient;
    }
    
    public void execute() {
        // Add the ingredient to the stock
        stock.addIngredient(ingredient);
    }
    
    public void undo() {
        // Remove the ingredient from the stock
        stock.removeIngredient(ingredient);
    }
}