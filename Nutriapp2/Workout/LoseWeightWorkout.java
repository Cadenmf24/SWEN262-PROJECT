package Nutriapp2.Workout;

public class LoseWeightWorkout implements Workout{

    @Override
    public void suggest() {

        System.out.print("Do stuff to lose weight or something ");
        throw new UnsupportedOperationException("Unimplemented method 'suggest'");
    }
    
}
