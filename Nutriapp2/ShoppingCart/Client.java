package Nutriapp2.ShoppingCart;

import Nutriapp2.food.Ingredient;

public class Client {
    //Once we have exported the csv file I can include the parameters to test
	public static void main(String[] args) {
		IngredientElement[] items = new IngredientElement[]{new Ingredient("Meat", 150, 35, 100, 0, 15),new Ingredient("Fish", 150, 10, 130, 0, 10), new Ingredient("Macaroni", 125, 25, 50, 10, 45), new Ingredient("Cerel", 100, 0, 0, 75, 15)};
		int total = calculateCalories(items);
		System.out.println("Total Cost = "+ total);
	}

	private static int calculateCalories(IngredientElement[] items) {
		ShoppingCartVisitor visitor = new ShoppingCartVisitor_child();
		int sum_calories=0;
		for(IngredientElement item : items){
			sum_calories = sum_calories + item.accept(visitor);
		}
		return sum_calories;
	}

}

