import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        library.loadData();

        while (true) {
            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. View Members");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Show Issued Books");
            System.out.println("9. Save & Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> library.addBook(sc);
                    case 2 -> library.viewBooks();
                    case 3 -> library.searchBook(sc);
                    case 4 -> library.addMember(sc);
                    case 5 -> library.viewMembers();
                    case 6 -> library.issueBook(sc);
                    case 7 -> library.returnBook(sc);
                    case 8 -> library.showIssuedBooks();
                    case 9 -> {
                        library.saveData();
                        System.out.println("Thank you for using the Library Management System!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
