package History;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Document document = new Document();
        document.setContent("Application is running");

        SaveHistory saveCommand = new SaveHistory(document);
        HistoryMenu menu = new HistoryMenu();
        menu.executeCommand(saveCommand);

        document.setContent("User has access to the application's resources");
        SaveHistory saveCommand2 = new SaveHistory(document);
        menu.executeCommand(saveCommand2); 
        List<String> commandHistory = menu.getCommandHistory();
        System.out.println("Command history: " + commandHistory); 

        menu.undoLastCommand();
        menu.undoLastCommand();
        commandHistory = menu.getCommandHistory();
        System.out.println("Command history after undo: " + commandHistory); 
    
    }
}
