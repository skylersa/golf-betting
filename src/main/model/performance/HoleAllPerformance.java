package model.performance;

import model.game.Golfer;
import model.game.Hole;

import java.util.ArrayList;

/*
 * Represents the performance of all the golfers in a game on one hole.
 * Like GameGolferPerformance, it holds a list of holeGolferPerformances. But, these are organized by hole (rather than
 * by golfer line in GameGolferPerformance)
 * See Performance structure.jpg for details the structure of performance holders
 */
public class HoleAllPerformance {
    private final Hole hole;
    private ArrayList<HoleGolferPerformance> holeGolferPerformances;
    
    // EFFECTS: create new HoleAllPerformance on given hole
    public HoleAllPerformance(Hole hole) {
        this.hole = hole;
        holeGolferPerformances = new ArrayList<>();
    }
    
    // REQUIRES: performance.getHole() == this.getHole()
    // MODIFIES: this
    // EFFECTS: stores given golfer performance on this hole
    public void addGolferPerformance(HoleGolferPerformance performance) {
        this.holeGolferPerformances.add(performance);
    }
    
    //TODO test este
    // REQUIRES: at least one hole performance stored
    // EFFECTS: returns the hole on which players performed the best (lowest strokes)
    public Golfer getBestPerformingGolfer() {
        Golfer bestGolfer = new Golfer("PLACEHOLDER GOLFER");
        int bestScore = 100;
        for (HoleGolferPerformance performance : holeGolferPerformances) {
            if (performance.getStrokes() < bestScore) {
                bestGolfer = performance.getGolfer();
                bestScore = performance.getStrokes();
            }
        }
        return bestGolfer;
    }
    
    
    public ArrayList<HoleGolferPerformance> getHoleGolferPerformances() {
        return holeGolferPerformances;
    }
    
    public Hole getHole() {
        return hole;
    }
}
