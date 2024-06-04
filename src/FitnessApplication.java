import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FitnessApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           // new FitnessApplication().run();
        	new LoginPage().setVisible(true);
        });
    }


}
