import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int year;
    private int edition;
    private boolean available;
    private List<String> reservationList;

    public Book(String title, String author, int year, int edition) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.edition = edition;
        this.available = true;
        this.reservationList = new ArrayList<>();
    }

    public boolean loan() {
        if (this.available) { // Kontrollera om boken är tillgänglig
            this.available = false; // Gör boken otillgänglig
            return true; // Returnera true eftersom lånet lyckades
        }
        return false; // Returnera false om boken redan är otillgänglig
    }

    public boolean returnBook() {
        if (!this.available) { // Kontrollera om boken är otillgänglig
            if (!reservationList.isEmpty()) {
                String nextInLine = reservationList.remove(0);
                System.out.println("Boken är nu reserverad för " + nextInLine);
                System.out.println(nextInLine + " är nu välkommen in för att hämta boken.");
                // Boken blir inte tillgänglig eftersom den är reserverad
                return false;
            } else {
                this.available = true; // Gör boken tillgänglig igen
                System.out.println("Boken är nu tillgänglig för utlåning" );
                return true; //Returnera true eftersom återlämningen lyckades
            }
        }
        System.out.println("Boken var redan tillgänglig.");
        return false; // Returnera false om boken redan är tillgänglig
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;

    }
    public String reserve(String userName) {
        if (!available) {
            reservationList.add(userName);
            return "Boken är utlånad. " + userName + " har reserverat boken.";
        } else {
            return "Boken är tillgänglig. Du kan låna den direkt.";
        }
    }

    @Override
    public String toString() {
        return "Boken: " + title + ", Författare: " + author + ", År: " + year +
                ", Upplaga: " + edition + ", Tillgänglig: " + (available ? "Ja" : "Nej");
    }
}


