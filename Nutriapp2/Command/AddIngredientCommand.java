package Command;

import food.Ingredient;
import food.InsufficientQuantityException;
import food.Stock;
public class AddIngredientCommand implements Command {
    private Stock stock = new Stock();
    private Ingredient ingredient;
    private int amount;
    public AddIngredientCommand(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }
    
    public void execute() {
        // Stock up the ingredient
        stock.addIngredient(ingredient, amount);
    }
    
    public void undo() {
        // Reduce the stock of ingredient
        try {
            stock.removeIngredient(ingredient, amount);
        } catch (InsufficientQuantityException e) {
            e.printStackTrace();
        }
    }
}