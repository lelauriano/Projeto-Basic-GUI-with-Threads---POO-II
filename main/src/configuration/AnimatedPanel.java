package configuration;

import javax.swing.*;
import java.awt.*;

public class AnimatedPanel extends JPanel implements Runnable {

    private volatile String pattern = "Circles";
    private volatile Color color = Color.CYAN;
    private volatile int speed = 50; // ms per frame
    private volatile boolean running = false;
    private Thread animationThread;

    public AnimatedPanel() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    setRandomColor();
                } else if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    setPattern(getPattern().equals("Circles") ? "Squares" : "Circles");
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        java.util.Random rand = new java.util.Random();

        g2d.setColor(this.color);
        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(Math.max(1, getWidth()));
            int y = rand.nextInt(Math.max(1, getHeight()));
            int size = rand.nextInt(40) + 10;

            if ("Squares".equals(pattern)) {
                g2d.fillRect(x, y, size, size);
            } else {
                g2d.fillOval(x, y, size, size);
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startAnimation() {
        if (!running) {
            running = true;
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    public void stopAnimation() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
            animationThread = null;
        }
    }

    public void setPattern(String newPattern) {
        this.pattern = newPattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void setColor(Color newColor) {
        if (newColor != null) {
            this.color = newColor;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setRandomColor() {
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }

    public void setSpeed(int newSpeed) {
        this.speed = Math.max(10, newSpeed);
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isRunning() {
        return running;
    }
}