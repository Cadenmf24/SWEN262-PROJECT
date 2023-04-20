package Nutriapp2.Workout;

import java.time.LocalDate;

public interface Workout {

    public void suggest();

    public int get_minutes();
    public LocalDate getDate();
    
}
