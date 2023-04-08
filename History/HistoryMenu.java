package History;

import java.util.ArrayList;
import java.util.List;

public class HistoryMenu {
    private List<HistoryCommand> commandHistory = new ArrayList<>();

    public void executeCommand(HistoryCommand command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            HistoryCommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
    public List<String> getCommandHistory() {
        List<String> commandlist = new ArrayList<>();
        for (HistoryCommand command : commandHistory) {
            commandlist.add(command.getData());
        }
        return commandlist;
    }
}
