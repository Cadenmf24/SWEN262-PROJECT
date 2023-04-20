package UserProfile;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Command.AddTeamMemberCommand;
import Command.Command;
import Command.EnterDailyWeightCommand;
import Command.SetGoalsCommand;
import food.Ingredient;
import food.Meal;

public class User {
    private String name;
    private int height; // in centimeters
    private double weight; // in kilograms
    private Date birthdate;
    private String goal;
    private int targetCaloriesPerDay;
    private int exercisePerDay; // in minutes
    private Map<Ingredient, Double> stock;
    private Stack<Double> weights = new Stack<>();
    
    private List<Ingredient> ingredients;
    private List<Meal> meals;
    private List<User> teamMembers;
    private Stack<Command> undoStack;




    public User(String name, int height, double weight, Date birthdate) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
        this.exercisePerDay = 0;
        this.stock = new HashMap<Ingredient, Double>();
        undoStack = new Stack<>();
    }

    public User(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        this.name = reader.readLine();
        this.height = Integer.parseInt(reader.readLine());
        this.weight = Double.parseDouble(reader.readLine());
        reader.close();
    }
    
    
    public void setTargetCalories(int cal) {
        this.targetCaloriesPerDay = cal;
    }
    public void setExercisePerDay(int minutes){
        this.exercisePerDay = minutes;
    }
    
     // method for setting/changing goals
    public void setGoal(String state){
        this.goal = state;
        Command command = new SetGoalsCommand(this, state);
        command.execute();
        undoStack.push(command);
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
    public String getGoal(){
        return goal;
    }
    public double getTargetWeight(){
        return (double) (10 * weight + 6.25 * height - 5 * calculateAge());
    }
    public int updateTargetCaloriesPerDay(String goal){
        int bmr = (int) (10 * weight + 6.25 * height - 5 * calculateAge());
        switch (goal) {
            case "LoseWeight":
                return (int) (bmr * 1.2) - 500;
            case "GainWeight":
                return (int) (bmr * 1.2) + 500;
            case "MaintainWeight":
            default:
                return (int) (bmr * 1.2);
        }
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
    public double getTargetCalories(){
        return targetCaloriesPerDay;
    }
    public int getExercisePerDay(){
        return exercisePerDay;
    }

    // Save the user profile to a file
    public void saveToFile(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(name);
        writer.newLine();
        writer.write(Integer.toString(height));
        writer.newLine();
        writer.write(Double.toString(weight));
        writer.close();
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
    // method for entering daily weight
    public void enterWeight(double weight) {
        Command command = new EnterDailyWeightCommand(this, (int) weight);
        command.execute();
        undoStack.push(command);
    }

    // method for adding users to team
    public void addTeamMember(User user) {
        Command command = new AddTeamMemberCommand(this, user);
        command.execute();
        undoStack.push(command);
    }

    // method for undoing the last command
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command lastCommand = undoStack.pop();
            lastCommand.undo();
        }
    }

    public void addWeight(double weight) {
        weights.push(weight);
    }
    public String toString() {
        return "Username= '" + name + "'";
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
    public void addIngredientToStock(Ingredient ingredient, double amount) {
        if (stock.containsKey(ingredient)) {
            amount += stock.get(ingredient);
        }
        stock.put(ingredient, amount);
    }

    public void deductIngredientsFromStock(Map<Ingredient, Integer> ingredients) {
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int amount = entry.getValue();
            if (stock.containsKey(ingredient)) {
                double st = stock.get(ingredient);
                int stockAmount = (int) st;
                if (stockAmount >= amount) {
                    stock.put(ingredient, (double) (stockAmount - amount));
                } else {
                    System.out.println("Insufficient " + ingredient.getName() + " in stock.");
                }
            } else {
                System.out.println("No " + ingredient.getName() + " in stock.");
            }
        }
    }

    public void deductIngredientsFromStock(Ingredient ingredient, double amount) {
        if (stock.containsKey(ingredient)) {
            double stockAmount = stock.get(ingredient);
            if (stockAmount >= amount) {
                stock.put(ingredient, (double) (stockAmount - amount));
            } else {
                System.out.println("Insufficient " + ingredient.getName() + " in stock.");
            }
        } else {
            System.out.println("No " + ingredient.getName() + " in stock.");
        }
    }
    
    public int getCaloriesBurned(String tenString) {
        switch (tenString) {
            case "high":
                return 10 * exercisePerDay;
            case "medium":
                return 7 * exercisePerDay;
            case "low":
                return 5 * exercisePerDay;
            default:
                return 0;
        }
    }

    public Map getStock() {
        return null;
    }

    public void removeDailyWeight() {
    }

    public void removeTeamMember(User teamMember) {
    }

}

