package Nutriapp2.History;
import java.util.List;
public class ReturnAllHistory implements HistoryCommand{
    List <ApplicationInterface> UserActivity;

    public ReturnAllHistory(List<ApplicationInterface> newUserActivity){
        UserActivity = newUserActivity;
    }
    @Override
    public void execute() {
        for(ApplicationInterface history: UserActivity){
            history.save();
            history.returnHistory();
        }
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void undo() {
        for(ApplicationInterface history: UserActivity){
            history.discard();
        }
        UserActivity.clear();
        throw new UnsupportedOperationException("Unimplemented method 'undo'");
    }
}
