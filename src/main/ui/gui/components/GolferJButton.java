package ui.gui.components;

import model.game.Golfer;

import javax.swing.*;

/*
 * A JButton that stores and is labelled with the given golfer's name
 */
public class GolferJButton extends JButton {
    private Golfer golfer;
    
    // EFFECTS: creates new GolferJButton with given golfer
    public GolferJButton(Golfer golfer) {
        super(golfer.getName());
        this.golfer = golfer;
    }
    
    public Golfer getGolfer() {
        return golfer;
    }
}
