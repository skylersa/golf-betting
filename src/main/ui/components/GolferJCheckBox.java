package ui.components;

import model.game.Golfer;

import javax.swing.*;

public class GolferJCheckBox extends JCheckBox {
    private Golfer golfer;
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public GolferJCheckBox(Golfer golfer) {
        super(golfer.getName());
        this.golfer = golfer;
    }
    
    public Golfer getGolfer() {
        return golfer;
    }
}
