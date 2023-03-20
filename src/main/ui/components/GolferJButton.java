package ui.components;

import model.game.Golfer;

import javax.swing.*;

public class GolferJButton extends JButton {
    private Golfer golfer;
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public GolferJButton(Golfer golfer) {
        super(golfer.getName());
        this.golfer = golfer;
    }
    
    public Golfer getGolfer() {
        return golfer;
    }
}
