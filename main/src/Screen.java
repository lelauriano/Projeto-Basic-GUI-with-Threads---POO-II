import javax.swing.*;
import java.awt.*;

import configuration.AnimatedPanel;
import file.FileHandler;
import help.HelpDialog;


public class Screen extends JFrame {

    private JTextArea fileTextArea;
    private JLabel statusBar;
    private AnimatedPanel animatedPanel;
    private FileHandler fileHandler; // nossa nova classe

    public Screen() {
        super("Basic GUI with Threads");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("icon.png").getImage());

        // Animated background
        animatedPanel = new AnimatedPanel();
        setLayout(new BorderLayout());
        add(animatedPanel, BorderLayout.CENTER);
        animatedPanel.startAnimation();

        // File text area
        fileTextArea = new JTextArea();
        fileTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(fileTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.EAST);

        // Status bar
        statusBar = new JLabel("Ready");
        add(statusBar, BorderLayout.SOUTH);


        fileHandler = new FileHandler(fileTextArea, statusBar); //iniciando a aba arquivos (File Handler)

        // Menu bar
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Arquivo
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open file");
        JMenuItem closeItem = new JMenuItem("Close file");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Delegando ações para FileHandler
        openItem.addActionListener(e -> fileHandler.openFile(this));
        closeItem.addActionListener(e -> fileHandler.clearFile());
        exitItem.addActionListener(e -> System.exit(0));

        // Menu Configuração
        JMenu configMenu = new JMenu("Settings");
        JMenuItem patternItem = new JMenuItem("Patterns");
        JMenuItem colorItem = new JMenuItem("Color");
        JMenuItem speedItem = new JMenuItem("Speed");
        configMenu.add(patternItem);
        configMenu.add(colorItem);
        configMenu.add(speedItem);

        patternItem.addActionListener(e -> choosePattern());
        colorItem.addActionListener(e -> chooseColor());
        speedItem.addActionListener(e -> chooseSpeed());

        // Menu Ajuda
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        helpItem.addActionListener(e -> showHelpDialog());
        aboutItem.addActionListener(e -> showAboutDialog());

        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    // Métodos de configuração do AnimatedPanel
    private void choosePattern() {
        String[] options = {"Circles", "Squares"};
        String pattern = (String) JOptionPane.showInputDialog(
                this, "Choose the pattern:", "Patterns",
                JOptionPane.QUESTION_MESSAGE, null, options, animatedPanel.getPattern());
        if (pattern != null) {
            animatedPanel.setPattern(pattern);
            setStatus("Pattern changed to: " + pattern);
        }
    }

    private void chooseColor() {
        Color color = JColorChooser.showDialog(this, "Choose color", animatedPanel.getColor());
        if (color != null) {
            animatedPanel.setColor(color);
            setStatus("Color changed");
        }
    }

    private void chooseSpeed() {
        String speedStr = JOptionPane.showInputDialog(this, "Speed (ms per frame):", animatedPanel.getSpeed());
        if (speedStr != null) {
            try {
                int speed = Integer.parseInt(speedStr);
                animatedPanel.setSpeed(speed);
                setStatus("Speed changed to: " + speed + " ms");
            } catch (NumberFormatException ex) {
                setStatus("Invalid value");
            }
        }
    }

    // Diálogo de ajuda personalizado
    private void showHelpDialog() {
        HelpDialog.showHelp(this);
    }

    // Diálogo "Sobre"
    private void showAboutDialog() {
        HelpDialog.showAbout(this);
    }

    private void setStatus(String msg) {
        statusBar.setText(msg);
    }
}
