import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
    private Map<String, String> userCredentials;
    
    // ✅ Declare UI components as instance variables
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Initialize the HashMap and load user credentials from file
        userCredentials = new HashMap<>();
        loadUserCredentials("users.txt");

        // Panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // ✅ Initialize UI components properly
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(); // Now it references the instance variable
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");

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
            dispose();
            new AccountCreationPage().setVisible(true);
        });

        setContentPane(panel);
    }

    // ✅ Getter methods now work because variables are instance variables
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
                    userCredentials.put(credentials[0].trim(), credentials[1].trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
