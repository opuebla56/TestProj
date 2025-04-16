import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final String FILE_PATH = "users.txt";

    public Map<String, String> loadUserCredentials() {
        Map<String, String> userCredentials = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    userCredentials.put(credentials[0].trim(), credentials[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading user credentials: " + e.getMessage());
        }
        return userCredentials;
    }

    public boolean saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }
}