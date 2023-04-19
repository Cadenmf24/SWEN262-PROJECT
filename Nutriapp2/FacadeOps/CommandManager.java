package Nutriapp2.FacadeOps;
import java.util.Stack;

import Nutriapp2.Command.Command;

public class CommandManager {
    private Stack<Command> undoStack;
    
    public CommandManager() {
        undoStack = new Stack<>();
    }
    
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
    }
    
    public void undoLastCommand() {
        if (!undoStack.empty()) {
            Command lastCommand = undoStack.pop();
            lastCommand.undo();
        }
    }
}