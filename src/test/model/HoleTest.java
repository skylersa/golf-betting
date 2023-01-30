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
        HoleGolferPerformance playh0g0 = h0.playHole(g0);
        HoleGolferPerformance playh1g0 = h1.playHole(g0);
        HoleGolferPerformance playh2g0 = h2.playHole(g0);
        HoleGolferPerformance playh3g0 = h3.playHole(g0);
        HoleGolferPerformance playh4g0 = h4.playHole(g0);
        HoleGolferPerformance playh0g1 = h0.playHole(g1);
        HoleGolferPerformance playh1g1 = h1.playHole(g1);
        HoleGolferPerformance playh2g1 = h2.playHole(g1);
        HoleGolferPerformance playh3g1 = h3.playHole(g1);
        HoleGolferPerformance playh4g1 = h4.playHole(g1);
        HoleGolferPerformance playh0g1 = h0.playHole(g1);
        HoleGolferPerformance playh1g2 = h1.playHole(g2);
        HoleGolferPerformance playh2g2 = h2.playHole(g2);
        HoleGolferPerformance playh3g2 = h3.playHole(g2);
        HoleGolferPerformance playh4g2 = h4.playHole(g2);
        
        int h0Max = h0.getPar() + h0.getMaxDeviation();
        int h1Max = h1.getPar() + h1.getMaxDeviation();
        int h2Max = h2.getPar() + h2.getMaxDeviation();
        int h3Max = h3.getPar() + h3.getMaxDeviation();
        int h4Max = h4.getPar() + h4.getMaxDeviation();
        
        int h0Min = h0.getPar() - h0.getMaxDeviation();
        int h1Min = h1.getPar() - h1.getMaxDeviation();
        int h2Min = h2.getPar() - h2.getMaxDeviation();
        int h3Min = h3.getPar() - h3.getMaxDeviation();
        int h4Min = h4.getPar() - h4.getMaxDeviation();
        
        
//        assertTrue(h0Min <= playh0g0 && playh0g0 <=;
//        assertTrue(h1Min <= playh1g0 && playh1g0 <=;
//        assertTrue(h2Min <= playh2g0 && playh2g0 <=;
//        assertTrue(h3Min <= playh3g0 && playh3g0 <=;
//        assertTrue(h4Min <= playh4g0 && playh4g0 <=;
//        assertTrue(h0Min <= playh0g1 && playh0g1 <=;
//        assertTrue(h1Min <= playh1g1 && playh1g1 <=;
//        assertTrue(h2Min <= playh2g1 && playh2g1 <=;
//        assertTrue(h3Min <= playh3g1 && playh3g1 <=;
//        assertTrue(h4Min <= playh4g1 && playh4g1 <=;
//        assertTrue(h0Min <= playh0g1 && playh0g1 <=;
//        assertTrue(h1Min <= playh1g2 && playh1g2 <=;
//        assertTrue(h2Min <= playh2g2 && playh2g2 <=;
//        assertTrue(h3Min <= playh3g2 && playh3g2 <=;
//        assertTrue(h4Min <= playh4g2 && playh4g2 <=;
    
    
    }
}
