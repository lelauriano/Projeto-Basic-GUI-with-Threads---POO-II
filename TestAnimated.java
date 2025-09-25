import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
public class MainWindow {
=======
public class TestAnimated {
>>>>>>> minha-nova-branch
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Config Menu + AnimatedPanel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            AnimatedPanel panel = new AnimatedPanel();
            frame.add(panel, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(new ConfigurationMenu(panel));
            frame.setJMenuBar(menuBar);

            frame.setVisible(true);

            panel.startAnimation();
        });
    }
}
