package Notifications;

import java.util.List;

public class Invites implements Subject {
    protected List<Observer> observers;

    @Override
    public void register(Observer observer) {
        if(observer == null) {
            throw new NullPointerException("null"); 
        }
        else {
            observers.add(observer);
        }
    }

    @Override
    public void deregister(Observer observer) {
       observers.remove(observer);
    }

    @Override
    public void notifyObserver(Notification notification) {
        for(Observer observer: observers){
            observer.update(notification);
        }
    }
    
}
