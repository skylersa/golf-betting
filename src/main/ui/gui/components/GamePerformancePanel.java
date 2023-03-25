package ui.gui.components;

import model.game.Hole;
import model.performance.GameAllPerformance;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * A JPanel that displays a set of hole performances
 */
public class GamePerformancePanel extends JPanel {
    private GameAllPerformance gap;
    private CardLayout card;
    private List<HolePerformanceJPanel> panels;
    private int cursor = 0;
    
    // EFFECTS: creates new GamePerformancePanel with given GAP
    public GamePerformancePanel(GameAllPerformance gap) {
        super();
        this.gap = gap;
        this.card = new CardLayout();
        super.setLayout(card);
        
        panels = new ArrayList<>();
        for (Hole hole : gap.getHoles()) {
            HolePerformanceJPanel eachHolePanel = new HolePerformanceJPanel(gap.getHolePerformance(hole));
            panels.add(eachHolePanel);
            super.add(eachHolePanel);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: switches to the next hole performance
    //       returns whether it is out of panels
    public boolean next() {
        boolean last = false;
        cursor++;
        if (cursor >= gap.getHoles().size()) {
            last = true;
        }
        card.next(this);
        
        return last;
    }
    
    public GameAllPerformance getGap() {
        return gap;
    }
}
