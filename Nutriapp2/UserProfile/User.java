package UserProfile;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import teamThings.Teams;
import State.Goal;
import State.GoalState;
import Workout.Workout;
import Workout.WorkoutFactory;
//import ShoppingCart.IngredientElement;
import food.Ingredient;

public class User {
    private String name;
    private int height; // in centimeters
    private double weight; // in kilograms
    private Date birthdate;
    private GoalState goal;
    private int targetCaloriesPerDay;
    private int exercisePerDay; // in minutes
    private Map<Ingredient, Double> stock;
    private Stack<Double> weights = new Stack<>();
    private Teams team = null;
    private ArrayList<Workout> workouts = new ArrayList<>();
    private ArrayList<String> notifications = new ArrayList<>();


    public User(String name, int height, double weight, Date birthdate) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
        //this.goal = goal.; Perphaps maybe we could have a scanner method select user inpout choice after they view options
        //this.targetCaloriesPerDay;
        this.exercisePerDay = 0;
        this.stock = new HashMap<Ingredient, Double>();
    }

    public int calculateAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthdateCalendar = Calendar.getInstance();
        birthdateCalendar.setTime(birthdate);
        int age = today.get(Calendar.YEAR) - birthdateCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthdateCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    public void addWeight(double weight) {
        weights.push(weight);
    }

    public double getPreviousWeight() {
        if (weights.size() < 2) {
            System.out.println("No previous weight recorded");
            return 0.0;
        } else {
            weights.pop(); // pop the current weight
            double previousWeight = weights.peek();
            System.out.println("Previous weight: " + previousWeight);
            return previousWeight;
        }
    }
    public void setTargetCalories(int cal) {
        this.targetCaloriesPerDay = cal;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setBirthdate(Date birthday){
        this.birthdate = birthday;
    }
    public double getTargetWeight(){
        return (int) (10 * weight + 6.25 * height - 5 * calculateAge());
    }
    public String getCurrentName(){
        return name;
    }
    public int getCurrentHeight(){
        return height;
    }
    public double getCurrentWeight(){
        return weight;
    }
    public ArrayList<Workout> getWorkouts(){
        return this.workouts;
    }

    public void joinTeam(Teams team_name){
        if(this.team == null){
            this.team = team_name;
            team_name.joinTeam(this);
        }
        else{
            System.out.println("You are already in a team!");
        }
    }
    public void leaveTeam(Teams team_name){
        if(this.team == team_name){
            team_name.leaveTeam(this);
            this.team = null;
        }
        else{
            System.out.println("You are not in a team!");
        }
    }
    public void addWorkout(String type, String intensity){
        WorkoutFactory workout_factory = new WorkoutFactory();
        Workout workout =  workout_factory.createWorkout(type, intensity);
        this.workouts.add(workout);
    }
    public void notify(String message){
        this.notifications.add(message);
    }

/*
    
    public void checkWeightChange(double currentWeight) {
        double previousWeight = 70.0; // assume this is the previously inputted weight
    
        if (currentWeight != previousWeight) {
            System.out.println("Weight has been changed from " + previousWeight + " to " + currentWeight);
            handleWeightChange(100);
        } else {
            System.out.println("Weight is unchanged at " + currentWeight);
        }
    }
    public void setGoal(Goal goal) {
        this.goal = goal;
        updateTargetCaloriesPerDay();
    }

    public void setTargetCalories(int cal) {
        this.targetCaloriesPerDay = cal;
        //updateTargetCaloriesPerDay();
    }

    private int calculateTargetCaloriesPerDay() {
        int bmr = (int) (10 * weight + 6.25 * height - 5 * calculateAge());
        switch (goal) {
            case LOSE:
                return (int) (bmr * 1.2) - 500;
            case GAIN:
                return (int) (bmr * 1.2) + 500;
            case MAINTAIN:
            default:
                return (int) (bmr * 1.2);
        }
    }

    public void updateTargetCaloriesPerDay() {
        this.targetCaloriesPerDay = calculateTargetCaloriesPerDay();
    }

    public void addExercise(int duration, ExerciseIntensity intensity) {
        int caloriesBurned = calculateCaloriesBurned(duration, intensity);
        this.exercisePerDay += duration;
        this.targetCaloriesPerDay -= caloriesBurned;
    }

    private int calculateCaloriesBurned(int duration, ExerciseIntensity intensity) {
        switch (intensity) {
            case LIGHT:
                return (int) (duration * weight * 3.5 / 200);
            case MODERATE:
                return (int) (duration * weight * 5 / 200);
            case VIGOROUS:
            default:
                return (int) (duration * weight * 7 / 200);
        }
    }
    */
    public void addIngredientToStock(Ingredient ingredient, double amount) {
        if (stock.containsKey(ingredient)) {
            amount += stock.get(ingredient);
        }
        stock.put(ingredient, amount);
    }

    public void deductIngredientsFromStock(Map<Ingredient, Double> ingredients) {
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double amount = entry.getValue();
            if (stock.containsKey(ingredient)) {
                double stockAmount = stock.get(ingredient);
                if (stockAmount >= amount) {
                    stock.put(ingredient, stockAmount - amount);
                } else {
                    System.out.println("Insufficient " + ingredient.getName() + " in stock.");
                }
            } else {
                System.out.println("No " + ingredient.getName() + " in stock.");
            }
        }
    }
}
