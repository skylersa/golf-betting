package model.performance;

import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameGolferPerformanceTest {
    private Hole h0, h1, h2, h3;
    private GameGolferPerformance hap0, hap1, hap2, hap3;
    private List<Hole> holes0, holes1, holes2, holes3;
    private List<Integer> strokes0, strokes1, strokes2, strokes3;
    
    
    @BeforeEach
    public void setup() {
        h0 = new Hole(0);
        h1 = new Hole(1);
        h2 = new Hole(2);
        h3 = new Hole(3);
        holes0 = new ArrayList<>(Arrays.asList(h0));
        holes1 = new ArrayList<>(Arrays.asList(h0, h1));
        holes2 = new ArrayList<>(Arrays.asList(h0, h1, h2));
        holes3 = new ArrayList<>(Arrays.asList(h0, h1, h2, h3));
        
        strokes0 = new ArrayList<>(Arrays.asList(5));
        strokes1 = new ArrayList<>(Arrays.asList(4, 3));
        strokes2 = new ArrayList<>(Arrays.asList(6, 1, 8));
        strokes3 = new ArrayList<>(Arrays.asList(2, 5, 9, 4));
        
        
        hap0 = new GameGolferPerformance(holes0, strokes0, "course0");
        hap1 = new GameGolferPerformance(holes1, strokes1, "course1");
        hap2 = new GameGolferPerformance(holes2, strokes2, "course2");
        hap3 = new GameGolferPerformance(holes3, strokes3, "course3");
    }
    
    @Test
    public void constructorAndGetterTest() {
        assertEquals(strokes0, hap0.getStrokes());
        assertEquals(strokes1, hap1.getStrokes());
        assertEquals(strokes2, hap2.getStrokes());
        assertEquals(strokes3, hap3.getStrokes());
        
        assertEquals(holes0, hap0.getHoles());
        assertEquals(holes1, hap1.getHoles());
        assertEquals(holes2, hap2.getHoles());
        assertEquals(holes3, hap3.getHoles());
    
        assertEquals("course0", hap0.getCourseName());
        assertEquals("course1", hap1.getCourseName());
        assertEquals("course2", hap2.getCourseName());
        assertEquals("course3", hap3.getCourseName());
    
    
        assertEquals(holes0.get(0), hap0.getHole(0));
        
        assertEquals(holes1.get(0), hap1.getHole(0));
        assertEquals(holes1.get(1), hap1.getHole(1));
        
        assertEquals(holes2.get(0), hap2.getHole(0));
        assertEquals(holes2.get(1), hap2.getHole(1));
        assertEquals(holes2.get(2), hap2.getHole(2));
        
        assertEquals(holes3.get(0), hap3.getHole(0));
        assertEquals(holes3.get(1), hap3.getHole(1));
        assertEquals(holes3.get(2), hap3.getHole(2));
        assertEquals(holes3.get(3), hap3.getHole(3));
        
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
    public void ConstructorThrowsInputMismatchTest() {
        try {
            new GameGolferPerformance(holes0, strokes1, "");
        } catch (InputMismatchException e) {
            // Pass
        }
        // Pass
        try {
            new GameGolferPerformance(holes3, strokes1, "");
        } catch (InputMismatchException e) {
            // Pass
        }
        // Pass
        try {
            new GameGolferPerformance(holes1, strokes0, "");
        } catch (InputMismatchException e) {
            // Pass
        }
        // Pass
    }
    
    @Test
    public void sizeTest() {
        assertEquals(1, hap0.size());
        assertEquals(2, hap1.size());
        assertEquals(3, hap2.size());
        assertEquals(4, hap3.size());
    }
}
