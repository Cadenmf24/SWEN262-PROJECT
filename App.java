import java.util.Scanner;

import Database.Database;
import FacadeOps.FeatureManager;
import UserProfile.User;

public class App {
    private User user;
    private FeatureManager featureManager;
    private Database database;
    
    public App() {
        database = new Database();
        featureManager = new FeatureManager(database);
        user = new User(null, 0, 0, null);
    }
    
    public void initialize() {
        // load database
        database.load();
        
        // initialize user
        user = featureManager.enterUserStats();
        user.setGoal(featureManager.setGoal());
        user.;
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            // display menu
            System.out.println("Welcome to the Health App!\n"
                    + "Please choose from the following options:\n"
                    + "1. Add exercise\n"
                    + "2. Add ingredient to stock\n"
                    + "3. Create recipe\n"
                    + "4. Create meal\n"
                    + "5. Prepare meal\n"
                    + "6. Generate shopping list\n"
                    + "7. Track workouts\n"
                    + "8. Suggest exercise\n"
                    + "9. Browse history\n"
                    + "10. Save daily activity\n"
                    + "11. Exit");
            int choice = scanner.nextInt();
            
            // process command
            switch (choice) {
                case 1:
                    featureManager.trackWorkouts();
                    break;
                case 2:
                    featureManager.addIngredientToStock();
                    break;
                case 3:
                    featureManager.createRecipe();
                    break;
                case 4:
                    featureManager.createMeal();
                    break;
                case 5:
                    featureManager.prepareMeal();
                    break;
                case 6:
                    featureManager.generateShoppingList();
                    break;
                case 7:
                    featureManager.trackWorkouts();
                    break;
                case 8:
                    featureManager.suggestExercise();
                    break;
                case 9:
                    featureManager.browseHistory();
                    break;
                case 10:
                    featureManager.saveDailyActivity();
                    break;
                case 11:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        
        // save user data
        user.save();
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.initialize();
        app.run();
    }
}
