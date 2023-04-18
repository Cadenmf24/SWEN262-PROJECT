package Nutriapp2.History;

public class SaveHistory implements HistoryCommand{
    ApplicationInterface app;

    public SaveHistory(ApplicationInterface newapp){
        app = newapp;
    }

    public void execute(){
        app.save();
    }

    public void undo(){
        app.discard();
    }
}
