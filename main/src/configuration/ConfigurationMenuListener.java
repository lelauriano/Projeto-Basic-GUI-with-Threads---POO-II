package configuration;

import java.awt.Color;

public interface ConfigurationMenuListener {
    void onPatternChange(String newPattern);
    void onColorChange(Color newColor);
    void onSpeedChange(int newSpeed);
    void onAnimationToggle(boolean running);
    void onReset();
}

