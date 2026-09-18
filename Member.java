public class Member extends User {
    private String email;

    public Member(int id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email);
    }

    public String saveLine() {
        return id + "|" + name.replace("|", " ") + "|" + email.replace("|", " ");
    }
}
