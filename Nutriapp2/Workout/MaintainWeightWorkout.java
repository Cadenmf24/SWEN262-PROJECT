package Workout;
import java.util.Random;
import java.time.LocalDate;

public class MaintainWeightWorkout implements Workout{

    public double intensity;
    public int minutes; 
    public LocalDate date; 
    public int calories;

    public MaintainWeightWorkout(String intensity){
        double difficulty;
        Random r = new Random();

        switch (intensity){
            case "low":
            difficulty = 5;
            case "medium":
            difficulty = 7.5;
            case "high":
            difficulty = 10;
            default:
            difficulty = 1;
        }

        this.intensity = difficulty;
        this.minutes = r.nextInt((int)difficulty - 1 * 10);
        this.date = LocalDate.now();
        this.calories = (int)difficulty * minutes;

    }

    @Override
    public void suggest() {
        System.out.print("Do mixed workout ");
    }
    
}
