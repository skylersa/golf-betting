package ui.gui.components;

import model.game.Golfer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * A JPanel full of GolferJButtons
 */
public class GolferListButtonJPanel extends JPanel {
    List<GolferJButton> buttons;
    
    // EFFECTS: creates a new GolferListButtonJPanel with given list of golfers
    public GolferListButtonJPanel(List<Golfer> golfers) {
        super(new GridLayout(golfers.size(), 1));
        
        buttons = new ArrayList<>();
        for (Golfer golfer: golfers) {
            GolferJButton b = new GolferJButton(golfer);
            buttons.add(b);
            super.add(b);
        }
    }
    
    public List<GolferJButton> getButtons() {
        return buttons;
    }
}
