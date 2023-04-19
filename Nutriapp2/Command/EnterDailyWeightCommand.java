package Nutriapp2.Command;

import Nutriapp2.UserProfile.User;

public class EnterDailyWeightCommand implements Command {
    private User user;
    private int Prev_weight;
    private int new_weight;
    
    public EnterDailyWeightCommand(User user, int weight) {
        this.user = user;
        this.Prev_weight = (int) user.getPreviousWeight(); //getweight()
        this.new_weight = weight;
    }
    
    public void execute() {
        // Enter the daily weight for the user
        user.setWeight(new_weight);
    }
    
    public void undo() {
        // Remove the daily weight for the user
        user.setWeight(Prev_weight);
    }
}
