import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

import Database.CSVDataImporter;
//import Database.Database;
import FacadeOps.CommandManager;
import FacadeOps.FeatureManager;
import FacadeOps.RecipeManager;
import FacadeOps.SessionManager;
import FacadeOps.HistoryManager;
import FacadeOps.Pair;
import FacadeOps.UserManager;
import GuestMode.IngredientManager;
import UserProfile.User;

public class Main {
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);
    public SessionManager sessionManager = new SessionManager();
    public UserManager userManager = new UserManager();
    public FeatureManager featureManager = new FeatureManager();
    public RecipeManager recipeManager = new RecipeManager();
    //public List<Pair> list = new Pair(null, null);
    public HistoryManager historyManager = new HistoryManager(null);
    public CommandManager commandManager = new CommandManager();
    public IngredientManager ingredientManager = new IngredientManager(null);

    public static void main(String[] args) throws IOException, CsvValidationException{
        Main tracker = new Main();
        CSVDataImporter csvDataImporter = new CSVDataImporter(); // Create the Adaptee object
        csvDataImporter.importData("ingredients.csv"); // Call the importData method on the Adapter
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
        //UserManager manager = new UserManager();
        try {
            userManager.register(username, password);
            sessionManager.login(username, password);
        } catch (Exception e) {   
            e.printStackTrace();
        }
        System.out.println("Registration successful!");
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
        System.out.println("16. View Workouts of Team Member");
        System.out.println("17. Issue Team Challenge");
        System.out.println("18. View Challenge Ranking");
        System.out.println("19. Quit");
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
                featureManager.enterWeight();;
                break;
            case 8:
                try {
                    featureManager.enterUserStats();
                } catch (ParseException e) {
                    e.printStackTrace();
                };
                break;
            case 9:
                featureManager.setGoal();
                break;
            case 10:
                featureManager.addExercise();
                break;
            case 11:
                featureManager.addIngredientToStock();
                break;
            case 12:
                featureManager.createRecipe();
                break;
            case 13:
                featureManager.trackWorkouts();
                break;
            case 14:
                featureManager.suggestExercise();
                break;
            case 15:
                featureManager.saveDailyActivity();
                break;
            case 16:
                handleViewWorkoutHistory();
            case 17:
                handleIssueChallenge();
            case 18:
                handleViewChallengeProgress();
            case 19:
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
                
    }
    private void handleLeaveTeam() {
        userManager.leaveTeam(this.currentUser.getCurrentName());
        System.out.println("Ties have been cut!");
    }
    private void handleBrowseStock() {
        System.out.println("Welcome! Here are some of our ingredients available in stock");
        ingredientManager.getIngredients();
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
        System.out.println("Enter receiver's username:");
        String username = scanner.nextLine();
        userManager.sendTeamRequest(this.currentUser.getCurrentName(), username);
    }

    private void handleAcceptTeamRequest() {
        System.out.println("Enter sender's username:");
        String username = scanner.nextLine();
        System.out.println("Enter the team you would like to join:");
        String teamname = scanner.nextLine();
        userManager.acceptTeamRequest(username, this.currentUser.getCurrentName(), teamname);
    }

    private void handleViewWorkoutHistory(){
        System.out.println("Enter username of target:");
        String targetUsername = scanner.nextLine();
        userManager.viewHistory(this.currentUser.getCurrentName(), targetUsername);
    }

    private void handleIssueChallenge(){
        System.out.println("Enter target minutes for the week:");
        Integer targetMinutes = scanner.nextInt();
        userManager.issueChallenge(this.currentUser.getCurrentName(), targetMinutes);
    }

    private void handleViewChallengeProgress(){
        userManager.viewChallengeProgress(this.currentUser.getCurrentName());
    }
    
}