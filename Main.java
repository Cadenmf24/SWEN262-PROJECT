import java.io.*;
import java.util.Scanner;

import Database.Database;
import FacadeOps.FeatureManager;
import UserProfile.User;
import java.io.*;
public class Main {

    //private static final String SAVE_FILE_NAME = "user_data.txt";
    public static void main(String[] args) throws IOException{
        File SAVE_FILE_NAME = new File("user_data.txt");
        // Initialize the app
        User user;
        FeatureManager featureManager = null;
        Database database = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME))) {
            user = (User) inputStream.readObject();
            featureManager = (FeatureManager) inputStream.readObject();
            database = (Database) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load saved data. Creating new user and database.");
        }

        if (user == null && database == null) {
            user = new User(null, 0, 0, null);
            database = new Database();
        }
        
        else if (SAVE_FILE_NAME.exists()) {
            user = new User(SAVE_FILE_NAME);
            database = new Database();
        }

        /*// Display the user's profile
        System.out.println("Name: " + user.getCurrentName());
        System.out.println("Height: " + user.getCurrentHeight());
        System.out.println("Weight: " + user.getWeight());

        // Update the user's profile
        user.setName("Tom");
        user.setHeight(400);
        user.setWeight(125.05);
*/
        // Save the user's profile to a file
        user.saveToFile(SAVE_FILE_NAME);

        featureManager = new FeatureManager(user, database);

        // Run the app
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("Welcome to the Diet Tracker app. What would you like to do?");
            System.out.println("1. Enter user stats");
            System.out.println("2. Set goal");
            System.out.println("3. Add exercise");
            System.out.println("4. Add ingredient to stock");
            System.out.println("5. Create recipe");
            System.out.println("6. Create meal");
            System.out.println("7. Prepare meal");
            System.out.println("8. Generate shopping list");
            System.out.println("9. Track workouts");
            System.out.println("10. Suggest exercise");
            System.out.println("11. Browse history");
            System.out.println("12. Save daily activity");
            System.out.println("13. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    featureManager.enterUserStats();
                    break;
                case 2:
                    featureManager.setGoal();
                    break;
                case 3:
                    featureManager.trackWorkouts();
                    break;
                case 4:
                    featureManager.addIngredientToStock();
                    break;
                case 5:
                    featureManager.createRecipe();
                    break;
                case 6:
                    featureManager.createMeal();
                    break;
                case 7:
                    featureManager.prepareMeal(null);
                    break;
                case 8:
                    featureManager.generateShoppingList();
                    break;
                case 9:
                    featureManager.trackWorkouts();
                    break;
                case 10:
                    featureManager.suggestExercise();
                    break;
                case 11:
                    featureManager.browseHistory();
                    break;
                case 12:
                    featureManager.saveDailyActivity();
                    break;
                case 13:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 13.");
            }
        }

        // Save the user's data
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME))) {
            outputStream.writeObject(user);
            outputStream.writeObject(featureManager);
            outputStream.writeObject(database);
        } catch (IOException e) {
            System.out.println("Failed to save user data.");
        }

        System.out.println("Thank you for using the Diet Tracker app. Goodbye!");
    }
}
