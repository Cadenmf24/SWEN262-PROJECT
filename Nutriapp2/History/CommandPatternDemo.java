package Nutriapp2.History;
import java.util.ArrayList;
import java.util.List;

public class CommandPatternDemo {
    public static void main(String[] args){
        ApplicationInterface newApplication = UserDevice.getUserInterface();

        SaveHistory saveCommand = new SaveHistory(newApplication);
        AppButton IsClicked = new AppButton(saveCommand);
        IsClicked.select();


        ReturnHistory returnCommand = new ReturnHistory(newApplication);
        IsClicked = new AppButton(returnCommand);
        IsClicked.select();

        DiscardHistory discardCommand = new DiscardHistory(newApplication);
        IsClicked = new AppButton(discardCommand);
        IsClicked.select();

        Application mobile = new Application();

        List<ApplicationInterface> info = new ArrayList<ApplicationInterface>();
        
        info.add(mobile);
        ReturnAllHistory allHistoryReturned = new ReturnAllHistory(info);

        AppButton returnall = new AppButton(allHistoryReturned);
        returnall.select();

        returnall.goBack();
    }
}
