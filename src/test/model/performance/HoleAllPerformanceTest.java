package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HoleAllPerformanceTest {
    private Golfer g0, g1, g2, g3;
    private HoleAllPerformance hap0, hap1, hap2, hap3;
    private List<Golfer> golfers0, golfers1, golfers2, golfers3;
    private List<Integer> strokes0, strokes1, strokes2, strokes3;
    
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("0");
        g1 = new Golfer("1");
        g2 = new Golfer("2");
        g3 = new Golfer("3");
        golfers0 = new ArrayList<>(Arrays.asList(g0));
        golfers1 = new ArrayList<>(Arrays.asList(g0, g1));
        golfers2 = new ArrayList<>(Arrays.asList(g0, g1, g2));
        golfers3 = new ArrayList<>(Arrays.asList(g0, g1, g2, g3));
        
        strokes0 = new ArrayList<>(Arrays.asList(5));
        strokes1 = new ArrayList<>(Arrays.asList(4, 3));
        strokes2 = new ArrayList<>(Arrays.asList(6, 1, 8));
        strokes3 = new ArrayList<>(Arrays.asList(2, 5, 9, 4));
        
        
        hap0 = new HoleAllPerformance(new Hole(0), golfers0, strokes0);
        hap1 = new HoleAllPerformance(new Hole(1), golfers1, strokes1);
        hap2 = new HoleAllPerformance(new Hole(2), golfers2, strokes2);
        hap3 = new HoleAllPerformance(new Hole(3), golfers3, strokes3);
    }
    
    @Test
    public void constructorAndGetterTest() {
        assertEquals(strokes0, hap0.getStrokes());
        assertEquals(strokes1, hap1.getStrokes());
        assertEquals(strokes2, hap2.getStrokes());
        assertEquals(strokes3, hap3.getStrokes());
        
        assertEquals(golfers0, hap0.getGolfers());
        assertEquals(golfers1, hap1.getGolfers());
        assertEquals(golfers2, hap2.getGolfers());
        assertEquals(golfers3, hap3.getGolfers());
        
        assertEquals(golfers0.get(0), hap0.getGolfer(0));
        
        assertEquals(golfers1.get(0), hap1.getGolfer(0));
        assertEquals(golfers1.get(1), hap1.getGolfer(1));
        
        assertEquals(golfers2.get(0), hap2.getGolfer(0));
        assertEquals(golfers2.get(1), hap2.getGolfer(1));
        assertEquals(golfers2.get(2), hap2.getGolfer(2));
        
        assertEquals(golfers3.get(0), hap3.getGolfer(0));
        assertEquals(golfers3.get(1), hap3.getGolfer(1));
        assertEquals(golfers3.get(2), hap3.getGolfer(2));
        assertEquals(golfers3.get(3), hap3.getGolfer(3));
        
        assertEquals(strokes0.get(0), hap0.getStroke(0));
        
        assertEquals(strokes1.get(0), hap1.getStroke(0));
        assertEquals(strokes1.get(1), hap1.getStroke(1));
        
        assertEquals(strokes2.get(0), hap2.getStroke(0));
        assertEquals(strokes2.get(1), hap2.getStroke(1));
        assertEquals(strokes2.get(2), hap2.getStroke(2));
        
        assertEquals(strokes3.get(0), hap3.getStroke(0));
        assertEquals(strokes3.get(1), hap3.getStroke(1));
        assertEquals(strokes3.get(2), hap3.getStroke(2));
        assertEquals(strokes3.get(3), hap3.getStroke(3));
    }
    
    @Test
    public void sizeTest() {
        assertEquals(1, hap0.size());
        assertEquals(2, hap1.size());
        assertEquals(3, hap2.size());
        assertEquals(4, hap3.size());
    }
    
    @Test
    public void constructorThrowsInputMismatchTest() {
        try {
            new HoleAllPerformance(
                    new Hole(),
                    Arrays.asList(new Golfer("j"), new Golfer("")),
                    Arrays.asList(1, 2, 3));
            fail();
        } catch (InputMismatchException e) {
            // Pass
        }
        // Pass
    }
    
    @Test
    public void getBestGolferTest() {
        assertEquals(g0, hap0.getBestGolfer());
        assertEquals(g1, hap1.getBestGolfer());
        assertEquals(g1, hap2.getBestGolfer());
        assertEquals(g0, hap3.getBestGolfer());
    }
}

