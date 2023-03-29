package Nutriapp2;

public class LoseWeight implements GoalState{

    @Override
    public void printGoal() {
        System.out.println("Losing Weight...");
        throw new UnsupportedOperationException("Unimplemented method 'printGoal'");
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
}


