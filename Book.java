public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;
    private int issuedToMemberId;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedToMemberId = -1;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public int getIssuedToMemberId() {
        return issuedToMemberId;
    }

    public void issueTo(int memberId) {
        issued = true;
        issuedToMemberId = memberId;
    }

    public void returnBook() {
        issued = false;
        issuedToMemberId = -1;
    }

    public void displayInfo() {
        String status = issued ? "Issued to Member " + issuedToMemberId : "Available";
        System.out.println("ID: " + id + " | " + title + " | " + author + " | " + status);
    }

    public String saveLine() {
        return id + "|" + title.replace("|", " ") + "|" + author.replace("|", " ")
                + "|" + issued + "|" + issuedToMemberId;
    }
}
