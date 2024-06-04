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
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Perform account creation functionality here
            JOptionPane.showMessageDialog(null, "Account Created! Username: " + username + ", Password: " + password);
            dispose(); // Close account creation frame
            new MainPage().setVisible(true); // Open main page
        });

        setContentPane(panel);
    }
}