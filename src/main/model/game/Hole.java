package model.game;

import model.performance.HoleGolferPerformance;

/*
 * Represents a hole on a golf course with an expected number of strokes to sink the hole (par)
 * Note: I've made the choice to include the playHole method on this class (rather than the golfer)
 */
public class Hole {
    private final int par; // expected # of strokes
    private final int maxDeviation;
    
    // EFFECTS: create new hole with random[1,5] par
    public Hole() {
        this((int)(Math.random() * 5) + 1);
    }
    
    // REQUIRES: par >= 1
    // EFFECTS: create hole with given par
    public Hole(int par) {
        this.par = par;
        this.maxDeviation = this.par - 1;
    }
    
    // EFFECTS: returns the strokes taken for given golfer to sink this hole
    public HoleGolferPerformance playHole(Golfer golfer) {
        int deviation = (int)(Math.random() * (maxDeviation) * 2) - maxDeviation;
        int strokes = this.par + deviation;

        return new HoleGolferPerformance(this, golfer, strokes);
    }
    
    public int getPar() {
        return this.par;
    }
    
    public int getMaxDeviation() {
        return maxDeviation;
    }
}
