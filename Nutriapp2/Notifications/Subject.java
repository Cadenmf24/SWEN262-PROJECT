package Nutriapp2.Notifications;

public interface Subject {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObserver(Notification notification);
}
