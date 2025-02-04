import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
    private Map<String, String> userCredentials;  // HashMap to store usernames and passwords

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Initialize the HashMap and load user credentials from file
        userCredentials = new HashMap<>();
        loadUserCredentials("users.txt");  // Replace with your actual file path

        // Panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        // Add components to panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        new LoginController(this);
        
        // Action listener for create account button
        createAccountButton.addActionListener(e -> {
            dispose();  // Close login page frame
            new AccountCreationPage().setVisible(true);
        });

        setContentPane(panel);
    }
    
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }


    // Method to load usernames and passwords from file into the HashMap
    private void loadUserCredentials(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String username = credentials[0].trim();
                    String password = credentials[1].trim();
                    userCredentials.put(username, password);  // Add to HashMap
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to perform login functionality
    private void loginUser(String username, String password) {
        String storedPassword = userCredentials.get(username);

        if (storedPassword != null && storedPassword.equals(password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Close login page frame
            new MainPage().setVisible(true);  // Open main page
        } else {
            JOptionPane.showMessageDialog(this, "Login Error: Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
