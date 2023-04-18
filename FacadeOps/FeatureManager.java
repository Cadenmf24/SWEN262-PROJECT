package FacadeOps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

import Database.Database;
import History.HistoryCommand;
import Nutriapp2.State.Goal;
import Nutriapp2.State.GoalState;
import UserProfile.User;
import food.*;

public class FeatureManager {
    private User user;
    private Database database;
    private HistoryCommand history;
    private Recipe recipeManager;
    //private ExerciseLog exerciseLog;

    public FeatureManager() {
        this.user = new User(null, 0, 0, null);
        this.database = new Database();
        this.history = new HistoryCommand();
        this.recipeManager = new RecipeManager();
        //this.exerciseLog = exerciseLog;
    }
    
    public void accessAllFeatures() {
        // Gives authenticated users access to all features of the current system
        
    }
    
    public void enterWeight() {
        Weight tracker = new Weight();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter weight: ");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character

            tracker.addWeight(weight);
            tracker.getPreviousWeight();
        }
    }
    
    public void enterUserStats() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your height in inches: ");
        int height = scanner.nextInt();
        System.out.print("Enter your weight in pounds: ");
        int weight = scanner.nextInt();
        System.out.print("Enter your birthdate in yyyy-MM-dd format: ");
        String birthdate = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(birthdate);
        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setBirthdate(date);
        scanner.close();
    }

    public void setGoal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a goal:");
        for (GoalState goalType : GoalState) {
            System.out.println(goalType);
        }
        String goalString = scanner.nextLine();
        GoalState goalType = GoalType.valueOf(goalString.toUpperCase());
        user.setGoal(goalType);
        scanner.close();
    }

    public void addExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter exercise name: ");
        String name = scanner.nextLine();
        System.out.print("Enter exercise duration in minutes: ");
        int duration = scanner.nextInt();
        System.out.print("Enter exercise intensity (LOW, MEDIUM, HIGH): ");
        String intensityString = scanner.next();
        ExerciseIntensity intensity = ExerciseIntensity.valueOf(intensityString.toUpperCase());
        user.addExercise(duration, intensity);
        scanner.close();
    }

    public void addIngredientToStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for an ingredient: ");
        String query = scanner.nextLine();
        ArrayList<Ingredient> ingredient = database.searchIngredients(query);
        if (ingredient == null) {
            System.out.println("No results found.");
        } else {
            System.out.print("Enter quantity: ");
            double quantity = scanner.nextDouble();
            user.addIngredientToStock(ingredient, quantity);
        }
        scanner.close();
    }

    public void createRecipe() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        Recipe recipe = new Recipe(name, name);
        boolean addMoreIngredients = true;
        while (addMoreIngredients) {
            System.out.print("Search for an ingredient: ");
            String query = scanner.nextLine();
            ArrayList<Ingredient> ingredient = database.searchIngredients(query);
            if (ingredient == null) {
                System.out.println("No results found.");
            } else {
                System.out.print("Enter quantity: ");
                double quantity = scanner.nextDouble();
                recipe.addIngredient(ingredient, quantity);
            }
            System.out.print("Add another ingredient? (y/n): ");
            String addMoreIngredientsString = scanner.next();
            addMoreIngredients = addMoreIngredientsString.equalsIgnoreCase("y");
            scanner.nextLine();
        }
        System.out.print("Enter preparation instructions: ");
        String instructions = scanner.nextLine();
        recipe.setInstructions(instructions);
        recipeManager.addRecipe(recipe);
        scanner.close();
    }

    public void createMeal() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Let's create a meal!");
        List<Recipe> recipes = new ArrayList<>();
        while (true) {
            System.out.println("Choose a recipe to add to the meal (or type 'done' to finish):");
            String input = scanner.nextLine();
            if (input.equals("done")) {
                break;
            }
            Recipe recipe = recipeManager.searchRecipes(input);
            if (recipe == null) {
                System.out.println("No recipe found for \"" + input + "\".");
                continue;
            }
            recipes.add(recipe);
            System.out.println("Added \"" + recipe.getName() + "\" to the meal.");
        }
        Meal meal = new Meal(recipes);
        System.out.println("Meal created: " + meal);
        prepareMeal(meal);
    }

    public void prepareMeal(Meal meal) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's prepare the meal \"" + meal.getName() + "\"!");
        for (Recipe recipe : meal.getRecipes()) {
            System.out.println("Preparing recipe \"" + recipe.getName() + "\"...");
            for (Map.Entry<Ingredient, Double> entry : recipe.getIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                double amount = entry.getValue() * meal.getServings();
                System.out.println(" - " + amount + " " + ingredient.getUnit() + " of " + ingredient.getName());
                user.deductIngredientFromStock(ingredient, amount);
            }
        }
        user.updateTargetCaloriesPerDay();
        System.out.println("Meal prepared!");
    }

    public void generateShoppingList() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's generate a shopping list!");
        System.out.println("Enter a minimum quantity for ingredients (leave blank for no minimum):");
        String minQuantityString = scanner.nextLine();
        double minQuantity = minQuantityString.isEmpty() ? 0 : Double.parseDouble(minQuantityString);
        System.out.println("Enter a maximum price per unit for ingredients (leave blank for no maximum):");
        String maxPriceString = scanner.nextLine();
        double maxPrice = maxPriceString.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceString));

        Map<Ingredient, Double> shoppingList = new HashMap<>();
        for (Ingredient ingredient : database.searchIngredients("")) {
            if (user.getStock().containsKey(ingredient)) {
                continue;
            }
            if (minQuantity > 0 && user.getStock().getOrDefault(ingredient, 0.0) >= minQuantity) {
                continue;
            }
            if (maxPrice < Double.MAX_VALUE && ingredient.getPricePerUnit() > maxPrice) {
                continue;
            }
            shoppingList.put(ingredient, ingredient.getBaseAmount());
        }
        System.out.println("Shopping list generated: " + shoppingList);
    }

    
    public void trackWorkouts() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the exercise name: ");
        String exerciseName = scanner.nextLine();
        System.out.print("Enter the duration in minutes: ");
        int duration = scanner.nextInt();
        System.out.print("Enter the intensity (LOW, MEDIUM, HIGH): ");
        ExerciseIntensity intensity = ExerciseIntensity.valueOf(scanner.next().toUpperCase());
        
        Exercise exercise = new Exercise(exerciseName, duration, intensity);
        exerciseLog.addExercise(exercise);
        
        System.out.println("Exercise added to log.");
    }
    
    public void suggestExercise() {
        int excessCalories = user.getDailyCalories() - user.getTargetCaloriesPerDay();
        if (excessCalories > 0) {
            int exerciseDuration = (int) Math.ceil(excessCalories / user.getCaloriesBurnedPerMinute());
            System.out.printf("You have exceeded your daily calorie target by %d calories. "
                    + "Consider doing %d minutes of exercise to burn them off.%n", excessCalories, exerciseDuration);
        } else {
            System.out.println("You have not exceeded your daily calorie target.");
        }
    }
    
    public void browseHistory() {
        System.out.println("Personal History:");
        for (HistoryEntry entry : historyManager.getHistory()) {
            System.out.println(entry);
        }
    }
    
    public void saveDailyActivity() {
        Scanner scanner = new Scanner(System.in);
        historyManager.addEntry(user.getWeight(), exerciseLog.getExercises());
        System.out.print("Enter your current weight: ");
        double weight = scanner.nextDouble();
        user.setWeight(weight);
    }

}
