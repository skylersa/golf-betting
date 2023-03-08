package model.game;

import model.performance.HoleGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoleTest {
    private Golfer g0, g1, g2;
    private Hole h0, h1, h2, h3, h4, h5, h6, h7, h8, h9;
    
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
        h5 = new Hole();
        h6 = new Hole();
        h7 = new Hole();
        h8 = new Hole();
        h9 = new Hole();
        
    }
    
    @Test
    public void constructorTest() {
        assertTrue(Hole.MIN_PAR <= h0.getPar());
        assertTrue(Hole.MIN_PAR <= h1.getPar());
        assertTrue(Hole.MIN_PAR <= h2.getPar());
        assertTrue(Hole.MIN_PAR <= h5.getPar());
        assertTrue(Hole.MIN_PAR <= h6.getPar());
        assertTrue(Hole.MIN_PAR <= h7.getPar());
        assertTrue(Hole.MIN_PAR <= h8.getPar());
        assertTrue(Hole.MIN_PAR <= h9.getPar());
        assertTrue(h0.getPar() <= Hole.MAX_PAR);
        assertTrue(h1.getPar() <= Hole.MAX_PAR);
        assertTrue(h2.getPar() <= Hole.MAX_PAR);
        assertTrue(h5.getPar() <= Hole.MAX_PAR);
        assertTrue(h6.getPar() <= Hole.MAX_PAR);
        assertTrue(h7.getPar() <= Hole.MAX_PAR);
        assertTrue(h8.getPar() <= Hole.MAX_PAR);
        assertTrue(h9.getPar() <= Hole.MAX_PAR);
        assertEquals(6, h3.getPar());
        assertEquals(1, h4.getPar());
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
    
    @Test
    public void tojsonTest() {
        assertEquals(h0.getPar(), h0.toJson().getInt("par"));
        assertEquals(h1.getPar(), h1.toJson().getInt("par"));
        assertEquals(h2.getPar(), h2.toJson().getInt("par"));
        assertEquals(h3.getPar(), h3.toJson().getInt("par"));
        assertEquals(h4.getPar(), h4.toJson().getInt("par"));
        assertEquals(h5.getPar(), h5.toJson().getInt("par"));
        assertEquals(h6.getPar(), h6.toJson().getInt("par"));
        assertEquals(h7.getPar(), h7.toJson().getInt("par"));
        assertEquals(h8.getPar(), h8.toJson().getInt("par"));
        assertEquals(h9.getPar(), h9.toJson().getInt("par"));
    }
}
