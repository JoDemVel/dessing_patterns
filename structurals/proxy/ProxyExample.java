package structurals.proxy;

// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy
class ImageProxy implements Image {
    private RealImage realImage;
    private String filename;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        // Usando el proxy para cargar la imagen de manera diferida
        Image imageProxy = new ImageProxy("sample.jpg");

        // La imagen real se carga solo cuando se llama a display
        imageProxy.display();

        // Si llamamos a display nuevamente, la imagen real no se recarga
        imageProxy.display();
    }
}
