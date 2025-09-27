import javax.swing.*;
import java.awt.*;

public class ConfigurationMenu extends JMenu {

    private final AnimatedPanel animatedPanel;
    private ConfigurationMenuListener listener;

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

    public ConfigurationMenu(AnimatedPanel panel) {
        super("Configuration");
        this.animatedPanel = panel;

        patternsMenu = new JMenu("Patterns");
        circlesItem = new JRadioButtonMenuItem("Circles");
        squaresItem = new JRadioButtonMenuItem("Squares");
        ButtonGroup patternGroup = new ButtonGroup();
        patternGroup.add(circlesItem);
        patternGroup.add(squaresItem);
        patternsMenu.add(circlesItem);
        patternsMenu.add(squaresItem);

        colorsMenu = new JMenu("Colors");
        JMenuItem cyanItem = new JMenuItem("Cyan", createColorIcon(Color.CYAN, 12, 12));
        JMenuItem redItem = new JMenuItem("Red", createColorIcon(Color.RED, 12, 12));
        JMenuItem blueItem = new JMenuItem("Blue", createColorIcon(Color.BLUE, 12, 12));
        JMenuItem randomItem = new JMenuItem("Random");
        JMenuItem customColorItem = new JMenuItem("Custom...");
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
        speedMenu.add(slowItem);
        speedMenu.add(normalItem);
        speedMenu.add(fastItem);
        speedMenu.addSeparator();
        JMenuItem customSpeedItem = new JMenuItem("Custom...");
        speedMenu.add(customSpeedItem);

        animationToggle = new JCheckBoxMenuItem("Animation running");
        resetItem = new JMenuItem("Reset to defaults");

        add(patternsMenu);
        add(colorsMenu);
        add(speedMenu);
        addSeparator();
        add(animationToggle);
        add(resetItem);

        setupListeners(cyanItem, redItem, blueItem, randomItem, customColorItem, customSpeedItem);

        syncMenuState();
    }

    public void setConfigurationMenuListener(ConfigurationMenuListener listener) {
        this.listener = listener;
    }

    private void setupListeners(JMenuItem cyanItem, JMenuItem redItem, JMenuItem blueItem,
                                JMenuItem randomItem, JMenuItem customColorItem, JMenuItem customSpeedItem) {

        circlesItem.addActionListener(e -> setPatternWithListener("Circles"));
        squaresItem.addActionListener(e -> setPatternWithListener("Squares"));

        cyanItem.addActionListener(e -> setColorWithListener(Color.CYAN));
        redItem.addActionListener(e -> setColorWithListener(Color.RED));
        blueItem.addActionListener(e -> setColorWithListener(Color.BLUE));
        randomItem.addActionListener(e -> {
            animatedPanel.setRandomColor();
            if(listener != null) listener.onColorChange(animatedPanel.getColor());
            syncMenuState();
        });
        customColorItem.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(this, "Choose Color", animatedPanel.getColor());
            if(chosen != null) setColorWithListener(chosen);
        });

        slowItem.addActionListener(e -> setSpeedWithListener(200));
        normalItem.addActionListener(e -> setSpeedWithListener(100));
        fastItem.addActionListener(e -> setSpeedWithListener(50));
        customSpeedItem.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter speed (ms/frame):", animatedPanel.getSpeed());
            if(input != null){
                try{
                    int sp = Math.max(10, Integer.parseInt(input.trim()));
                    setSpeedWithListener(sp);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this,"Invalid value");
                }
            }
        });
        animationToggle.addActionListener(e -> {
            if(animationToggle.isSelected()) animatedPanel.startAnimation();
            else animatedPanel.stopAnimation();
            if(listener != null) listener.onAnimationToggle(animationToggle.isSelected());
        });
        resetItem.addActionListener(e -> {
            animatedPanel.setPattern("Circles");
            animatedPanel.setColor(Color.CYAN);
            animatedPanel.setSpeed(50);
            if(listener != null) listener.onReset();
            syncMenuState();
        });
    }

    private void setPatternWithListener(String pattern){
        animatedPanel.setPattern(pattern);
        if(listener != null) listener.onPatternChange(pattern);
        syncMenuState();
    }

    private void setColorWithListener(Color color){
        animatedPanel.setColor(color);
        if(listener != null) listener.onColorChange(color);
        syncMenuState();
    }

    private void setSpeedWithListener(int speed){
        animatedPanel.setSpeed(speed);
        if(listener != null) listener.onSpeedChange(speed);
        syncMenuState();
    }

    private void syncMenuState(){
        circlesItem.setSelected("Circles".equalsIgnoreCase(animatedPanel.getPattern()));
        squaresItem.setSelected("Squares".equalsIgnoreCase(animatedPanel.getPattern()));

        int sp = animatedPanel.getSpeed();
        slowItem.setSelected(sp == 200);
        normalItem.setSelected(sp == 100);
        fastItem.setSelected(sp == 50);

        animationToggle.setSelected(animatedPanel.isRunning());
    }

    private static Icon createColorIcon(Color color, int w, int h){
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



