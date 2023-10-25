import java.util.List;

// Component interface representing a basic web page
interface WebPage {
    void display();
}

// Concrete component representing a basic web page
class BasicWebPage implements WebPage {
    private String content;

    public BasicWebPage(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        System.out.println("Basic Web Page: " + content);
    }
}

// Decorator for adding access control to a web page
class AccessControlDecorator implements WebPage {
    private WebPage page;

    public AccessControlDecorator(WebPage page) {
        this.page = page;
    }

    @Override
    public void display() {
        if (checkAccess()) {
            page.display();
        } else {
            System.out.println("Access denied!");
        }
    }

    private boolean checkAccess() {
        // Implement access control logic here, e.g., check user authorization
        return true; // For demonstration, always grant access
    }
}

// Decorator for paginating search results on a web page
class PaginationDecorator implements WebPage {
    private WebPage page;
    private List<String> searchResults;

    public PaginationDecorator(WebPage page, List<String> searchResults) {
        this.page = page;
        this.searchResults = searchResults;
    }

    @Override
    public void display() {
        page.display();
        paginateResults();
    }

    private void paginateResults() {
        // Implement pagination logic here
        System.out.println("Displaying 10 results per page:");
        for (int i = 0; i < searchResults.size(); i += 10) {
            int end = Math.min(i + 10, searchResults.size());
            List<String> resultsPage = searchResults.subList(i, end);
            System.out.println("Page " + (i / 10 + 1) + ": " + resultsPage);
        }
    }
}

public class WebPageDecoratorDemo {
    public static void main(String[] args) {
        // Create a basic web page
        WebPage basicPage = new BasicWebPage("Welcome to the Basic Web Page!");

        // Create a web page with access control
        WebPage protectedPage = new AccessControlDecorator(new BasicWebPage("Welcome to the Protected Web Page!"));

        // Create a web page with access control and search result pagination
        List<String> searchResults = List.of("Result 1", "Result 2", "Result 3", "Result 4", "Result 5",
                "Result 6", "Result 7", "Result 8", "Result 9", "Result 10", "Result 11");
        WebPage paginatedPage = new PaginationDecorator(new AccessControlDecorator(basicPage), searchResults);

        System.out.println("Basic Web Page:");
        basicPage.display();

        System.out.println("\nProtected Web Page:");
        protectedPage.display();

        System.out.println("\nPaginated Web Page:");
        paginatedPage.display();
    }
}
