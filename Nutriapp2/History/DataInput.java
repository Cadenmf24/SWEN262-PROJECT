package History;
public class DataInput {
    //State state;
    int weight;
    int CaloriesConsumed;
    int target;
    String meals;
    String workouts;

    public DataInput(int newWeight, int newCaloriesConsumed, int newTarget, String newMeals, String newWorkouts){
        weight = newWeight;
        CaloriesConsumed = newCaloriesConsumed;
        target = newTarget;
        meals = newMeals;
        workouts = newWorkouts;
    }

    public int getWeight(){
        return weight;
    }

    public int getCaloriesConsumed(){
        return CaloriesConsumed;
    }

    public int getTarget(){
        return target;
    }

    public String getMeals(){
        return meals;
    }

    public String workouts(){
        return workouts;
    }
}
