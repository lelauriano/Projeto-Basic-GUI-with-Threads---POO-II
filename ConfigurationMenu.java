import javax.swing.*;
import java.awt.*;
public class ConfigurationMenu extends JMenu {

    private final AnimatedPanel animatedPanel;

    private final JMenu patternsMenu;
    private final JMenu colorsMenu;
    private final JMenu speedMenu;

    private final JRadioButtonMenuItem circlesItem;
    private final JRadioButtonMenuItem squaresItem;

    private final JRadioButtonMenuItem slowItem;
    private final JRadioButtonMenuItem normalItem;
    private final JRadioButtonMenuItem fastItem;

    private final JCheckBoxMenuItem animationToggle;
    private final JMenuItem resetItem;

    public ConfigurationMenu(AnimatedPanel animatedPanel) {
        super("Configuration");
        this.animatedPanel = animatedPanel;

        patternsMenu = new JMenu("Patterns");
        circlesItem = new JRadioButtonMenuItem("Circles");
        squaresItem = new JRadioButtonMenuItem("Squares");
        ButtonGroup patternsGroup = new ButtonGroup();
        patternsGroup.add(circlesItem);
        patternsGroup.add(squaresItem);

        circlesItem.addActionListener(e -> setPattern("Circles"));
        squaresItem.addActionListener(e -> setPattern("Squares"));

        patternsMenu.add(circlesItem);
        patternsMenu.add(squaresItem);

        colorsMenu = new JMenu("Colors");
        JMenuItem cyanItem = new JMenuItem("Cyan", createColorIcon(Color.CYAN, 12, 12));
        JMenuItem redItem  = new JMenuItem("Red", createColorIcon(Color.RED, 12, 12));
        JMenuItem blueItem = new JMenuItem("Blue", createColorIcon(Color.BLUE, 12, 12));
        JMenuItem randomItem = new JMenuItem("Randomize");
        JMenuItem customColorItem = new JMenuItem("Custom...");

        cyanItem.addActionListener(e -> { animatedPanel.setColor(Color.CYAN); syncMenuState(); });
        redItem.addActionListener(e -> { animatedPanel.setColor(Color.RED); syncMenuState(); });
        blueItem.addActionListener(e -> { animatedPanel.setColor(Color.BLUE); syncMenuState(); });
        randomItem.addActionListener(e -> { animatedPanel.setRandomColor(); syncMenuState(); });
        customColorItem.addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(this);
            Color chosen = JColorChooser.showDialog(owner, "Choose a color", animatedPanel.getColor());
            if (chosen != null) {
                animatedPanel.setColor(chosen);
                syncMenuState();
            }
        });

        colorsMenu.add(cyanItem);
        colorsMenu.add(redItem);
        colorsMenu.add(blueItem);
        colorsMenu.addSeparator();
        colorsMenu.add(randomItem);
        colorsMenu.add(customColorItem);

        speedMenu = new JMenu("Speed");
        slowItem = new JRadioButtonMenuItem("Slow (200 ms)");
        normalItem = new JRadioButtonMenuItem("Normal (100 ms)");
        fastItem = new JRadioButtonMenuItem("Fast (50 ms)");
        ButtonGroup speedGroup = new ButtonGroup();
        speedGroup.add(slowItem);
        speedGroup.add(normalItem);
        speedGroup.add(fastItem);

        slowItem.addActionListener(e -> { animatedPanel.setSpeed(200); syncMenuState(); });
        normalItem.addActionListener(e -> { animatedPanel.setSpeed(100); syncMenuState(); });
        fastItem.addActionListener(e -> { animatedPanel.setSpeed(50); syncMenuState(); });

        JMenuItem customSpeedItem = new JMenuItem("Custom...");
        customSpeedItem.addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(this);
            String input = JOptionPane.showInputDialog(owner,
                    "Enter the animation speed in ms (min 10):",
                    String.valueOf(animatedPanel.getSpeed()));
            if (input != null) {
                try {
                    int sp = Integer.parseInt(input.trim());
                    sp = Math.max(10, sp);
                    animatedPanel.setSpeed(sp);
                    syncMenuState();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "Invalid number. Please try again.");
                }
            }
        });

        speedMenu.add(slowItem);
        speedMenu.add(normalItem);
        speedMenu.add(fastItem);
        speedMenu.addSeparator();
        speedMenu.add(customSpeedItem);

        animationToggle = new JCheckBoxMenuItem("Animation running");
        animationToggle.addActionListener(e -> {
            if (animationToggle.isSelected()) {
                animatedPanel.startAnimation();
            } else {
                animatedPanel.stopAnimation();
            }
        });

        resetItem = new JMenuItem("Reset to defaults");
        resetItem.addActionListener(e -> {
            animatedPanel.setPattern("Circles");
            animatedPanel.setColor(Color.CYAN);
            animatedPanel.setSpeed(50);
            syncMenuState();
        });

        add(patternsMenu);
        add(colorsMenu);
        add(speedMenu);
        addSeparator();
        add(animationToggle);
        add(resetItem);

        syncMenuState();
    }

    private void setPattern(String pattern) {
        animatedPanel.setPattern(pattern);
        syncMenuState();
    }

    private void syncMenuState() {
        circlesItem.setSelected("Circles".equalsIgnoreCase(animatedPanel.getPattern()));
        squaresItem.setSelected("Squares".equalsIgnoreCase(animatedPanel.getPattern()));

        int sp = animatedPanel.getSpeed();
        slowItem.setSelected(sp >= 150);
        normalItem.setSelected(sp >= 75 && sp < 150);
        fastItem.setSelected(sp < 75);

        animationToggle.setSelected(animatedPanel.isRunning());
    }

    private static Icon createColorIcon(Color color, int w, int h) {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(color);
                g.fillRect(x, y, w, h);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x, y, w-1, h-1);
            }
            @Override public int getIconWidth() { return w; }
            @Override public int getIconHeight() { return h; }
        };
    }
}




