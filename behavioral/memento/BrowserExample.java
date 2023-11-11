package behavioral.memento;

import java.util.Stack;

// Mememto
class WebPageMemento {
    private final String url;

    public WebPageMemento(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

// Originator
class WebBrowser {
    private String currentUrl;
    private Stack<WebPageMemento> history = new Stack<>();

    public void open(String url) {
        System.out.println("Opening page: " + url);
        currentUrl = url;
        history.push(new WebPageMemento(url));
    }

    public void goBack() {
        if (!history.isEmpty()) {
            WebPageMemento previousPage = history.pop();
            currentUrl = previousPage.getUrl();
            System.out.println("Going back to: " + currentUrl);
        } else {
            System.out.println("No more pages to go back");
        }
    }
}

public class BrowserExample {
    public static void main(String[] args) {
        WebBrowser browser = new WebBrowser();

        browser.open("www.example.com");
        browser.open("www.google.com");
        browser.open("www.github.com");

        // Backing pages
        browser.goBack(); // Back to "www.google.com"
        browser.goBack(); // Back to "www.example.com"
        browser.goBack(); // No more pages to go back
    }
}

