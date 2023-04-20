package Nutriapp2.FacadeOps;

public class Pair {
    private Double doubleValue;
    private String stringValue;

    public Pair(Double doubleValue, String stringValue) {
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}