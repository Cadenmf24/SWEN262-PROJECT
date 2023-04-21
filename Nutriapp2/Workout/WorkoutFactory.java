package Workout;

public class WorkoutFactory {
    
    public Workout createWorkout(String type, String intensity){

        if (type == null || type.isEmpty()){
            System.out.print("Valid Commands: LoseWeight, GainWeight, MaintainWeigth");
        }

        switch (type){
            case "LoseWeight":
            return new LoseWeightWorkout(intensity);
            case "GainWeight":
            return new GainWeightWorkout(intensity);
            case "MaintainWeight":
            return new MaintainWeightWorkout(intensity);

            default: 
            throw new IllegalArgumentException("Unknown workout type " + type);
            }

        }
    }

