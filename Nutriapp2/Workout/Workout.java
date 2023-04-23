package Nutriapp2.Workout;

import java.time.LocalDateTime;

public interface Workout {

    public void suggest();

    public int get_minutes();
    public LocalDateTime getDate();
    
}
