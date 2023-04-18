package State;

public class LoseWeight implements GoalState{

    @Override
    public String toString() {
        return "Goal Set to [LoseWeight]";
    }

    @Override
    public void handleWeightGain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleWeightGain'");
    }

    @Override
    public void handleWeightLoss() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleWeightLoss'");
    }

    @Override
    public void setGoal(GoalState goalState) {
        goalState.setGoal(this);
        throw new UnsupportedOperationException("Unimplemented method 'setGoal'");
    }
}


