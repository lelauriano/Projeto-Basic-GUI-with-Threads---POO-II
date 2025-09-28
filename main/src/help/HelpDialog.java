package help;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class HelpDialog {

    // Help dialog/
    public static void showHelp(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Help", true);
        dialog.setSize(800, 500);
        dialog.setLayout(new BorderLayout());
        
        // Getting images from resources for HTML
        URL menuUrl = HelpDialog.class.getResource("/menu.jpg");
        URL settingsUrl = HelpDialog.class.getResource("/settings.jpg");
        URL helpUrl = HelpDialog.class.getResource("/help.jpg");

        String menuImg = (menuUrl != null) ? menuUrl.toExternalForm() : "";
        String settingsImg = (settingsUrl != null) ? settingsUrl.toExternalForm() : "";
        String helpImg = (helpUrl != null) ? helpUrl.toExternalForm() : "";
   
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        
        HTMLEditorKit kit = new HTMLEditorKit();
        textPane.setEditorKit(kit);

        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        if (menuUrl != null) {
            doc.setBase(menuUrl);
        }
        
        textPane.setDocument(doc);

// JTextPane com HTML
        textPane.setText(
            "<html>" +
                "<body style='font-family:sans-serif; font-size:12px;'>" +
                    "<h1 style='text-align:center;'>Application Help</h1>" +
                    "<h2 style='text-align:center;'>This is a Java application with a graphical interface that explores:</h2>" +
                    "<p>- Creating basic interfaces<br>" + 
                    "- File integration<br>" +
                    "- Using threads and customizing components.<br></p>" +

                    "<h2 style='text-align:center;'>This application demonstrates:</h2>" + 
                    "<p>- File reading<br>" + 
                    "- Threads with animations<br>" + 
                    "- Background customization<br>" + 
                    "- Usage of menus and dialogs.<br><br></p>" + 

                    "<h2 style='text-align:center;'>Explanation of menu options:</h2>" + 
                    "<h2>File Menu:</h2>" +
                    "<p><img src='" + menuImg + "' ><br>" +
                    "<b>Open File:</b> Select and display a text file.<br>" +
                    "<b>Close File:</b> Clear the text area.<br>" +
                    "<b>Exit:</b> Close the application.</p>" +

                    "<h2>Settings Menu:</h2>" +
                    "<p><img src='" + settingsImg + "' ><br>" +
                    "<b>Patterns:</b> Choose the background pattern.<br>" +
                    "<b>Colors:</b> Change the background color.<br>" +
                    "<b>Speed:</b> Adjust animation speed.</p>" +

                    "<h2>Help Menu:</h2>" +
                    "<p><img src='" + helpImg + "' ><br>" +
                    "<b>Help:</b> Open this dialog.<br>" +
                    "<b>About:</b> Show project info.</p>" +

                "</body>" +
            "</html>"
        );
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        // Add to layout
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    // About dialog
    public static void showAbout(JFrame parent) {
        JDialog dialog = new JDialog(parent, "About", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new BorderLayout());

        // Info text
        JLabel infoLabel = new JLabel(
            "<html>" +
                "<body style='font-family:sans-serif; font-size:12px;'>" +
                    "* Basic GUI with Threads<br><br>" +
                    "* Version: 1<br><br>" +
                    "* Authors: <br>Beatriz Cristina De Oliveira Jatobá 240421"
                    + "<br>Caio Vinicius Pereira Sousa 260996"
                    + "<br>Giovanni Da Silva Virginio Brandão 288839"
                    + "<br>Julia Fernandes dos Santos 249661"
                    + "<br>Leticia Lauriano De Oliveira 173008"
                    + "<br>Rafael Guilherme Da Silva 260684 <br><br>" +
                    "* Year: 2025<br><br>" +
                    "* Course: Object Oriented Programming II" +
                "</body>" +
            "</html>",
                SwingConstants.CENTER
        );

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        // Add to layout
        dialog.add(infoLabel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

}