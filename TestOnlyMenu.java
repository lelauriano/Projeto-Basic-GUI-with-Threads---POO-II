import javax.swing.*;

public class TestOnlyMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
<<<<<<< HEAD
          
=======

    
>>>>>>> minha-nova-branch
            JFrame frame = new JFrame("Test Config Menu (standalone)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            AnimatedPanel animatedPanel = new AnimatedPanel();
            animatedPanel.startAnimation(); 

            JMenuBar menuBar = new JMenuBar();

            ConfigurationMenu configMenu = new ConfigurationMenu(animatedPanel);
            menuBar.add(configMenu);

            frame.setJMenuBar(menuBar);

            frame.setVisible(true);
        });
    }
}
