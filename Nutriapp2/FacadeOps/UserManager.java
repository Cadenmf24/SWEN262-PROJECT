package FacadeOps;

import java.util.HashMap;
import java.util.Map;


import teamThings.Teams;
import UserProfile.User;

public class UserManager {
    private Map<String, User> usernames = new HashMap<>();
    private static Map<String, String> users = new HashMap<>();
    private Map<String, Teams> teams = new HashMap<>();

    public static boolean authenticate(String username, String password) {
        if (!users.containsKey(username)) {
            return false; // username not found
        }
        System.out.println("Authentication was a success");
        return users.get(username).equals(password); // check if password matches
    }
    
    public void register(String username, String password) {
        // registers the user with the given username and password
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (password == null || password.isEmpty()) {
            System.out.println("Password cannot be null or empty");
        }
        // Check if the username already exists in the system
        else if (usernames.containsKey(username)) {
            System.out.println("Username already exists");
        }
        else{
        // Add the new user to the system
            users.put(username, password);
            User newuser = new User(username, 0, 0, null);
            usernames.put(username, newuser);
        }
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        // will change the password for the user with the given username and old password
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (oldPassword == null || oldPassword.isEmpty()) {
            System.out.println("Old password cannot be null or empty");
        }
        else if (newPassword == null || newPassword.isEmpty()) {
            System.out.println("New password cannot be null or empty");
        }
        // Check if the username exists in the system
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        // Check if the old password matches the current password
        else if (!users.get(username).equals(oldPassword)) {
            System.out.println("Old password is incorrect");
        }
        else{
            // Change the password for the user
            users.put(username, newPassword);
        }
    }

    public void sendTeamRequest(String username, String otherUsername) {
        // sends a team request from the user with the given username to the user with the given otherUsername
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (otherUsername == null || otherUsername.isEmpty()) {
            System.out.println("Other username cannot be null or empty");
        }
        // Check if the username exists in the system
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        // Check if the otherUsername exists in the system
        else if (!users.containsKey(otherUsername)) {
            System.out.println("Other username does not exist");
        }
        else{
            User tempUser = usernames.get(username);
            User tempUser2 = usernames.get(otherUsername);
            tempUser.sendInvite(tempUser2);
        }
    }

    public void acceptTeamRequest(String username, String otherUsername, String teamName) {
        // accepts a team request from the user with the given otherUsername to the user with the given username
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (otherUsername == null || otherUsername.isEmpty()) {
            System.out.println("Other username cannot be null or empty");
        }
        // Check if the username exists in the system
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        // Check if the otherUsername exists in the system
        else if (!users.containsKey(otherUsername)) {
            System.out.println("Other username does not exist");
        }
        else{
            User tempUser = usernames.get(otherUsername);
            Teams tempTeam = teams.get(teamName);
            tempUser.joinTeam(tempTeam);
        }
    }

    public void leaveTeam(String username) {
        // will remove the user with the given username from their team
        if (!users.containsKey(username) || !usernames.containsKey(username)) {
            System.out.println("User is not registered or authenticated.");
        }

        // Remove the user from their team
        User tempUser = usernames.get(username);
        tempUser.leaveTeam();
    }

    public void viewHistory(String username, String targetName){
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        else{
            User tempUser = usernames.get(username);
            tempUser.viewTeamMemberHistory(targetName);
        }
    }

    public void issueChallenge(String username, Integer minutes){
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        else{
            User tempUser = usernames.get(username);
            tempUser.issueChallenge(minutes);
        }
    }

    public void viewChallengeProgress(String username){
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        else{
            User tempUser = usernames.get(username);
            tempUser.viewChallengeProgress();
        }
    }

    public void createTeam(String teamname, String username){
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be null or empty");
        }
        else if (!users.containsKey(username)) {
            System.out.println("Username does not exist");
        }
        else{
            User tempUser = usernames.get(username);
            Teams team = new Teams(teamname, tempUser);
            teams.put(teamname, team);
            tempUser.createTeam(team);
        }
    }
}
