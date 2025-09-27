import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestAnimated {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Config Menu + AnimatedPanel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            AnimatedPanel panel = new AnimatedPanel();
            frame.add(panel, BorderLayout.CENTER);
            panel.startAnimation();

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
            AutoAnimationChanger autoChanger = new AutoAnimationChanger(panel, 1000); // 1s

            JCheckBoxMenuItem autoModeToggle = new JCheckBoxMenuItem("Automatic Mode");
            autoModeToggle.addActionListener(e -> autoChanger.setAutomaticMode(autoModeToggle.isSelected()));
            menu.add(autoModeToggle);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menu);
            frame.setJMenuBar(menuBar);

            frame.setVisible(true);
        });
    }

    static class AutoAnimationChanger {
        private final AnimatedPanel panel;
        private final Timer timer;
        private final Random rand = new Random();
        private boolean automaticMode = false;

        public AutoAnimationChanger(AnimatedPanel panel, int intervalMs) {
            this.panel = panel;
            timer = new Timer(intervalMs, e -> {
                if (automaticMode) {
                    String newPattern = panel.getPattern().equals("Circles") ? "Squares" : "Circles";
                    panel.setPattern(newPattern);

                    panel.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));

                    panel.repaint();
                }
            });
            timer.start();
        }

        public void setAutomaticMode(boolean automaticMode) {
            this.automaticMode = automaticMode;
        }
    }
}
