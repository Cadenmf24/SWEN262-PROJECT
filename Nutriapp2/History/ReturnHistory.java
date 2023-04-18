package Nutriapp2.History;

public class ReturnHistory implements HistoryCommand{
    ApplicationInterface app;
    
    public ReturnHistory(ApplicationInterface newapp){
        app = newapp;
    }

    public void execute(){
        app.returnHistory();
    }

    public void undo(){
        return;
    }
}
