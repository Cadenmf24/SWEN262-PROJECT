package Nutriapp2.Observer;

public interface Component {
    void register(Goal g);
    void deregister(Goal g);
    void notifyListener(UserEvent userEvent);
}
