package Nutriapp2;


public interface GoalState{

    public void setGoal(GoalState goalState);

    public String toString();

    public void handleWeightGain();

    public void handleWeightLoss();


}

