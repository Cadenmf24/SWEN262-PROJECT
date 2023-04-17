package Nutriapp2.Workout;

public class MaintainWeightWorkout implements Workout{

    @Override
    public void suggest() {
        System.out.print("Do mixed workout ");
        throw new UnsupportedOperationException("Unimplemented method 'suggest'");
    }
    
}
