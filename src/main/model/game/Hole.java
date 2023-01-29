package model.game;

import model.Golfer;
import model.performance.HoleGolferPerformance;

public class Hole {
    private final int par; // expected # of strokes
    //private int obstacleLevel;
    
    // EFFECTS: create new hole with random[1,5] par
    public Hole() {
        this((int)(Math.random() * 5) + 1);
    }
    
    // REQUIRES: par >= 1
    // EFFECTS: create hole with given par
    public Hole(int par) {
        this.par = par;
    }
    
    // EFFECTS: returns the strokes taken for given golfer to sink thi hole
    public HoleGolferPerformance playHole(Golfer golfer) {
        int maxDeviation = this.par - 1;
        int deviation = (int)(Math.random() * (maxDeviation) * 2) - maxDeviation;
        int strokes = this.par + deviation;

        return new HoleGolferPerformance(this, golfer, strokes);
    }
    
    public int getPar() {
        return this.par;
    }
}
