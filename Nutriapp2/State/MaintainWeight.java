package Nutriapp2.State;

public class MaintainWeight implements GoalState{

    @Override
    public String toString() {
        return "Goal is set to [Maintaining Weight]";
    }

    @Override
    public void handleWeightGain() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleWeightLoss() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setGoal(GoalState goalState) {
        goalState.setGoal(this);
        
    }
    
}
