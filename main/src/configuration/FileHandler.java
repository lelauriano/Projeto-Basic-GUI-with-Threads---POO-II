package configuration;

import javax.swing.*;
import java.io.*;

public class FileHandler {
    private JTextArea textArea;
    private JLabel statusBar;

    public FileHandler(JTextArea textArea, JLabel statusBar) {
        this.textArea = textArea;
        this.statusBar = statusBar;
    }

    public void openFile(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                setStatus("File loaded: " + file.getName());
            } catch (Exception e) {
                setStatus("Error reading file");
            }
        }
    }

    public void clearFile() {
        textArea.setText("");
        setStatus("File cleared");
    }

    private void setStatus(String msg) {
        if (statusBar != null) {
            statusBar.setText(msg);
        }
    }
}
