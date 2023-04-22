package Nutriapp2.Command;

import Nutriapp2.UserProfile.User;

public class SetGoalsCommand implements Command {
    private User user;
    private String newGoal;
    private String oldGoal;
    
    public SetGoalsCommand(User user, String newGoal) {
        this.user = user;
        this.newGoal = newGoal;
        this.oldGoal = user.getGoal();
    }
    
    public void execute() {
        // Change the user's current goal
        user.changeGoal(newGoal);
    }
    
    public void undo() {
        // Change the user's current goal back to the old goal
        user.setGoal(oldGoal);
    }
}

