package Nutriapp2.ShoppingCart;

import Nutriapp2.food.Ingredient;

public interface ShoppingCartVisitor {

	int visit(Ingredient ingredient);
}