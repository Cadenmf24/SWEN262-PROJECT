package FacadeOps;

import java.util.ArrayList;

import Workout.Workout;

public class Pair {
    private Double doubleValue;
    private ArrayList<Workout> stringValue;

    public Pair(Double doubleValue, ArrayList<Workout> exercises) {
        this.doubleValue = doubleValue;
        this.stringValue = exercises;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public ArrayList<Workout> getStringValue() {
        return stringValue;
    }

    public void setStringValue(ArrayList<Workout> stringValue) {
        this.stringValue = stringValue;
    }
}