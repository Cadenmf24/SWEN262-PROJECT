package Notifications;

import java.util.List;

public class Invites implements Subject {
    protected List<InviteObserver> inviteObservers;

    @Override
    public void register(InviteObserver inviteObserver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public void deregister(Observer observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deregister'");
    }

    @Override
    public void notifyObserver(Notification notification) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObserver'");
    }
    
}
