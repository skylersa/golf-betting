package ui.gui.components;

import model.performance.HoleAllPerformance;

import javax.swing.*;
import java.awt.*;

/*
 * A JPanel that displays the HAP
 */
public class HolePerformanceJPanel extends JPanel {
    
    // EFFECTS: creates a new HolePerformanceJPanel with given HAP
    public HolePerformanceJPanel(HoleAllPerformance hap) {
        super(new GridLayout(hap.size(), 1));
        for (Integer stroke : hap.getStrokes()) {
            super.add(new JLabel("" + stroke));
        }
    }
}
