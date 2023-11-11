package behavioral.templateMethod;

// Abstract Class define template methods
abstract class DocumentEditor {
    // Template Method: Define the general flow for the document proccess
    public void processDocument() {
        openDocument();
        editDocument();
        saveDocument();
    }

    public abstract void openDocument();
    public abstract void editDocument();
    public abstract void saveDocument();
}

class TextDocumentEditor extends DocumentEditor {
    @Override
    public void openDocument() {
        System.out.println("Open Text Document");
        // Logic especific to open text document
    }

    @Override
    public void editDocument() {
        System.out.println("Edit Text Document");
        // Logic especific to open text document
    }

    @Override
    public void saveDocument() {
        System.out.println("Save Text Document");
        // Logic especific to open text document
    }
}

class ImageDocumentEditor extends DocumentEditor {
    @Override
    public void openDocument() {
        System.out.println("Open Image Document");
        // Logic especific to open image document
    }

    @Override
    public void editDocument() {
        System.out.println("Edit Image Document");
        // Logic especific to open image document
    }

    @Override
    public void saveDocument() {
        System.out.println("Save Image Document");
        // Logic especific to open image document
    }
}

public class EditorExample {
    public static void main(String[] args) {
        DocumentEditor textEditor = new TextDocumentEditor();
        textEditor.processDocument();

        DocumentEditor imageEditor = new ImageDocumentEditor();
        imageEditor.processDocument();
    }
}
