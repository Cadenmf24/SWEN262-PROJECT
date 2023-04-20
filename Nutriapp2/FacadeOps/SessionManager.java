package Nutriapp2.FacadeOps;

import java.util.*;

//import FacadeOps.UserManager;
public class SessionManager {

    private Map<String, String> sessions; // map of session keys to usernames
    
    public SessionManager() {
        sessions = new HashMap<>();
    }
    
    // logs in a user with the given username and password and returns a session key
    public String login(String username, String password) throws Exception {
        // authenticate user with username and password
        if (!UserManager.authenticate(username, password)) {
            throw new Exception("Invalid username or password");
        }
        return generateSessionKey(username);
    }
    public String generateSessionKey(String username){
        // generate session key and store it in the map of sessions
        String sessionKey = UUID.randomUUID().toString();
        sessions.put(sessionKey, username);
        System.out.println(sessionKey);
        return sessionKey;
    }
    // logs out a user with the given session key
    public void logout(String sessionKey) throws Exception {
        // Check if the session key exists in the sessions HashMap
        if(sessions.containsKey(sessionKey)) {
            // Remove the session key from the sessions HashMap
            sessions.remove(sessionKey);
            System.out.println("User logged out successfully.");
        } else {
            // If the session key is not found in the sessions HashMap, the user is not logged in
            System.out.println("User is not logged in.");
        }
    }
    // returns the username associated with the given session key
    public String getUsername(String sessionKey) {
        return sessions.get(sessionKey);
    }
}
