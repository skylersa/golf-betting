package model;

import model.game.Hole;
import model.performance.HoleGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoleTest {
    private Golfer g0, g1, g2;
    private Hole h0, h1, h2, h3, h4;
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        h0 = new Hole();
        h1 = new Hole();
        h2 = new Hole();
        h3 = new Hole(6);
        h4 = new Hole(1);
    }
    
    @Test
    public void constructorTest() {
        assertTrue(1 <= h0.getPar() && h0.getPar() <= 5);
        assertTrue(1 <= h1.getPar() && h1.getPar() <= 5);
        assertTrue(1 <= h2.getPar() && h2.getPar() <= 5);
        assertEquals(h3.getPar(), 6);
        assertEquals(h4.getPar(), 1);
    }
    
    @Test
    public void playHoleTest() {
        playHoleTestIndividual(h0, g0);
        playHoleTestIndividual(h1, g0);
        playHoleTestIndividual(h2, g0);
        playHoleTestIndividual(h3, g0);
        playHoleTestIndividual(h4, g0);
        playHoleTestIndividual(h0, g1);
        playHoleTestIndividual(h1, g1);
        playHoleTestIndividual(h2, g1);
        playHoleTestIndividual(h3, g1);
        playHoleTestIndividual(h4, g1);
        playHoleTestIndividual(h0, g2);
        playHoleTestIndividual(h1, g2);
        playHoleTestIndividual(h2, g2);
        playHoleTestIndividual(h3, g2);
        playHoleTestIndividual(h4, g2);
        
    }
    
    private void playHoleTestIndividual(Hole hole, Golfer golfer) {
        HoleGolferPerformance hgp = hole.playHole(golfer);
        int maxStrokes = hole.getPar() + hole.getMaxDeviation();
        int minStrokes = hole.getPar() - hole.getMaxDeviation();
        assertTrue((minStrokes <= hgp.getStrokes()) && (hgp.getStrokes() <= maxStrokes));
        
        
    }
}
