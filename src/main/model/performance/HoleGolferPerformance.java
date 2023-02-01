package model.performance;

import model.game.Golfer;
import model.game.Hole;

/*
 * Represents the performance of one golfer on one hole.
 * This class is immutable and is the granular performance that the rest of the performance packages hold and manage
 * See Performance structure.jpg for details the structure of performance holders
 */
public final class HoleGolferPerformance {
    private final Hole hole;
    private final Golfer golfer;
    private final int strokes;
    
    // REQUIRES: strokes > 0
    // EFFECTS: create new HoleAllPerformance with given hole and golfer of given number of strokes
    public HoleGolferPerformance(Hole hole, Golfer golfer, int strokes) {
        this.hole = hole;
        this.golfer = golfer;
        this.strokes = strokes;
    }
    
    // EFFECTS: returns golfer's performance compared to par
    //          < means better score, -ve means below par, +ve means above par
    public int getParDeviation() {
        return strokes - hole.getPar();
    }
    
    public int getStrokes() {
        return this.strokes;
    }
    
    public Hole getHole() {
        return this.hole;
    }
    
    public Golfer getGolfer() {
        return this.golfer;
    }
}
