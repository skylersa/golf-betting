package model;

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
        Course c1 = new Course("Mini course golf", 1);
        Course c0 = new Course("Westfield Golf", 18);
        
        assertEquals(c0.getName(), "Mini course golf");
        assertEquals(c1.getName(), "Westfield Golf");
        assertEquals(c0.getNumHoles(), 1);
        assertEquals(c1.getNumHoles(), 18);
    
        
        for (int holeIndex = 0; holeIndex < 1; holeIndex++) {
            c0.getHole(holeIndex);
        }
    
        for (int holeIndex = 0; holeIndex < 18; holeIndex++) {
        
        }

    }
}
