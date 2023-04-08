package History;

// Defines the Command interface
public interface HistoryCommand {
    void execute();
    void undo();
    String getData();
}
