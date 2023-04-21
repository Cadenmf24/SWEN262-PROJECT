package ShoppingCart;

import food.Ingredient;

public interface ShoppingCartVisitor {

	int visit(Ingredient ingredient);
}