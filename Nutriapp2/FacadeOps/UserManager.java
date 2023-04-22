package FacadeOps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserManager {
    private Set<String> usernames = new HashSet<>();
    private static Map<String, String> users = new HashMap<>();
    private Map<String, Set<String>> teamRequestsMap = new HashMap<>();
    private Map<String, Set<String>> userTeams = new HashMap<>();
    private Map<String, String> teams = new HashMap<>();


    
    public static boolean authenticate(String username, String password) {
        if (!users.containsKey(username)) {
            return false; // username not found
        }
        //System.out.println("Authentication was a success");
        return users.get(username).equals(password); // check if password matches
    }
    public void register(String username, String password) {
        // registers the user with the given username and password
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        // Check if the username already exists in the system
        if (usernames.contains(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
    
        // Add the new user to the system
        users.put(username, password);
        usernames.add(username);
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        // will change the password for the user with the given username and old password
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (oldPassword == null || oldPassword.isEmpty()) {
            throw new IllegalArgumentException("Old password cannot be null or empty");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("New password cannot be null or empty");
        }
        
        // Check if the username exists in the system
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("Username does not exist");
        }
        
        // Check if the old password matches the current password
        if (!users.get(username).equals(oldPassword)) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
    
        // Change the password for the user
        users.put(username, newPassword);
    }

    public void sendTeamRequest(String username, String otherUsername) {
        // sends a team request from the user with the given username to the user with the given otherUsername
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (otherUsername == null || otherUsername.isEmpty()) {
            throw new IllegalArgumentException("Other username cannot be null or empty");
        }
        
        // Check if the username exists in the system
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("Username does not exist");
        }
        
        // Check if the otherUsername exists in the system
        if (!users.containsKey(otherUsername)) {
            throw new IllegalArgumentException("Other username does not exist");
        }
    
        // Add the username to the Set of team requests for the otherUsername
        // If the otherUsername is not already a key in the teamRequests map, add it with an empty Set as the value
        Set<String> teamRequests = teamRequestsMap.getOrDefault(otherUsername, new HashSet<>());
        teamRequests.add(username);
        teamRequestsMap.put(otherUsername, teamRequests);
    }

    public void acceptTeamRequest(String username, String otherUsername) {
        // accepts a team request from the user with the given otherUsername to the user with the given username
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (otherUsername == null || otherUsername.isEmpty()) {
            throw new IllegalArgumentException("Other username cannot be null or empty");
        }
    
        // Check if the username exists in the system
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("Username does not exist");
        }
    
        // Check if the otherUsername exists in the system
        if (!users.containsKey(otherUsername)) {
            throw new IllegalArgumentException("Other username does not exist");
        }
    
        // Check if the otherUsername has sent a team request to the username
        Set<String> teamRequests = teamRequestsMap.get(otherUsername);
        if (teamRequests == null || !teamRequests.contains(username)) {
            throw new IllegalArgumentException("Other user has not sent a team request to the user");
        }
    
        // Add the otherUsername to the team of the username
        Set<String> team = userTeams.getOrDefault(username, new HashSet<>());
        team.add(otherUsername);
        userTeams.put(username, team);
    
        // Remove the team request from the otherUsername's team requests
        teamRequests.remove(username);
    }

    public void leaveTeam(String username) {
        // will remove the user with the given username from their team
        if (!users.containsKey(username) || !usernames.contains(username)) {
            throw new IllegalArgumentException("User is not registered or authenticated.");
        }

        // Check if the user is part of a team
        if (!teams.containsKey(username)) {
            throw new IllegalStateException("User is not part of a team.");
        }

        // Remove the user from their team
        String teamName = teams.get(username);
        Set<String> teamMembers = userTeams.get(teamName);
        teamMembers.remove(username);
        teams.remove(username);

        // Notify the remaining team members
        for (String member : teamMembers) {
            System.out.printf(member, username + " has left the team.");
        }
    }
}
