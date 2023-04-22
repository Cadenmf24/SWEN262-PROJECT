package Nutriapp2.teamThings;

import Nutriapp2.UserProfile.User;
import Nutriapp2.Workout.Workout;

public interface TeamOptions {
    
    public void joinTeam(User user);
    public void leaveTeam(User user);
    public void sendInvite(User sender, User receiver);
    public void viewHistory(User user);
    public void logWorkout(User user, Workout workout);

    public void sendNotification(User user, Workout workout);

    public void setChallenge(Integer minutes);
    public void viewRanking();

}
