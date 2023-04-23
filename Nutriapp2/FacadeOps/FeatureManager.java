package FacadeOps;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Command.AddIngredientCommand;
import Command.Command;
import State.*;
//import Command.HistoryCommand;
import State.Goal;
import State.GoalState;
import State.MaintainWeight;
import UserProfile.User;
import Workout.WorkoutFactory;
import food.*;
import Workout.*;
import GuestMode.*;;

public class FeatureManager {
    private User user;
    //private Database database;
    private HistoryManager historyManager = new HistoryManager(null);
    private RecipeManager recipeManager;
    public IngredientManager ingredientManager = new IngredientManager(null);
    GoalState state;
    
    public FeatureManager() {
        this.user = new User(null, 0, 0, null);
    }
    public void accessAllFeatures() {
        // Gives authenticated users access to all features of the current system
    
    }
    public Date formatDateStringtoDate(String birthdate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(birthdate);
        return date;
    }
    public void setGoal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a goal (Options: GainWeight, LoseWeight, or MaintainWeight):");
        String goalString = scanner.nextLine();
        switch(goalString)
        {
            case "GainWeight":
            state = new GainWeight();
            break;
            case "LoseWeight":
            state = new LoseWeight();
            break;
            case "MaintainWeight":
            state = new MaintainWeight();
            break;
            default:
            state = new MaintainWeight();
        }
        Goal goalType = new Goal();
        goalType.setGoalType(state);
        System.out.println(goalType.toString());
        user.setGoal(goalString);
    }
    public Workout addExercise(String type, String intensityString) {
        WorkoutFactory wkt = new WorkoutFactory();
        return wkt.createWorkout(type, intensityString);
        
    }

    public void addIngredientToStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for an ingredient: ");
        String query = scanner.nextLine();
        Ingredient ingredient = findIngredientByName(ingredientManager.getIngredients(), query);
        if (ingredient == null) {
            System.out.println("No results found.");
        } else {
            System.out.print("Enter quantity: ");
            double quantity = scanner.nextDouble();
            
            Command addCommand = new AddIngredientCommand(ingredient, (int) quantity);
            addCommand.execute();
            user.addIngredientToStock(ingredient, quantity);
        }
        scanner.close();
    }

    public void RemoveIngredientToStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for an ingredient: ");
        String query = scanner.nextLine();
        Ingredient ingredient = findIngredientByName(ingredientManager.getIngredients(), query);
        if (ingredient == null) {
            System.out.println("No results found.");
        } else {
            System.out.print("Enter quantity: ");
            double quantity = scanner.nextDouble();
            
            Command addCommand = new AddIngredientCommand(ingredient, (int) quantity);
            addCommand.undo();
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
            Ingredient ingredient = findIngredientByName(ingredientManager.getIngredients(), query);
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
        //List<Recipe> recipes = new ArrayList<>();
        //Meal recipes = new Meal(input);
        while (true) {
            System.out.println("Choose a recipe to add to the meal (or type 'done' to finish):");
            String input = scanner.nextLine();
            Meal recipes = new Meal(input);
            if (input.equals("done")) {
                break;
            }
            Recipe recipe = recipeManager.searchRecipes(input);
            if (recipe == null) {
                System.out.println("No recipe found for \"" + input + "\".");
                continue;
            }
            recipes.addRecipe(recipe);
            System.out.println("Added \"" + recipe.getName() + "\" to the meal.");
            System.out.println("Meal created: " + recipes);
            
        }
        scanner.close();
        //Might create a Second Version of prepareMeal();
    }
    public void prepareMeal(Meal meal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's prepare the meal \"" + meal.getName() + "\"!");
        for (Recipe recipe : meal.getRecipes()) {
            System.out.println("Preparing recipe \"" + recipe.getName() + "\"...");
            for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                int amount = entry.getValue() * meal.getServings();
                System.out.println(" - " + amount + " " + ingredient.getUnit() + " of " + ingredient.getName());
                user.deductIngredientsFromStock(ingredient, (double) amount);
            }
        }
        user.updateTargetCaloriesPerDay(user.getGoal());
        scanner.close();
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
        double maxPrice = maxPriceString.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceString);
        Map<Ingredient, Double> shoppingList = new HashMap<>();
        for (Ingredient ingredient : ingredientManager.getIngredients()) { //database
            if (user.getStock().containsKey(ingredient)) {
                continue;
            }
            if (minQuantity > 0 &&  100 >= minQuantity) { //user.getStock().getOrDefault(ingredient, 0.0)
                continue;
            }
            if (maxPrice < Double.MAX_VALUE && ingredient.getPricePerUnit() > maxPrice) {
                continue;
            }
            shoppingList.put(ingredient, ingredient.getBaseAmount());
        }
        scanner.close();
        System.out.println("Shopping list generated: " + shoppingList);
    }
    public void trackWorkouts() {
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Enter exercise type(Valid Commands: LoseWeight, GainWeight, MaintainWeigth): ");
        // String type = scanner.nextLine();
        // //System.out.print("Enter the duration in minutes: ");
        // //int duration = scanner.nextInt();
        // System.out.print("Enter exercise intensity (low, medium, high): ");
        // String intensity = scanner.nextLine(); //valueOf(scanner.next().toUpperCase());
        // scanner.close();
        // System.out.println("Exercise added to log.");
        
    }
    
    public void suggestExercise() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a target calories amount: ");
        int target = scanner.nextInt();
        System.out.println("Choose a goal (Options: GainWeight, LoseWeight, or MaintainWeight):");
        String goalString = scanner.nextLine();
        System.out.print("Enter exercise intensity (low, medium, high): ");
        String intense = scanner.nextLine();
        user.setTargetCalories(target);
        int excessCalories = (int) (user.updateTargetCaloriesPerDay(goalString) - user.getTargetCalories());
        if (excessCalories > 0) {

            int exerciseDuration = (int) Math.ceil(excessCalories / user.getCaloriesBurned(intense));
            System.out.printf("You have exceeded your daily calorie target by %d calories. "
                    + "Consider doing %d minutes of exercise to burn them off.%n", excessCalories, exerciseDuration);
        } else {
            System.out.println("You have not exceeded your daily calorie target.");
        }
        scanner.close();
    }
    
    public void browseHistory() {
        System.out.println("Personal History:");
        historyManager.getHistory();  //fix
        System.out.println("All User History Has been Returned");
    }
    
    public void saveDailyActivity() {
        Scanner scanner = new Scanner(System.in);
        historyManager.addEntry(user.getCurrentWeight(), (String) exerciseLog.getExercises());
        System.out.print("Enter your current weight: ");
        double weight = scanner.nextDouble();
        user.setWeight(weight);
        scanner.close();
    }

    public Ingredient findIngredientByName(List<Ingredient> ingredients, String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null; // ingredient not found
    }
    
}
