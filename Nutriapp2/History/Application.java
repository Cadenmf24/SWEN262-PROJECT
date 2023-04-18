package History;
public class Application implements ApplicationInterface{

    @Override
    public void save() {
        //Need to further implement
        System.out.println("Application is up to date.");
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void returnHistory() {
        //Need to further implement
        System.out.println("User's command history has been returned");
        throw new UnsupportedOperationException("Unimplemented method 'returnCommandHistory'");
    }

    @Override
    public void discard() {
        //Need to further implement
        System.out.println("Discarded!");
        throw new UnsupportedOperationException("Unimplemented method 'undo'");
    }
    
}
