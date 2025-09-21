import javax.swing.*;
import java.awt.*;

public class ConfigurationMenu extends JMenu {
    private JMenuItem patternItem;
    private JMenuItem colorItem;
    private JMenuItem speedItem;

    private final AnimatedPanel animatedPanel;

    public ConfigurationMenu(AnimatedPanel animatedPanel) {
        super("Configuration");
        this.animatedPanel = animatedPanel;

        patternItem = new JMenuItem("Patterns");
        colorItem = new JMenuItem("Colors");
        speedItem = new JMenuItem("Speed");

        add(patternItem);
        add(colorItem);
        add(speedItem);

        patternItem.addActionListener(e -> choosePattern());
        colorItem.addActionListener(e -> chooseColor());
        speedItem.addActionListener(e -> chooseSpeed());
    }

    // --- METHODS THAT OPEN DIALOGS AND APPLY SETTINGS ---

    private void choosePattern() {
        String[] options = {"Circles", "Squares"};
        Window owner = SwingUtilities.getWindowAncestor(this);

        String choice = (String) JOptionPane.showInputDialog(
                owner,
                "Select a pattern:",
                "Pattern Configuration",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice != null) {
            animatedPanel.setPattern(choice);
        }
    }

    private void chooseColor() {
        Window owner = SwingUtilities.getWindowAncestor(this);

        Color newColor = JColorChooser.showDialog(owner, "Choose a color", Color.CYAN);
        if (newColor != null) {
            animatedPanel.setColor(newColor);
        }
    }

    private void chooseSpeed() {
        Window owner = SwingUtilities.getWindowAncestor(this);

        String input = JOptionPane.showInputDialog(
                owner,
                "Enter the animation speed (ms):",
                String.valueOf(animatedPanel.getSpeed()) // valor atual, não fixo
        );

        if (input != null) {
            try {
                int speed = Integer.parseInt(input);
                animatedPanel.setSpeed(speed);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(owner, "Invalid number. Please try again.");
            }
        }
    }
}



