package Nutriapp2.State;

import UserProfile.User;

public class LoseWeight implements GoalState{
    public void setGoal(User user) {
        int targetCalories = calculateLoseCalories(user);
        user.setTargetCalories(targetCalories);
        System.out.println("Lose weight goal set.");
    }
    private int calculateLoseCalories(User user) {
        int bmr = (int) (10 * user.getCurrentWeight() + 6.25 * user.getCurrentHeight() - 5 * user.calculateAge());
        return (int) (bmr * 1.2) - 500;
        // Calculate target calories to lose weight
    }
}


