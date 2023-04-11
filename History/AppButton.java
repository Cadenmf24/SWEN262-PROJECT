package History;
public class AppButton {
    HistoryCommand command;

    public AppButton(HistoryCommand newCommand){
        command = newCommand;
    }

    public void select(){
        command.execute();
    }

    public void goBack(){
        command.undo();
    }
}
