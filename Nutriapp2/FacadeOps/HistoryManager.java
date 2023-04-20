package FacadeOps;
//import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    List<Pair> history = new ArrayList<>();

    public HistoryManager(List<Pair> history) {
        this.history = history;
    }
    public void addEntry(double currentWeight, String exercises) {
        history.add(new Pair(currentWeight, exercises));
    }
    public List<Pair> getHistory(){
        //Pair myPair = history.get(index);
        //Double myDouble = myPair.getDoubleValue();
        //String myString = myPair.getStringValue();
        //Pair<Integer, String> values = new Pair<>(myDouble, myString);
        //return values;
        return history;
    }
}
