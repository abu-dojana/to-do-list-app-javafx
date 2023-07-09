import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static final String FILE_PATH = "tasks.txt";

    public static void saveData(List<LocalEvent> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (LocalEvent event : events) {
                writer.write(event.getDate().toString());
                writer.newLine();
                writer.write(event.getDescription());
                writer.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<LocalEvent> loadData() {
        List<LocalEvent> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LocalDate date = LocalDate.parse(line);
                String description = reader.readLine();
                events.add(new LocalEvent(date, description));
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return events;
    }
}
