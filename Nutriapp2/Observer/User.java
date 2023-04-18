package Nutriapp2.Observer;

import java.util.List;

public class User implements Component{

    private List<Goal> goals;

    @Override
    public void register(Goal goal) {
       goals.add(goal);
    }

    @Override
    public void deregister(Goal goal) {
        goals.remove(goal);
    }

    @Override
    public void notifyListener(UserEvent userEvent) {
        for(Goal g: goals){
            g.userChanged(userEvent);
        }
    }
    
}
