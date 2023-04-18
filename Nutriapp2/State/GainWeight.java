package Nutriapp2.State;

import UserProfile.User;

public class GainWeight implements GoalState{

    public void setGoal(User user) {
        int targetCalories = calculateGainCalories(user);
        user.setTargetCalories(targetCalories);
        System.out.println("Gain weight goal set.");
    }
    private int calculateGainCalories(User user) {
        int bmr = (int) (10 * user.getCurrentWeight() + 6.25 * user.getCurrentHeight() - 5 * user.calculateAge());
        return (int) (bmr * 1.2) + 500;
        // Calculate target calories to gain weight
    }
}
