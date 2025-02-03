import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

@SuppressWarnings("serial")
class AccountCreationPage extends JFrame {
    public AccountCreationPage() {
        setTitle("Create Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton createButton = new JButton("Create");

        // Add components to panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(createButton);

        // Action listener for create button
        createButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            
            // Ensure username and password are not empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call method to save credentials to file
            if (saveCredentials(username, password)) {
                JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                dispose();  // Close account creation frame
                new LoginPage().setVisible(true);  // Open login page
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setContentPane(panel);
    }

    // Method to save credentials to the users.txt file
    private boolean saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
            return true;  // Return true if writing was successful
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if there was an error
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountCreationPage().setVisible(true));
    }
}
