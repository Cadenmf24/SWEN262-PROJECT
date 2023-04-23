package food;

import ShoppingCart.IngredientElement;
import ShoppingCart.ShoppingCartVisitor;

/**
 * child class of Food that defines state and behavior for an ingredient, acts
 * as a leaf in the composite pattern
 * 
 * @author Jackson Shortell
 */
public class Ingredient extends Food implements IngredientElement{

    public Ingredient(String name, int calories, int fat, int protein, int fiber, int carbs) {
        super(name, calories, fat, protein, fiber, carbs);
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

    public String getUnit() {
        return null;
    }

    public double getPricePerUnit() {
        return 0;
    }

    public Double getBaseAmount() {
        return null;
    }
    public String toString(){
        return "Name: " + name + " Calories: " + calories + " Fat: " + fat + " Protein: " + protein + " Fiber: " + fiber + " Carbs: " + carbs;
    }
}