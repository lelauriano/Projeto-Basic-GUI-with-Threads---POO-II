package configuration;

import javax.swing.*;
import java.awt.*;

public class TestOnlyMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          
            JFrame frame = new JFrame("Test Configuration Menu (Standalone)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            AnimatedPanel panel = new AnimatedPanel();
            panel.startAnimation();
            frame.add(panel, BorderLayout.CENTER);

            ConfigurationMenu menu = new ConfigurationMenu(panel);

            menu.setConfigurationMenuListener(new ConfigurationMenuListener() {
                @Override
                public void onPatternChange(String newPattern) {
                    System.out.println("[Listener] Pattern changed: " + newPattern);
                }

                @Override
                public void onColorChange(Color newColor) {
                    System.out.println("[Listener] Color changed: " + newColor);
                }

                @Override
                public void onSpeedChange(int newSpeed) {
                    System.out.println("[Listener] Speed changed: " + newSpeed + " ms/frame");
                }

                @Override
                public void onAnimationToggle(boolean running) {
                    System.out.println("[Listener] Animation running: " + running);
                }

                @Override
                public void onReset() {
                    System.out.println("[Listener] Reset to defaults");
                }
            });
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menu);
            frame.setJMenuBar(menuBar);

            frame.setVisible(true);
        });
    }
}
