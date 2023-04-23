package Notifications;
import UserProfile.User;

public interface Observer {
    void update(Notification notification, User user);
}
