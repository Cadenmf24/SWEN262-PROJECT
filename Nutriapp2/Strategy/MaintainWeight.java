package Strategy;

public class MaintainWeight implements StrategyGoal{

    private int desiredCalories;
    private int desiredWeight;

    @Override
    public void setStrategy(StrategyGoal strategyGoal) {
        strategyGoal.setStrategy(this);
    }

    @Override
    public int execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
