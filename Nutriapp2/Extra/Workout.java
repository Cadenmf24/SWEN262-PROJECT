package Extra;

import java.time.LocalDateTime;

public class Workout {
    private int minutes;
    private String intensity;
    private LocalDateTime dateTime;

    public Workout(int minutes, String intensity) {
        this.minutes = minutes;
        this.intensity = intensity;
        this.dateTime = LocalDateTime.now();
    }

    public int getCaloriesBurned() {
        switch (intensity) {
            case "high":
                return 10 * minutes;
            case "medium":
                return 7 * minutes;
            case "low":
                return 5 * minutes;
            default:
                return 0;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}