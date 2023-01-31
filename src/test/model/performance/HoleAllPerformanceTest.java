package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoleAllPerformanceTest {
    private Hole h0, h1, h2, h3;
    private HoleAllPerformance hap0, hap1, hap2, hap3, hap4, hap5;
    
    private HoleGolferPerformance h0g1p, h0g2p, h1g1p, h1g2p, h2g1p, h2g2p, h3g1p, h3g2p;
    
    @BeforeEach
    public void setup() {
        hap0 = new HoleAllPerformance(h0);
        hap1 = new HoleAllPerformance(h0);
        hap2 = new HoleAllPerformance(h1);
        hap3 = new HoleAllPerformance(h2);
        hap4 = new HoleAllPerformance(h3);
        hap5 = new HoleAllPerformance(h3);
        
        Golfer g1 = new Golfer("1");
        Golfer g2 = new Golfer("2");
        Golfer g3 = new Golfer("3");
        Golfer g4 = new Golfer("4");
        Golfer g5 = new Golfer("5");
        Golfer g6 = new Golfer("6");
        Golfer g7 = new Golfer("7");
        Golfer g8 = new Golfer("8");
        
        h0g1p = new HoleGolferPerformance(h0, g1, 1);
        h0g2p = new HoleGolferPerformance(h0, g2, 2);
        h1g1p = new HoleGolferPerformance(h1, g3, 3);
        h1g2p = new HoleGolferPerformance(h1, g4, 4);
        h2g1p = new HoleGolferPerformance(h2, g5, 5);
        h2g2p = new HoleGolferPerformance(h2, g6, 6);
        h3g1p = new HoleGolferPerformance(h3, g7, 7);
        h3g2p = new HoleGolferPerformance(h3, g8, 8);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(h0, hap0.getHole());
        assertEquals(h0, hap1.getHole());
        assertEquals(h1, hap2.getHole());
        assertEquals(h2, hap3.getHole());
        assertEquals(h3, hap4.getHole());
        assertEquals(h3, hap5.getHole());
    }
    
    @Test
    public void addGolferPerformanceTest() {
        hap0.addGolferPerformance(h0g1p);
        hap0.addGolferPerformance(h0g2p);
        hap1.addGolferPerformance(h1g1p);
        hap1.addGolferPerformance(h1g2p);
        hap2.addGolferPerformance(h2g1p);
        hap2.addGolferPerformance(h2g2p);
    
        assertEquals(h0g1p, hap0.getHoleGolferPerformances().get(0));
        assertEquals(h0g2p, hap0.getHoleGolferPerformances().get(1));
        assertEquals(h1g1p, hap1.getHoleGolferPerformances().get(0));
        assertEquals(h1g2p, hap1.getHoleGolferPerformances().get(1));
        assertEquals(h2g1p, hap2.getHoleGolferPerformances().get(0));
        assertEquals(h2g2p, hap2.getHoleGolferPerformances().get(1));
    
    }
}

