package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameAllPerformanceTest {
    private GameAllPerformance gap1x1, gap2x2, gap3x4, gap4x3; // holesxgolfers
    private Golfer g0, g1, g2, g3;
    private Hole h0, h1, h2, h3;
    private List<Hole> holes1, holes2, holes3, holes4;
    private List<Golfer> golfers1, golfers2, golfers4, golfers3;
    
    @BeforeEach
    public void setup() {
        h0 = new Hole();
        h1 = new Hole();
        h2 = new Hole();
        h3 = new Hole();
        
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
        
        holes1 = new ArrayList<>(Arrays.asList(h3));
        holes2 = new ArrayList<>(Arrays.asList(h3, h2));
        holes3 = new ArrayList<>(Arrays.asList(h2, h3, h1));
        holes4 = new ArrayList<>(Arrays.asList(h3, h2, h1, h0));
        
        golfers1 = new ArrayList<>(Arrays.asList(g1));
        golfers2 = new ArrayList<>(Arrays.asList(g2, g3));
        golfers4 = new ArrayList<>(Arrays.asList(g0, g1, g2, g3));
        golfers3 = new ArrayList<>(Arrays.asList(g1, g2, g3));
        
        gap1x1 = new GameAllPerformance(holes1, golfers1);
        gap2x2 = new GameAllPerformance(holes2, golfers2);
        gap3x4 = new GameAllPerformance(holes3, golfers4);
        gap4x3 = new GameAllPerformance(holes4, golfers3);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(holes1, gap1x1.getHoles());
        assertEquals(holes2, gap2x2.getHoles());
        assertEquals(holes3, gap3x4.getHoles());
        assertEquals(holes4, gap4x3.getHoles());
        
        assertEquals(golfers1, gap1x1.getGolfers());
        assertEquals(golfers2, gap2x2.getGolfers());
        assertEquals(golfers4, gap3x4.getGolfers());
        assertEquals(golfers3, gap4x3.getGolfers());
    }
    
    
    @Test
    public void addHolePerformanceTest() {
        inputStrokes();
        testHoleStrokes();
        testGolferStrokesByIndexGet();
        testGolferStrokesByGolferGet();
    }
    
    private void testGolferStrokesByIndexGet() {
        assertEquals( 0, gap1x1.getGolferPerformance(0).getStrokes().get(0));
    
        assertEquals( 1, gap2x2.getGolferPerformance(0).getStrokes().get(0));
        assertEquals( 2, gap2x2.getGolferPerformance(1).getStrokes().get(0));
        assertEquals( 3, gap2x2.getGolferPerformance(0).getStrokes().get(1));
        assertEquals( 4, gap2x2.getGolferPerformance(1).getStrokes().get(1));
    
        assertEquals( 5, gap3x4.getGolferPerformance(0).getStrokes().get(0));
        assertEquals( 6, gap3x4.getGolferPerformance(1).getStrokes().get(0));
        assertEquals( 7, gap3x4.getGolferPerformance(2).getStrokes().get(0));
        assertEquals( 8, gap3x4.getGolferPerformance(3).getStrokes().get(0));
        assertEquals( 9, gap3x4.getGolferPerformance(0).getStrokes().get(1));
        assertEquals(10, gap3x4.getGolferPerformance(1).getStrokes().get(1));
        assertEquals(11, gap3x4.getGolferPerformance(2).getStrokes().get(1));
        assertEquals(12, gap3x4.getGolferPerformance(3).getStrokes().get(1));
        assertEquals(13, gap3x4.getGolferPerformance(0).getStrokes().get(2));
        assertEquals(14, gap3x4.getGolferPerformance(1).getStrokes().get(2));
        assertEquals(15, gap3x4.getGolferPerformance(2).getStrokes().get(2));
        assertEquals(16, gap3x4.getGolferPerformance(3).getStrokes().get(2));
    
        assertEquals(17, gap4x3.getGolferPerformance(0).getStrokes().get(0));
        assertEquals(18, gap4x3.getGolferPerformance(1).getStrokes().get(0));
        assertEquals(19, gap4x3.getGolferPerformance(2).getStrokes().get(0));
        assertEquals(20, gap4x3.getGolferPerformance(0).getStrokes().get(1));
        assertEquals(21, gap4x3.getGolferPerformance(1).getStrokes().get(1));
        assertEquals(22, gap4x3.getGolferPerformance(2).getStrokes().get(1));
        assertEquals(23, gap4x3.getGolferPerformance(0).getStrokes().get(2));
        assertEquals(24, gap4x3.getGolferPerformance(1).getStrokes().get(2));
        assertEquals(25, gap4x3.getGolferPerformance(2).getStrokes().get(2));
        assertEquals(26, gap4x3.getGolferPerformance(0).getStrokes().get(3));
        assertEquals(27, gap4x3.getGolferPerformance(1).getStrokes().get(3));
        assertEquals(28, gap4x3.getGolferPerformance(2).getStrokes().get(3));
    
    }
    
    private void testGolferStrokesByGolferGet() {
        assertEquals( 0, gap1x1.getGolferPerformance(g1).getStrokes().get(0));
        
        assertEquals( 1, gap2x2.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals( 2, gap2x2.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals( 3, gap2x2.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals( 4, gap2x2.getGolferPerformance(g3).getStrokes().get(1));
       
        assertEquals( 5, gap3x4.getGolferPerformance(g0).getStrokes().get(0));
        assertEquals( 6, gap3x4.getGolferPerformance(g1).getStrokes().get(0));
        assertEquals( 7, gap3x4.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals( 8, gap3x4.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals( 9, gap3x4.getGolferPerformance(g0).getStrokes().get(1));
        assertEquals(10, gap3x4.getGolferPerformance(g1).getStrokes().get(1));
        assertEquals(11, gap3x4.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals(12, gap3x4.getGolferPerformance(g3).getStrokes().get(1));
        assertEquals(13, gap3x4.getGolferPerformance(g0).getStrokes().get(2));
        assertEquals(14, gap3x4.getGolferPerformance(g1).getStrokes().get(2));
        assertEquals(15, gap3x4.getGolferPerformance(g2).getStrokes().get(2));
        assertEquals(16, gap3x4.getGolferPerformance(g3).getStrokes().get(2));
        
        assertEquals(17, gap4x3.getGolferPerformance(g1).getStrokes().get(0));
        assertEquals(18, gap4x3.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals(19, gap4x3.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals(20, gap4x3.getGolferPerformance(g1).getStrokes().get(1));
        assertEquals(21, gap4x3.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals(22, gap4x3.getGolferPerformance(g3).getStrokes().get(1));
        assertEquals(23, gap4x3.getGolferPerformance(g1).getStrokes().get(2));
        assertEquals(24, gap4x3.getGolferPerformance(g2).getStrokes().get(2));
        assertEquals(25, gap4x3.getGolferPerformance(g3).getStrokes().get(2));
        assertEquals(26, gap4x3.getGolferPerformance(g1).getStrokes().get(3));
        assertEquals(27, gap4x3.getGolferPerformance(g2).getStrokes().get(3));
        assertEquals(28, gap4x3.getGolferPerformance(g3).getStrokes().get(3));
    }
    
    private void testHoleStrokes() {
        assertEquals(0, gap1x1.getHolePerformance(h3).getStrokes().get(0));
    
        assertEquals( 1, gap2x2.getHolePerformance(h3).getStrokes().get(0));
        assertEquals( 2, gap2x2.getHolePerformance(h3).getStrokes().get(1));
        assertEquals( 3, gap2x2.getHolePerformance(h2).getStrokes().get(0));
        assertEquals( 4, gap2x2.getHolePerformance(h2).getStrokes().get(1));
     
        assertEquals( 5, gap3x4.getHolePerformance(h2).getStrokes().get(0));
        assertEquals( 6, gap3x4.getHolePerformance(h2).getStrokes().get(1));
        assertEquals( 7, gap3x4.getHolePerformance(h2).getStrokes().get(2));
        assertEquals( 8, gap3x4.getHolePerformance(h2).getStrokes().get(3));
        assertEquals( 9, gap3x4.getHolePerformance(h3).getStrokes().get(0));
        assertEquals(10, gap3x4.getHolePerformance(h3).getStrokes().get(1));
        assertEquals(11, gap3x4.getHolePerformance(h3).getStrokes().get(2));
        assertEquals(12, gap3x4.getHolePerformance(h3).getStrokes().get(3));
        assertEquals(13, gap3x4.getHolePerformance(h1).getStrokes().get(0));
        assertEquals(14, gap3x4.getHolePerformance(h1).getStrokes().get(1));
        assertEquals(15, gap3x4.getHolePerformance(h1).getStrokes().get(2));
        assertEquals(16, gap3x4.getHolePerformance(h1).getStrokes().get(3));
    
        assertEquals(17, gap4x3.getHolePerformance(h3).getStrokes().get(0));
        assertEquals(18, gap4x3.getHolePerformance(h3).getStrokes().get(1));
        assertEquals(19, gap4x3.getHolePerformance(h3).getStrokes().get(2));
        assertEquals(20, gap4x3.getHolePerformance(h2).getStrokes().get(0));
        assertEquals(21, gap4x3.getHolePerformance(h2).getStrokes().get(1));
        assertEquals(22, gap4x3.getHolePerformance(h2).getStrokes().get(2));
        assertEquals(23, gap4x3.getHolePerformance(h1).getStrokes().get(0));
        assertEquals(24, gap4x3.getHolePerformance(h1).getStrokes().get(1));
        assertEquals(25, gap4x3.getHolePerformance(h1).getStrokes().get(2));
        assertEquals(26, gap4x3.getHolePerformance(h0).getStrokes().get(0));
        assertEquals(27, gap4x3.getHolePerformance(h0).getStrokes().get(1));
        assertEquals(28, gap4x3.getHolePerformance(h0).getStrokes().get(2));
    }
    
    private void inputStrokes() {
        gap1x1.addHolePerformance(new HoleGolferPerformance(h3, g1, 0));
    
        gap2x2.addHolePerformance(new HoleGolferPerformance(h3, g2, 1));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h3, g3, 2));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h2, g2, 3));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h2, g3, 4));
    
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g0, 5));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g1, 6));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g2, 7));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g3, 8));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g0, 9));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g1, 10));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g2, 11));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g3, 12));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g0, 13));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g1, 14));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g2, 15));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g3, 16));
    
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g1, 17));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g2, 18));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g3, 19));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g1, 20));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g2, 21));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g3, 22));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g1, 23));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g2, 24));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g3, 25));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g1, 26));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g2, 27));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g3, 28));
    }
    
    @Test
    public void getBestPerformingGolferTest() {
    
    }
}
