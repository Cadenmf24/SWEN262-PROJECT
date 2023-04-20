package Notifications;

public class InviteObserver implements Observer {
    Notification notification;

    public InviteObserver(Notification notification){
       this.notification = notification;
    }

    @Override
    public void update(Notification notification) {
        System.out.println("Notification: " + notification.text);
    }
    
}
