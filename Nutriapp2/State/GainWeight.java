package Nutriapp2.State;

public class GainWeight implements GoalState{

    @Override
    public String toString() {
        return "Goal Set to [GainWeight]";
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
