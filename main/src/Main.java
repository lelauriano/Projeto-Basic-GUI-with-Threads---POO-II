import configuration.Screen;

public class Main{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Screen();
        });
    }
}