import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "library.txt";

    public static void save(List<Book> books, List<Member> members) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("[BOOKS]");
            for (Book b : books) {
                writer.println(b.saveLine());
            }

            writer.println("[MEMBERS]");
            for (Member m : members) {
                writer.println(m.saveLine());
            }
        }
    }

    public static void load(List<Book> books, List<Member> members) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String section = "";
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals("[BOOKS]") || line.equals("[MEMBERS]")) {
                    section = line;
                    continue;
                }

                if (line.isBlank()) continue;

                String[] p = line.split("\\|", -1);

                try {
                    if (section.equals("[BOOKS]") && p.length == 5) {
                        Book b = new Book(Integer.parseInt(p[0]), p[1], p[2]);
                        if (Boolean.parseBoolean(p[3])) {
                            b.issueTo(Integer.parseInt(p[4]));
                        }
                        books.add(b);
                    } else if (section.equals("[MEMBERS]") && p.length == 3) {
                        members.add(new Member(Integer.parseInt(p[0]), p[1], p[2]));
                    }
                } catch (NumberFormatException ignored) {
                    // Skip damaged records.
                }
            }
        }
    }
}
