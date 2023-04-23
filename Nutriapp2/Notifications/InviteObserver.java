package Notifications;
import teamThings.*;
import UserProfile.User;

public class InviteObserver implements Observer {
    protected Notification notification;

    public InviteObserver(Notification notification){
       this.notification = notification;
    }


    @Override
    public void update(Notification notification, User user) {
        //System.out.println("Notification: " + notification.text);
        user.getNotifications().add(notification);
    }
    
}
