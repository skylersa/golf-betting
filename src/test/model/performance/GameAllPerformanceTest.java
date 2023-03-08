package model.performance;

import exceptions.GolferNotPresentException;
import exceptions.HoleNotPresentException;
import model.game.Golfer;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        
        gap1x1 = new GameAllPerformance(holes1, golfers1, "place holder");
        gap2x2 = new GameAllPerformance(holes2, golfers2, "place holder");
        gap3x4 = new GameAllPerformance(holes3, golfers4, "place holder");
        gap4x3 = new GameAllPerformance(holes4, golfers3, "place holder");
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
    public void addHolePerformanceTestThrowsNotPresentException() {
        try {
            gap1x1.addHolePerformance(new HoleGolferPerformance(new Hole(), g1, 4));
            fail();
        } catch (GolferNotPresentException e) {
            fail();
        } catch (HoleNotPresentException e) {
            // Pass
        }
        // Pass
        try {
            gap2x2.addHolePerformance(new HoleGolferPerformance(h3, g1, 4));
            fail();
        } catch (GolferNotPresentException e) {
            // Pass
        } catch (HoleNotPresentException e) {
            fail();
        }
        // Pass
        try {
            gap1x1.addHolePerformance(new HoleGolferPerformance(new Hole(), new Golfer(""), 4));
            fail();
        } catch (GolferNotPresentException e) {
            // Pass
        } catch (HoleNotPresentException e) {
            fail();
        }
        // Pass
    
    
    }
    
    @Test
    public void getHolePerformanceThrowsHoleNotPresentException() {
        try {
            gap4x3.getHolePerformance(new Hole());
            fail();
        } catch (HoleNotPresentException e) {
         // Pass
        }
        // Pass
    }
    
    @Test
    public void getGolferPerformanceThrowsHoleNotPresentException() {
        try {
            gap4x3.getGolferPerformance(new Golfer(""));
            fail();
        } catch (GolferNotPresentException e) {
            // Pass
        }
        // Pass
    }
}
