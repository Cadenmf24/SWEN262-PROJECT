package Nutriapp2.Workout;

public class GainWeightWorkout implements Workout{

    @Override
    public void suggest() {

        System.out.print("Do less workout");
        throw new UnsupportedOperationException("Unimplemented method 'suggest'");
    }
    
}
