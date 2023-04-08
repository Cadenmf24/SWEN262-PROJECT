package History;
public class SaveHistory implements HistoryCommand{
    private Document document;
    
    public SaveHistory(Document document) {
        this.document = document;
    }

    public void execute() {
        document.save();
    }

    public void undo() {
        document.undo();
    }

    @Override
    public String getData() {
        return document.toString();
    }
}
