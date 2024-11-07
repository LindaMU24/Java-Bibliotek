import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
        // Lägg till några böcker i "biblioteket"
        books.add(new Book("Harry Potter och De Vises Sten", "J.K. Rowling", 1997, 1));
        books.add(new Book("Sagan om Ringen", "J.R.R. Tolkien", 1954, 1));
        books.add(new Book("1984", "George Orwell", 1949, 1));
        books.add(new Book("Möss och Människor", "John Steinbeck", 1937, 1));
        books.add(new Book("Brave New World", "Aldous Huxley", 1932, 1));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> findBooksByPartialName(String partialTitle) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}