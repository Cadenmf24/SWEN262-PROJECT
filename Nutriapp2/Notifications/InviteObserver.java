package Nutriapp2.Notifications;

public class InviteObserver implements Observer {
    protected Notification notification;

    public InviteObserver(Notification notification){
       this.notification = notification;
    }

    @Override
    public void update(Notification notification) {
        System.out.println("Notification: " + notification.text);
    }
    
}
