package State;

public class GainWeight implements GoalState{

    @Override
    public String toString() {
        return "Goal Set to [GainWeight]";
    }

    @Override
    public void handleWeightGain() {
        
    }

    @Override
    public void handleWeightLoss() {
        
    }

    @Override
    public void setGoal(GoalState goalState) {
        goalState.setGoal(this);
    }
    
}
