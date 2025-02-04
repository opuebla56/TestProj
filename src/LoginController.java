import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginController {
    private LoginPage loginPage;

    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
        addListeners();
    }

    private void addListeners() {
        loginPage.getLoginButton().addActionListener(new LoginButtonListener());
        loginPage.getCreateAccountButton().addActionListener(new CreateAccountButtonListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogin();
        }
    }

    private class CreateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginPage.dispose();
            new AccountCreationPage().setVisible(true);
        }
    }

    private void handleLogin() {
        String username = loginPage.getUsernameField().getText().trim();
        String password = new String(loginPage.getPasswordField().getPassword()).trim();

        if (UserManager.authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(loginPage, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loginPage.dispose();
            new MainPage().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginPage, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
