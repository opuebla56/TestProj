import javax.swing.*;
import java.awt.*;

public class FitnessApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setApplicationIcon();
            LoginPage loginPage = new LoginPage();
            centerWindow(loginPage);
            loginPage.setVisible(true);
        });
    }

    private static void setApplicationIcon() {
        try {
            String iconPath = "C:\\Users\\oscar\\OneDrive\\Desktop\\Classes\\DONE\\Java Programming 2\\FitnessTrackerLogo.png";
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage();

            // Set the icon image for all windows
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            for (Window window : Window.getWindows()) {
                if (window instanceof JFrame || window instanceof JDialog) {
                    window.setIconImage(image);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to center a window on the screen
    private static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = (screenSize.height - window.getHeight()) / 2;
        window.setLocation(x, y);
    }
}