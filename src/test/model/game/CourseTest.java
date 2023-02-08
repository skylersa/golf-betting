package model.game;

import model.game.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    @BeforeEach
    public void setup() {
    
    }
    
    @Test
    public void constructorTest() {
        Course c0 = new Course("Mini course golf", 1);
        Course c1 = new Course("Westfield Golf", 18);
        
        assertEquals("Mini course golf", c0.getName());
        assertEquals("Westfield Golf", c1.getName());
        assertEquals(1, c0.getNumHoles());
        assertEquals(18, c1.getNumHoles());
    
        
        for (int holeIndex = 0; holeIndex < 1; holeIndex++) {
            int par = c0.getHole(holeIndex).getPar();
            assertTrue(Hole.MIN_PAR <= par && par <= Hole.MAX_PAR);
        }
    
        for (int holeIndex = 0; holeIndex < 18; holeIndex++) {
            int par = c1.getHole(holeIndex).getPar();
            assertTrue(Hole.MIN_PAR <= par && par <= Hole.MAX_PAR);
        }
    }
}
