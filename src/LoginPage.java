import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 3));

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

        // Action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Perform login functionality here
            JOptionPane.showMessageDialog(null, "Login Successful! Username: " + username + ", Password: " + password);
            dispose(); // Close login page frame
            new MainPage().setVisible(true); // Open main page
        });

        // Action listener for create account button
        createAccountButton.addActionListener(e -> {
            dispose(); // Close login page frame
            new AccountCreationPage().setVisible(true);
        });

        setContentPane(panel);
    }
}