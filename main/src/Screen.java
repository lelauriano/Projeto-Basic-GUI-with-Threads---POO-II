import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Screen extends JFrame {
    private JTextArea fileTextArea;
    private JLabel statusBar;
    private AnimatedPanel animatedPanel;

    public Screen() {
        super("Basic GUI with Threads");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("icon.png").getImage()); // Coloque um ícone chamado icon.png na pasta do projeto

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

        // Menu bar
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Arquivo
        JMenu fileMenu = new JMenu("file");
        JMenuItem openItem = new JMenuItem("Open file");
        JMenuItem closeItem = new JMenuItem("Close file");
        JMenuItem exitItem = new JMenuItem("exit");
        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        openItem.addActionListener(e -> openFile());
        closeItem.addActionListener(e -> fileTextArea.setText(""));
        exitItem.addActionListener(e -> System.exit(0));

        // Configuração
        JMenu configMenu = new JMenu("Settings");
        JMenuItem patternItem = new JMenuItem("patterns");
        JMenuItem colorItem = new JMenuItem("color");
        JMenuItem speedItem = new JMenuItem("speed");
        configMenu.add(patternItem);
        configMenu.add(colorItem);
        configMenu.add(speedItem);

        patternItem.addActionListener(e -> choosePattern());
        colorItem.addActionListener(e -> chooseColor());
        speedItem.addActionListener(e -> chooseSpeed());

        // Ajuda
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("Help");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        helpItem.addActionListener(e -> showHelpDialog());
        aboutItem.addActionListener(e -> showAboutDialog());

        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    // Abrir arquivo
    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                fileTextArea.read(reader, null);
                setStatus("File Charged: " + file.getName());
            } catch (Exception ex) {
                setStatus("Error reading file");
            }
        }
    }

    // Configuração de padrão
    private void choosePattern() {
        String[] options = {"Circles", "Squares"};
        String pattern = (String) JOptionPane.showInputDialog(
                this, "Choose the patterns:", "patterns",
                JOptionPane.QUESTION_MESSAGE, null, options, animatedPanel.getPattern());
        if (pattern != null) {
            animatedPanel.setPattern(pattern);
            setStatus("Patterns changed for: " + pattern);
        }
    }

    // Configuração de cor
    private void chooseColor() {
        Color color = JColorChooser.showDialog(this, "Choose the color", animatedPanel.getColor());
        if (color != null) {
            animatedPanel.setColor(color);
            setStatus("Color changed");
        }
    }

    // Configuração de velocidade
    private void chooseSpeed() {
        String speedStr = JOptionPane.showInputDialog(this, "Speed (ms by frame):", animatedPanel.getSpeed());
        if (speedStr != null) {
            try {
                int speed = Integer.parseInt(speedStr);
                animatedPanel.setSpeed(speed);
                setStatus("Speed changed for: " + speed + " ms");
            } catch (NumberFormatException ex) {
                setStatus("Invalid value");
            }
        }
    }

    // Diálogo de ajuda personalizado
    private void showHelpDialog() {
        JDialog dialog = new JDialog(this, "Help", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("help.png")); // Coloque help.png na pasta do projeto
        JTextArea textArea = new JTextArea(
                "This application shows:\n" +
                "- FIle Reading\n" +
                "- Threads with animations\n" +
                "- Personalizing background\n" +
                "- Menus and dialogs uses\n\n" +
                "Click with the left botton to change the backgroubd color.\n" +
                "Click with the right botton to altern between circles and squares."
        );
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        dialog.add(imageLabel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Diálogo "Sobre"
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "Projeto Basic GUI with Threads\nVersão 1.0\nAutors: Beatriz,Caio, Giovanni,Julia, Leticia and Rafael",
                "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    // Atualiza status
    private void setStatus(String msg) {
        statusBar.setText(msg);
    }
}