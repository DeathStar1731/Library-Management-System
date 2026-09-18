import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public void addBook(Scanner sc) throws LibraryException {
        int id = readInt(sc, "Enter book ID: ");
        if (findBook(id) != null) {
            throw new LibraryException("Book ID already exists.");
        }

        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter author: ");
        String author = sc.nextLine().trim();

        if (title.isEmpty() || author.isEmpty()) {
            throw new LibraryException("Title and author cannot be empty.");
        }

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        System.out.println("\n----- BOOKS -----");
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) b.displayInfo();
    }

    public void searchBook(Scanner sc) {
        System.out.print("Enter title or author to search: ");
        String query = sc.nextLine().toLowerCase().trim();

        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query)
                    || b.getAuthor().toLowerCase().contains(query)) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) System.out.println("No matching book found.");
    }

    public void addMember(Scanner sc) throws LibraryException {
        int id = readInt(sc, "Enter member ID: ");
        if (findMember(id) != null) {
            throw new LibraryException("Member ID already exists.");
        }

        System.out.print("Enter member name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        if (name.isEmpty() || email.isEmpty()) {
            throw new LibraryException("Name and email cannot be empty.");
        }

        members.add(new Member(id, name, email));
        System.out.println("Member added successfully.");
    }

    public void viewMembers() {
        System.out.println("\n----- MEMBERS -----");
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (Member m : members) m.displayInfo();
    }

    public void issueBook(Scanner sc) throws LibraryException {
        int bookId = readInt(sc, "Enter book ID: ");
        int memberId = readInt(sc, "Enter member ID: ");

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) throw new LibraryException("Book not found.");
        if (member == null) throw new LibraryException("Member not found.");
        if (book.isIssued()) throw new LibraryException("Book is already issued.");

        book.issueTo(memberId);
        System.out.println("Book issued to " + member.getName() + ".");
    }

    public void returnBook(Scanner sc) throws LibraryException {
        int bookId = readInt(sc, "Enter book ID: ");
        Book book = findBook(bookId);

        if (book == null) throw new LibraryException("Book not found.");
        if (!book.isIssued()) throw new LibraryException("Book is already available.");

        book.returnBook();
        System.out.println("Book returned successfully.");
    }

    public void showIssuedBooks() {
        System.out.println("\n----- ISSUED BOOKS -----");
        boolean found = false;

        for (Book b : books) {
            if (b.isIssued()) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) System.out.println("No books are currently issued.");
    }

    public void saveData() {
        try {
            FileManager.save(books, members);
            System.out.println("Data saved to library.txt.");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    public void loadData() {
        try {
            FileManager.load(books, members);
        } catch (IOException e) {
            System.out.println("Could not load previous data.");
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private Member findMember(int id) {
        for (Member m : members) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    private int readInt(Scanner sc, String message) {
        System.out.print(message);
        return Integer.parseInt(sc.nextLine());
    }
}
