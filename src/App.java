import java.util.Scanner;
import java.util.List;

public class App {
    private Library library;

    public App() {
        library = new Library();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Välj ett alternativ:");
            System.out.println("1. Lägg till en bok i biblioteket");
            System.out.println("2. Sök efter en bok med namn");
            System.out.println("3. Lista alla tillgängliga böcker");
            System.out.println("4. Returnera en bok");
            System.out.println("5. Avsluta");
            System.out.println("6. Reservera en bok");


            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumera newline

            switch (choice) {
                case 1:
                    System.out.println("Ange titel:");
                    String title = scanner.nextLine();
                    System.out.println("Ange författare:");
                    String author = scanner.nextLine();
                    System.out.println("Ange år:");
                    int year = scanner.nextInt();
                    System.out.println("Ange upplaga:");
                    int edition = scanner.nextInt();
                    scanner.nextLine(); // Konsumera newline
                    library.addBook(new Book(title, author, year, edition));
                    System.out.println("Bok tillagd!");
                    break;
                case 2:
                    System.out.println("Ange del av bokens titel du vill söka efter:");
                    String searchTitle = scanner.nextLine();
                    List<Book> foundBooks = library.findBooksByPartialName(searchTitle);
                    if (!foundBooks.isEmpty()) {
                        System.out.println("Följande böcker hittades:");
                        for (Book book : foundBooks) {
                            System.out.println(book);
                            System.out.println("Vill du låna denna bok? (ja/nej)");
                            String loanChoice = scanner.nextLine();
                            if (loanChoice.equalsIgnoreCase("ja")) {
                                if (book.loan()) {
                                    System.out.println("Boken är nu utlånad.");
                                } else {
                                    System.out.println("Boken är inte tillgänglig för utlåning.");
                                }
                            }
                        }
                    } else {
                        System.out.println("Inga böcker kunde hittas.");
                    }
                    break;
                case 3:
                    List<Book> availableBooks = library.listAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("Inga tillgängliga böcker just nu.");
                    } else {
                        System.out.println("Tillgängliga böcker:");
                        for (Book book : availableBooks) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Ange del av bokens titel du vill returnera:");
                    String returnTitle = scanner.nextLine();
                    List<Book> returnBooks = library.findBooksByPartialName(returnTitle);
                    if (!returnBooks.isEmpty()) {
                        System.out.println("Följande böcker hittades:");
                        for (int i = 0; i < returnBooks.size(); i++) {
                            System.out.println((i + 1) + ": " + returnBooks.get(i));
                        }
                        System.out.println("Ange numret på boken du vill returnera:");
                        int bookNumber = scanner.nextInt();
                        scanner.nextLine(); // Konsumera newline
                        if (bookNumber > 0 && bookNumber <= returnBooks.size()) {
                            Book returnBook = returnBooks.get(bookNumber - 1);
                            if (returnBook.returnBook()) {
                                System.out.println("Boken har returnerats.");
                            } else {
                                System.out.println("Boken var redan tillgänglig.");
                            }
                        } else {
                            System.out.println("Ogiltigt val.");
                        }
                    } else {
                        System.out.println("Inga böcker kunde hittas.");
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Avslutar programmet.");
                    break;
                case 6:
                    System.out.println("Ange titeln på boken du vill reservera:");
                    String titel = scanner.nextLine();
                    List<Book> booksToReserve = library.findBooksByPartialName(titel);
                    if (!booksToReserve.isEmpty()) {
                        for (Book book : booksToReserve) {
                            System.out.println(book);
                            System.out.println("Vill du reservera denna bok? (ja/nej)");
                            String reserveChoice = scanner.nextLine();
                            if (reserveChoice.equalsIgnoreCase("ja")) {
                                System.out.println("Ange ditt namn för reservation:");
                                String namn = scanner.nextLine();
                                System.out.println(book.reserve(namn));
                            }
                        }
                    } else {
                        System.out.println("Inga böcker kunde hittas.");
                    }
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }

        scanner.close();
    }
}