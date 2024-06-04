import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FitnessApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setApplicationIcon();
            new LoginPage().setVisible(true);
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
}







