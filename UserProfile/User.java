package UserProfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Nutriapp2.State.Goal;
import Nutriapp2.State.GoalState;
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
    private Stack<Double> weights = new Stack<>();;


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
