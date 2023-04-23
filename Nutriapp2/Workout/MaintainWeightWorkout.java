package Workout;
import java.util.Random;
import java.time.LocalDateTime;

public class MaintainWeightWorkout implements Workout{

    public double intensity;
    public int minutes; 
    public LocalDateTime date; 
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
        this.minutes = r.nextInt(60);
        this.date = LocalDateTime.now();
        this.calories = (int)difficulty * minutes;

    }

    @Override
    public void suggest() {
        System.out.print("Do mixed workout ");
    }
    

    @Override
    public int get_minutes() {
        return this.minutes;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }
    
    @Override
    public String toString() {
        return "MaintainWeight Workout for " + minutes + " minutes on " + date;
    }
    
}
