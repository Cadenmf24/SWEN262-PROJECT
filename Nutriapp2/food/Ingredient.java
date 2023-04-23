package food;


/**
 * child class of Food that defines state and behavior for an ingredient, acts
 * as a leaf in the composite pattern
 * 
 * @author Jackson Shortell
 */
public class Ingredient extends Food {

    public Ingredient(int id, String name, int calories, int fat, int protein, int fiber, int carbs) {
        super(id, name, calories, fat, protein, fiber, carbs);
    }

    public Ingredient() {
        super();
    }
}