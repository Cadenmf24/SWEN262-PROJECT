package History;

public class Document {
    private String content;
    private String previousContent;

    public void setContent(String content) {
        previousContent = this.content;
        this.content = content;
    }

    public void save() {
        // Implement save method here
        System.out.println("Document saved: " + content);
    }

    public void undo() {
        // Undo the last change made to the document
        content = previousContent;
        System.out.println("Undoing last change. Current content: " + content);
    }
}
