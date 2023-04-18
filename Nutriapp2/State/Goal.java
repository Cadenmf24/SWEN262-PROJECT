package State;

public class Goal{

    private GoalState state;

    public void setGoalType(GoalState state){
        this.state = state;
    }

    public GoalState getGoalType(){
        return this.state;
    }

    public String toString(){
        return state.toString();
    }
}