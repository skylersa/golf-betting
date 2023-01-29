package model.performance;

import model.Golfer;
import model.game.Hole;

import java.util.ArrayList;

public class HoleAllPerformance {
    private Hole hole;
    private ArrayList<HoleGolferPerformance> holeGolferPerformances;
    
    // EFFECTS: create new HoleAllPerformance on given hole
    public HoleAllPerformance(Hole hole) {
    
    }
    
    // MODIFIES: this
    // EFFECTS: stores given golfer performance on this hole
    public void addGolferPerformance(HoleGolferPerformance performance) {
        this.holeGolferPerformances.add(performance);
    }
    
    
    // REQUIRES: at least one hole performance stored
    // EFFECTS: returns the hole on which players performed the best
    public Golfer getBestPerformingGolfer() {
        return null;
        //TODO getBestPerformingGolfer is a stub
    }
}
