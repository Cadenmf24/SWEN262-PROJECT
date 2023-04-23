package teamThings;

import UserProfile.User;
import Workout.Workout;

public interface TeamOptions {
    
    public boolean joinTeam(User user);
    public void leaveTeam(User user);
    public void sendInvite(User sender, User receiver);
    public void viewHistory(String targetName);
    public void logWorkout(User user, Workout workout);

    public void sendNotification(User user, Workout workout);

    public void setChallenge(Integer minutes);
    public void viewRanking();

}
