package teamThings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Workout.Workout;
import UserProfile.User;

public class Teams implements TeamOptions{
    private String team_name;
    private ArrayList<User> team_members;
    private ArrayList<User> invited_users;
    private boolean active_challenge = false;
    private String current_challenge;
    private HashMap<User, Integer> challenge_progress;
    private HashMap<User, ArrayList<Workout>> team_workouts;

    public Teams(String team_name){
        this.team_name = team_name;
        this.team_members = new ArrayList<User>();
        this.invited_users = new ArrayList<User>();
        this.challenge_progress = new HashMap<User, Integer>();
        this.team_workouts = new HashMap<User, ArrayList<Workout>>();
    }

    public Teams(String team_name, User team_creator){
        this.team_name = team_name;
        this.team_members = new ArrayList<User>();
        this.invited_users = new ArrayList<User>();
        this.challenge_progress = new HashMap<User, Integer>();
        this.team_workouts = new HashMap<User, ArrayList<Workout>>();

        this.joinTeamBypass(team_creator);
        
    }

    @Override
    public boolean joinTeam(User user) {
        if(this.invited_users.contains(user)){
            invited_users.remove(user);
            this.team_members.add(user);
            return true;
        }
        else{
            return false;
        }
    }

    public void joinTeamBypass(User user){
        this.team_members.add(user);
    }

    @Override
    public void leaveTeam(User user){
        if(team_members.contains(user)){
            team_members.remove(user);
            user.leaveTeam(this);
        }
        else{
            System.out.println("How did you even get here? Something went really wrong.");
        }
    }

    @Override
    public void sendInvite(User sender, User receiver) {
        if(team_members.contains(sender)){
            this.invited_users.add(receiver);
            receiver.notify("You have been invited to " + this.team_name + " by " + sender.getCurrentName());
        }
    }

    @Override
    public void viewHistory(String targetName) {
        User tempUser = null;
        for (User user : team_members) {
            if(user.getCurrentName() ==targetName){
                tempUser = user;
            }
        }
        if(tempUser != null){
            ArrayList<Workout> member_workouts = tempUser.getWorkouts();
            System.out.println("Workout history for "+ tempUser.getCurrentName() + ":");
            for (Workout workout : member_workouts) {
                System.out.println(workout.getDate() + ": " + workout.get_minutes() + "minutes");
            }
        }
        else{
            System.out.println("Target user could not be found");
        }
    }

    @Override
    public void logWorkout(User user, Workout workout) {
        if(this.team_members.contains(user)){
            this.team_workouts.get(user).add(workout);
            if(active_challenge == true){
                Integer minutes = this.challenge_progress.get(user);
                minutes += workout.get_minutes(); 
            }
            sendNotification(user, workout);
        } 
    }

    @Override
    public void sendNotification(User user, Workout workout) {
        for (User curr_user : team_members) {
            curr_user.notify(user.getCurrentName() + " has logged a workout for " + workout.get_minutes() + " minutes on " + workout.getDate());
        }
    }


    @Override
    public void setChallenge(Integer minutes) {
        if(this.active_challenge){
            System.out.println("There is already an active challenge!");
        }
        else{
            this.active_challenge = true;
            this.current_challenge = "Active challenge of " + minutes.toString() + " minutes this week.";
        }
    }

    @Override
    public void viewRanking() {
        Map<User, Integer> sorted = this.challenge_progress.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Comparator.reverseOrder()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted);
    }
}