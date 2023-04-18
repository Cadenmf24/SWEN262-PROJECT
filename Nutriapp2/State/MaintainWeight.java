package Nutriapp2.State;

import UserProfile.User;

class MaintainWeight implements GoalState {
    public void setGoal(User user) {
        int targetCalories = calculateMaintainCalories(user);
        user.setTargetCalories(targetCalories);
        System.out.println("Maintain weight goal set.");
    }

    private int calculateMaintainCalories(User user) {    
        int bmr = (int) (10 * user.getCurrentWeight() + 6.25 * user.getCurrentHeight() - 5 * user.calculateAge());
        return (int) (bmr * 1.2);
    }
}
