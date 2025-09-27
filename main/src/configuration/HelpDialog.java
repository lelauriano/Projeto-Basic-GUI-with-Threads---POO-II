package configuration;

import javax.swing.*;
import java.awt.*;

public class HelpDialog {

    // Help dialog
    public static void showHelp(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Help", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new BorderLayout());

// JTextPane com HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
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
                    "<p><img src='" + HelpDialog.class.getResource("menu.jpg") + "' ><br>" +
                    "<b>Open File:</b> Select and display a text file.<br>" +
                    "<b>Close File:</b> Clear the text area.<br>" +
                    "<b>Exit:</b> Close the application.</p>" +

                    "<h2>Settings Menu:</h2>" +
                    "<p><img src='" + HelpDialog.class.getResource("settings.jpg") + "' ><br>" +
                    "<b>Patterns:</b> Choose the background pattern.<br>" +
                    "<b>Colors:</b> Change the background color.<br>" +
                    "<b>Speed:</b> Adjust animation speed.</p>" +

                    "<h2>Help Menu:</h2>" +
                    "<p><img src='" + HelpDialog.class.getResource("help.jpg") + "' ><br>" +
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
        dialog.add(imageLabel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    // About dialog
    public static void showAbout(JFrame parent) {
        JDialog dialog = new JDialog(parent, "About", true);
        dialog.setSize(350, 200);
        dialog.setLayout(new BorderLayout());

        // Info text
        JLabel infoLabel = new JLabel(
            "<html>" +
                "<body style='font-family:sans-serif; font-size:12px;'>" +
                    "* Basic GUI with Threads<br>" +
                    "* Authors: Beatriz, Caio, Giovanni, Julia, Leticia, Rafael<br>" +
                    "* Year: 2025<br>" +
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
