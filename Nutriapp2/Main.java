import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

import Command.AddIngredientCommand;
import Command.Command;
import Database.CSVDataImporter;
import Workout.Workout;
import food.Ingredient;
import food.Recipe;
//import Database.Database;
import FacadeOps.*;
import State.Goal;
import State.GoalState;
import UserProfile.User;

public class Main {
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);
    public SessionManager sessionManager = new SessionManager();
    public UserManager userManager = new UserManager();
    public FeatureManager featureManager = new FeatureManager();
    public RecipeManager recipeManager = new RecipeManager();
    public List<Pair> list;
    public HistoryManager historyManager = new HistoryManager(list);
    public CommandManager commandManager = new CommandManager();
    static List<Ingredient> ingredientsList = new ArrayList<>();
    public static void main(String[] args) throws IOException, CsvValidationException{
        Main tracker = new Main();
        CSVDataImporter csvDataImporter = new CSVDataImporter(); // Create the Adaptee object
        System.out.print("DataImporter about to be called\n");
        csvDataImporter.importData("NutriApp2/Database/ingredients.csv"); // Call the importData method on the Adapter
        ingredientsList = csvDataImporter.getData();
        tracker.run();
    }
    public void run() {
        while (true) {
            if (currentUser == null) {
                handleGuestMode();
            } else {
                handleUserMode();
            }
        }
    }

    private void handleGuestMode() {
        System.out.println("Welcome to NutritionTracker!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. BrowseStock");
        System.out.println("4. Quit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegistration();
                break;
            case 3:
                handleBrowseStock();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void handleLogin() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        try {
            UserManager.authenticate(username, password);
            sessionManager.login(username, password);
            currentUser = new User(username, 0, 0, null);
        } catch (Exception e) {   
            e.printStackTrace();
        }
        System.out.println("Log in was successful!");
    }

    private void handleRegistration() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        UserManager manager = new UserManager();
        try {
            manager.register(username, password);
            sessionManager.login(username, password);
        } catch (Exception e) {   
            e.printStackTrace();
        }
        System.out.println("Registration successful!");
    }
    
    private void handleBrowseStock() {
        System.out.println("Welcome! Here are some of our ingredients available in stock");
        System.out.print(ingredientsList + "\n");
    }
    private void handleUserMode() {
        System.out.println("Welcome, " + currentUser.getCurrentName()+ " to the Diet Tracker app. What would you like to do?");

        System.out.println("1. Browse stock");
        System.out.println("2. Change password");
        System.out.println("3. Send team request");
        System.out.println("4. Accept team request");
        System.out.println("5. Leave team");
        System.out.println("6. Log out");
        System.out.println("7. Update weight");
        System.out.println("8. Enter user stats");
        System.out.println("9. Set Goal");
        System.out.println("10. Add an Exercise");
        System.out.println("11. Add an ingredient to stock");
        System.out.println("12. Create a Recipe");
        System.out.println("13. Track your Workouts");
        System.out.println("14. Suggest an Exercise");
        System.out.println("15. Save Daily Activity");
        System.out.println("16. Quit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                handleBrowseStock();
                break;
            case 2:
                handleChangePassword();
                break;
            case 3:
                handleSendTeamRequest();
                break;
            case 4:
                handleAcceptTeamRequest();
                break;
            case 5:
                handleLeaveTeam();
                break;
            case 6:
                handleLogOut();
                currentUser = null;
                break;
            case 7:
                handleEnterWeight();
                break;
            case 8:
                handleEnterUserStats();
                break;
            case 9:
                handleUserSetGoal();
                break;
            case 10:
                handleWorkout();
                break;
            case 11:
                handleAddIngredientToStock();
                break;
            case 12:
                featureManager.createRecipe();
                break;
            case 13:
                handleTrackWorkout();
                break;
            case 14:
                handleBrowseHistory();
                break;
            case 15:
                handleSaveDailyActivity();;
                break;
            case 16:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void handleLogOut() {
        
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        try {
            sessionManager.logout(sessionManager.generateSessionKey(username));
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }//test fails need to update code in a way that can add the String goal chosen my the useer as their goal's current state
    public void handleUserSetGoal(){
        System.out.println("Choose a goal (Options: GainWeight, LoseWeight, or MaintainWeight):");
        String goalString = scanner.nextLine();
        Goal state = new Goal();
        Goal goalType = new Goal();
        goalType.setGoalType((GoalState) state);
        System.out.println(goalType.toString());
        currentUser.setGoal(goalString);
    }
    public void handleEnterWeight(){
        System.out.print("Enter weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        currentUser.addWeight(weight);
        currentUser.getPreviousWeight();
    }
    public void handleEnterUserStats(){
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your height in inches: ");
        int height = scanner.nextInt();
        System.out.print("Enter your weight in pounds: ");
        int weight = scanner.nextInt();
        System.out.print("Enter your birthdate in yyyy-MM-dd format: ");
        String birthdate = scanner.next();
        try {
            
            System.out.print("String birthdate was passed through, about to convert\n");
            Date date = featureManager.formatDateStringtoDate(birthdate);
            currentUser.setName(name);
            currentUser.setHeight(height);
            currentUser.setWeight(weight);
            currentUser.setBirthdate(date);
            System.out.printf("Username: ", currentUser.getCurrentName(), " Age: ", currentUser.calculateAge(), " Weight: ", currentUser.getCurrentWeight());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void handleWorkout(){
        System.out.print("Enter exercise type(Valid Commands: LoseWeight, GainWeight, MaintainWeigth): ");
        String type = scanner.nextLine();
        System.out.print("Enter exercise intensity (low, medium, high): ");
        String intensityString = scanner.next();
        Workout workout = featureManager.addExercise(type, intensityString);
        this.currentUser.addWorkout(workout);
    }
    public void handleRemoveIngredientFromStock(){
        System.out.print("Search for an ingredient: ");
        String query = scanner.nextLine();
        Ingredient ingredient = featureManager.findIngredientByName(ingredientsList, query);
        if (ingredient == null) {
            System.out.println("No results found.");
        } else {
            System.out.print("Enter quantity: ");
            double quantity = scanner.nextDouble();
            currentUser.deductIngredientsFromStock(ingredient, quantity);
        }
    }
    private void handleLeaveTeam() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        userManager.leaveTeam(username);
        System.out.println("Ties have been cut!");
    }
    public void handleAddIngredientToStock(){
        System.out.print("Search for an ingredient: ");
        String query = scanner.nextLine();
        Ingredient ingredient = featureManager.findIngredientByName(ingredientsList, query);
        if (ingredient == null) {
            System.out.println("No results found.");
        } else {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            //featureManager.addIngredientToStock(ingredient, quantity);
            Command addCommand = new AddIngredientCommand(ingredient, quantity);
            addCommand.execute();
            currentUser.addIngredientToStock(ingredient, quantity);
        }
        
    }
    private void handleSuggestWorkout(){
        System.out.print("Enter a target calories amount: ");
        int target = scanner.nextInt();
        System.out.println("Choose a goal (Options: GainWeight, LoseWeight, or MaintainWeight):");
        String goalString = scanner.nextLine();
        System.out.print("Enter exercise intensity (low, medium, high): ");
        String intense = scanner.nextLine();
        currentUser.setTargetCalories(target);
        int excessCalories = (int) (currentUser.updateTargetCaloriesPerDay(goalString) - currentUser.getTargetCalories());
        if (excessCalories > 0) {

            int exerciseDuration = (int) Math.ceil(excessCalories / currentUser.getCaloriesBurned(intense));
            System.out.printf("You have exceeded your daily calorie target by %d calories. "
                    + "Consider doing %d minutes of exercise to burn them off.%n", excessCalories, exerciseDuration);
        } else {
            System.out.println("You have not exceeded your daily calorie target.");
        }
    }

    private void handleChangePassword() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter current password:");
        String currentPassword = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        
        userManager.changePassword(username, currentPassword, newPassword);
        System.out.println("Password changed!");
    }

    private void handleSendTeamRequest() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter username:");
        String otherUsername = scanner.nextLine();
        userManager.sendTeamRequest(username, otherUsername);
        System.out.println("Team request has been sent over!");
    }

    private void handleAcceptTeamRequest() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter username:");
        String otherUsername = scanner.nextLine();
        userManager.acceptTeamRequest(username, otherUsername);
        System.out.println("Team request has been accepted!");
    }

    private void handleTrackWorkout(){
        ArrayList<Workout> workoutHistory =  currentUser.getWorkouts();
        for (Workout workout : workoutHistory) {
            System.out.println(workout);
        }
    }
    private void handleCreateRecipe(){
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your Instructions: ");
        String instructions = scanner.nextLine();
        Recipe recipe = new Recipe(name, instructions);
        recipe.setInstructions(instructions);
        recipe.setName(name);
        boolean addMoreIngredients = true;
        while (addMoreIngredients) {
            System.out.print("Search for an ingredient: ");
            String query = scanner.nextLine();
            Ingredient ingredient = featureManager.findIngredientByName(ingredientsList, query);
            if (ingredient == null) {
                System.out.println("No results found.");
            } else {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                recipe.addIngredient(ingredient, quantity);
            }
            System.out.print("Add another ingredient? (y/n): ");
            String addMoreIngredientsString = scanner.next();
            addMoreIngredients = addMoreIngredientsString.equalsIgnoreCase("y");
            //scanner.nextLine();
        }
    }
    private void handleSaveDailyActivity(){
        historyManager.addEntry(currentUser.getCurrentWeight(), currentUser.getWorkouts());
        System.out.print("Enter your current weight: ");
        double weight = scanner.nextDouble();
        currentUser.setWeight(weight);
    }
    private void handleBrowseHistory(){
        System.out.println("Personal History:");
        historyManager.getHistory();  //fix
        System.out.println("All User History Has been Returned");
    
    }
}