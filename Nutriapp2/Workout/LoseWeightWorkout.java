package Workout;
import java.util.Random;
import java.time.LocalDate;

public class LoseWeightWorkout implements Workout{

    public double intensity;
    public int minutes; 
    public LocalDate date; 
    public int calories;

    public LoseWeightWorkout(String intensity){
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

        System.out.print("Do stuff to lose weight or something ");
        
    }

    @Override
    public int get_minutes() {
        return this.minutes;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }
    
}
