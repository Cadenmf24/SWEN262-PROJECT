package Nutriapp2.Workout;

public class WorkoutFactory {
    
    public Workout createWorkout(String type){

        if (type == null || type.isEmpty()){
            System.out.print("Valid Commands: LoseWeight, GainWeight, MaintainWeigth");
        }
        
        switch (type){
            case "LoseWeight":
            return new LoseWeightWorkout();
            case "GainWeight":
            return new GainWeightWorkout();
            case "MaintainWeight":
            return new MaintainWeightWorkout();

            default: 
            throw new IllegalArgumentException("Unknown workout type " + type);
            }

        }
    }

