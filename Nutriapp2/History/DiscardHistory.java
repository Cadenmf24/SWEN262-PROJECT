package Nutriapp2.History;

public class DiscardHistory implements HistoryCommand{
    ApplicationInterface app;

    public DiscardHistory(ApplicationInterface newapp){
        app = newapp;
    }

    public void execute(){
        app.discard();
    }

    public void undo(){
        app.save();
    }
}
