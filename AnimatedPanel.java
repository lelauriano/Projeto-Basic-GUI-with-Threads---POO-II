import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class AnimatedPanel extends JPanel implements Runnable {

    // --- PRIVATE ATTRIBUTES THAT DEFINE THE ANIMATION STATE ---
    // The Configuration Menu will change these values.
    private String pattern = "Circles";
    private Color color = Color.CYAN;
    private int speed = 50; // Interval in milliseconds
    
    private boolean running = false;
    private Thread animationThread;

    // ... (Constructor and other methods like run(), paintComponent(), etc.) ...
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Random rand = new Random();

        // The drawing method USES the attributes 'color' and 'pattern'
        g2d.setColor(this.color);
        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight());
            int size = rand.nextInt(40) + 10;
            
            // Rendering changes based on the value of the 'pattern' attribute
            switch (this.pattern) {
                case "Squares":
                    g2d.fillRect(x, y, size, size);
                    break;
                case "Circles":
                default:
                    g2d.fillOval(x, y, size, size);
                    break;
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            repaint();
            try {
                // The thread USES the 'speed' attribute for the pause
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void startAnimation() {
        if (animationThread == null) {
            running = true;
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    // --- PUBLIC METHODS TO CONTROL THE ANIMATION ---
    // These are the methods that the Configuration Menu will call.
    
    /**
     * Sets the pattern of the objects drawn in the animation.
     * @param newPattern The name of the pattern (e.g., "Circles", "Squares").
     */
    public void setPattern(String newPattern) {
        this.pattern = newPattern;
    }

    /**
     * Sets the base color of the objects in the animation.
     * @param newColor The Color object to be used.
     */
    public void setColor(Color newColor) {
        if (newColor != null) {
            this.color = newColor;
        }
    }

    /**
     * Sets the animation speed.
     * @param newSpeed The pause time in milliseconds between frames.
     */
    public void setSpeed(int newSpeed) {
        // Prevents a value too low that could overload the CPU
        this.speed = Math.max(10, newSpeed);
    }
    /**
     * Returns the current animation speed.
     * @return speed in milliseconds
     */
    public int getSpeed() {
        return this.speed;
    }
}