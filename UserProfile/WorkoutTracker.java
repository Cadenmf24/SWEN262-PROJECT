package UserProfile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutTracker {
    private List<Workout> workouts;

    public WorkoutTracker() {
        this.workouts = new ArrayList<>();
    }

    public void addWorkout(int minutes, String intensity) {
        Workout workout = new Workout(minutes, intensity);
        workouts.add(workout);
    }

    public int getTotalCaloriesBurned() {
        int totalCalories = 0;
        for (Workout workout : workouts) {
            totalCalories += workout.getCaloriesBurned();
        }
        return totalCalories;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public List<Workout> getWorkoutsByDate(LocalDateTime date) {
        List<Workout> workoutsByDate = new ArrayList<>();
        for (Workout workout : workouts) {
            if (workout.getDateTime().toLocalDate().equals(date.toLocalDate())) {
                workoutsByDate.add(workout);
            }
        }
        return workoutsByDate;
    }

    public int suggestExercise(int excessCalories) {
        int suggestedMinutes = excessCalories / 5;
        if (suggestedMinutes == 0) {
            return 0;
        } else if (suggestedMinutes > 60) {
            suggestedMinutes = 60;
        }
        return suggestedMinutes;
    }
}
