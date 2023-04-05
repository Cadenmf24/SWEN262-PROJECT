package ShoppingCart;

import food.Ingredient;

public class Client {
    //Once we have exported the csv file I can include the parameters to test
	public static void main(String[] args) {
		IngredientElement[] items = new IngredientElement[]{new Ingredient(),new Ingredient(),
				new Ingredient(), new Ingredient()};
		
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

