package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoleGolferPerformanceTest {
    Hole h0, h1, h2, h3;
    Golfer g0, g1, g2, g3;
    HoleGolferPerformance hgp0, hgp1, hgp2, hgp3;
    
    @BeforeEach
    public void setup() {
        h0 = new Hole(4);
        h1 = new Hole(6);
        h2 = new Hole(8);
        h3 = new Hole(10);
    
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
        
        hgp0 = new HoleGolferPerformance(h0, g0, 3);
        hgp1 = new HoleGolferPerformance(h1, g1, 7);
        hgp2 = new HoleGolferPerformance(h2, g2, 2);
        hgp3 = new HoleGolferPerformance(h3, g3, 6);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(h0, hgp0.getHole());
        assertEquals(h1, hgp1.getHole());
        assertEquals(h2, hgp2.getHole());
        assertEquals(h3, hgp3.getHole());
    
        assertEquals(g0, hgp0.getGolfer());
        assertEquals(g1, hgp1.getGolfer());
        assertEquals(g2, hgp2.getGolfer());
        assertEquals(g3, hgp3.getGolfer());
    
        assertEquals(3, hgp0.getStrokes());
        assertEquals(7, hgp1.getStrokes());
        assertEquals(2, hgp2.getStrokes());
        assertEquals(6, hgp3.getStrokes());
    }
}
