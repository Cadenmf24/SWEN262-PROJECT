package Nutriapp2;


public class Goal{

    private GoalState state = new LoseWeight();

    public void setGoatType(GoalState state){
        this.state = state;
    }

    public GoalState getGoalType(){
        return this.state;
    }

    public void printGoal(){
        state.printGoal();
    }
}