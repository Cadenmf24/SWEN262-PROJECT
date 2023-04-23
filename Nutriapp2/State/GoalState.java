package State;


public interface GoalState{
    
    public void setGoal(GoalState goalState);

    public String toString();

    public GoalState update(int userWeight, int targetWeight);


}

