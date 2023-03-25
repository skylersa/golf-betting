package ui.gui.components;

import model.game.Golfer;

import javax.swing.*;

/*
 * A JCheckBox that stores and is labelled with the given golfer's name
 */
public class GolferJCheckBox extends JCheckBox {
    private Golfer golfer;
    
    // EFFECTS: creates a new GolferJCheckBox with given golfer
    public GolferJCheckBox(Golfer golfer) {
        super(golfer.getName());
        this.golfer = golfer;
    }
    
    public Golfer getGolfer() {
        return golfer;
    }
}
