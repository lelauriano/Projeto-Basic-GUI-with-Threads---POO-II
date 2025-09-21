import javax.swing.*;

public class TestOnlyMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            JFrame frame = new JFrame("Test Config Menu (standalone)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Um painel animado fictício só para não dar erro
            AnimatedPanel animatedPanel = new AnimatedPanel();
            animatedPanel.startAnimation(); // opcional, se quiser ver animação

            JMenuBar menuBar = new JMenuBar();

            ConfigurationMenu configMenu = new ConfigurationMenu(animatedPanel);
            menuBar.add(configMenu);

            frame.setJMenuBar(menuBar);

            frame.setVisible(true);
        });
    }
}
