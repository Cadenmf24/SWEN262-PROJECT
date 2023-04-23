package Notifications;
import UserProfile.User;

public interface Subject {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObserver(Notification notification, User user);
}
