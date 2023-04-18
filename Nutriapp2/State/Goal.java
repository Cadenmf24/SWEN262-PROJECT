package Nutriapp2.State;

import UserProfile.User;

public class Goal{

    private GoalState state;
    private User user;

    public Goal(User user) {
        this.user = user;
        this.state = new MaintainWeight();
    }

    public void setGoalState(GoalState state) {
        this.state = state;
    }

    public void setGoal() {
        state.setGoal(user);
    }

    //Trying to sort of handle user input on weight and transition to their appropriate goal based on changed weight
    public void handleWeightChange(int weightChange) {
        double currentWeight = user.getCurrentWeight();
        double previousWeight = user.getPreviousWeight();
        if (Math.abs(weightChange) >= 5) {
            if (weightChange > 0) {
                setGoalState(new GainWeight());
            } else {
                setGoalState(new LoseWeight());
            }
            setGoal();
        } else if (currentWeight == user.getTargetWeight() && previousWeight != currentWeight) {
            setGoalState(new MaintainWeight());
            setGoal();
        }
    }

}