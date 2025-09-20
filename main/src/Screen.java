import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private JLabel statusLabel;

    public Screen() {
        // Configurações da janela
        setTitle("Basic GUI with Threads");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centraliza na tela

        // Ícone da aplicação (troque "icon.png" pelo caminho certo)
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        // Barra de menus
        setJMenuBar(createMenuBar());

        // Barra de status
        add(createStatusBar(), BorderLayout.SOUTH);

        // Torna a janela visível
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Arquivo
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem openItem = new JMenuItem("Abrir Arquivo");
        JMenuItem closeItem = new JMenuItem("Fechar Arquivo");
        JMenuItem exitItem = new JMenuItem("Sair");

        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Menu Configuração
        JMenu configMenu = new JMenu("Configuração");
        JMenuItem defaultItem = new JMenuItem("Padrões");
        JMenuItem colorItem = new JMenuItem("Cores");
        JMenuItem speedItem = new JMenuItem("Velocidade");

        configMenu.add(defaultItem);
        configMenu.add(colorItem);
        configMenu.add(speedItem);

        // Menu Ajuda
        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem helpItem = new JMenuItem("Ajuda");
        JMenuItem aboutItem = new JMenuItem("Sobre");

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Adiciona menus à barra
        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JPanel createStatusBar() {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Pronto");
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(statusLabel, BorderLayout.WEST);
        return statusPanel;
    }

    // Atualiza texto da barra de status
    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    // Classe main para rodar o programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Screen::new);
    }
}
