package Nutriapp2.food;

/**
 * abstract class that defines state and behavior for food items, acts as the
 * component in the composite pattern
 * 
 * @author Jackson Shortell
 */
public abstract class Food {

    protected String name;

    protected int calories;

    /**
     * all measured in grams
     */
    protected int fat;

    protected int protein;

    protected int fiber;

    protected int carbs;

    public Food(String name, int calories, int fat, int protein, int fiber, int carbs) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.fiber = fiber;
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getFiber() {
        return fiber;
    }

    public int getCarbs() {
        return carbs;
    }

}