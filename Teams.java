import java.util.ArrayList;
import java.util.HashMap;

public class Teams implements TeamOptions{

    private ArrayList<Users> team_members;
    private ArrayList<Users> invited_users;
    private boolean active_challenge = false;
    private String current_challenge;
    private HashMap<User, Integer > challenge_progress;
    

    @Override
    public void joinTeam(User user) {
        // check if user is in a team, then join if not already in one
        // maybe need to check if user is in a team on the user's side rather than here
    }

    @Override
    public void leaveTeam(User user){
        if(team_members.contains(user)){
            team_members.remove(user);
            //depends on user implementation to remove their own current team
        }
    }

    @Override
    public void sendInvite(User sender, User receiver) {
        if(team_members.contains(sender)){
            //sends notification to receiver that they have received a team invite
        }
        else{
            System.out.println("You are not in a team! Join a team to invite others.");
        }
    }

    @Override
    public void viewHistory(User user) {
        if(team_members.contains(user)){
            //returns history from target user
            // may request a string since a user shouldn't be able to access another user's fields
        }
    }

    @Override
    public void logWorkout() {
        // based on implementation of workout, Teams may have an arraylist of team members' workouts, and this would add to that list
        // also would update challenge progress if there is an active challenge
    }

    @Override
    public void sendNotification() {
        for (User curr_user : team_members) {
            //sends notification to each team member
            // will be called by logWorkout
        }
    }


    @Override
    public void setChallenge(Integer minutes) {
        if(this.active_challenge){
            System.out.println("There is already an active challenge!");
        }
        else{
            this.active_challenge = true;
            this.current_challenge = "Active challenge of " + minutes.toString() + "minutes this week.";
        }
    }

    @Override
    public void viewRanking() {
        //would sort users by minutes, then print out rankings
    }
    
}
