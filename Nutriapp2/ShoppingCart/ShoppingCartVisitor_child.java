package ShoppingCart;

import food.Ingredient;

public class ShoppingCartVisitor_child implements ShoppingCartVisitor{
    @Override
	public int visit(Ingredient ingredient) {
		int diet=0;
		if(ingredient.getCalories() > 50){
		    diet = ingredient.getCalories() - 25;
		}else diet = ingredient.getCalories();
		System.out.println("Calories in the ingredient::"+ingredient.getCalories() + " diet ="+ diet);
		return diet;
	}
}
