package Observer;

public interface Component {
    void register();
    void deregister();
    void notifyListener();
}
