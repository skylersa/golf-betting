package model.performance;

import model.game.Golfer;
import model.game.Hole;

/*
 * Represents the performance of one golfer on one hole.
 * transient class only, not for storage.
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
