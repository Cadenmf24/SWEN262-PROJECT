package  Nutriapp2.Observer;

public class Goal implements UserListener{

    //private int weightGoal;

    @Override
    public void userChanged(UserEvent event) {
       //test 
       System.out.println("user changed goal");
    }
    
}
