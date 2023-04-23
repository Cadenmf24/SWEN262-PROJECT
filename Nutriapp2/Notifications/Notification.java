package Notifications;
import teamThings.*;

public class Notification {
    protected String text;
    protected Teams team;

    public Notification(String text, Teams team){
        this.text = text;
        this.team = team;
    }

    public String getText(){
        return text;
    }
    public Teams getTeam(){
        return team;
    }
}
