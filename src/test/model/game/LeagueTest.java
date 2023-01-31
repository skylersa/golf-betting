package model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueTest {
    private League l0, l1;
    
    @BeforeEach
    public void setup() {
        l0 = new League();
        l1 = new League();
    }
    
    @Test
    public void constructorTest() {
        assertEquals(0, l0.getCourses().size());
        assertEquals(0, l1.getCourses().size());
        assertEquals(0, l0.getGolfers().size());
        assertEquals(0, l1.getGolfers().size());
    }
    
    @Test
    public void addGolferTest() {
        l0.addGolfer("carlo");
        l0.addGolfer("timmy");
        l0.addGolfer("bobby");
        l0.addGolfer("jackson");
        
        assertEquals("carlo", l0.getGolfers().get(0).getName());
        assertEquals("timmy", l0.getGolfers().get(1).getName());
        assertEquals("bobby", l0.getGolfers().get(2).getName());
        assertEquals("jackson", l0.getGolfers().get(3).getName());
    }
    
    @Test
    public void addCourseTest() {
        l1.addCourse("west", 6);
        l1.addCourse("east", 1);
        l1.addCourse("north", 15);
        
        assertEquals("west", l1.getCourses().get(0).getName());
        assertEquals("east", l1.getCourses().get(1).getName());
        assertEquals("north", l1.getCourses().get(2).getName());
        
        assertEquals(6, l1.getCourses().get(0).getNumHoles());
        assertEquals(1, l1.getCourses().get(1).getNumHoles());
        assertEquals(15, l1.getCourses().get(2).getNumHoles());
    }
    
    @Test
    public void makeGameL1Test() {
        l1.addCourse("west", 6);
        l1.addCourse("east", 1);
        l1.addCourse("north", 15);
        
        l1.addGolfer("carlo");
        l1.addGolfer("timmy");
        l1.addGolfer("bobby");
        l1.addGolfer("jackson");
        
        Game game0 = l1.makeGame("west", new ArrayList<>(List.of("carlo", "timmy")));
        Game game1 = l1.makeGame("west", new ArrayList<>(List.of("bobby", "jackson")));
        Game game2 = l1.makeGame("east", new ArrayList<>(List.of("carlo", "bobby")));
        Game game3 = l1.makeGame("north", new ArrayList<>(List.of("carlo", "timmy", "bobby")));
        
        assertEquals("west", game0.getCourse().getName());
        assertEquals("west", game1.getCourse().getName());
        assertEquals("east", game2.getCourse().getName());
        assertEquals("north", game3.getCourse().getName());
        
        assertEquals("carlo", game0.getGolfers().get(0).getName());
        assertEquals("timmy", game0.getGolfers().get(1).getName());
        assertEquals("bobby", game1.getGolfers().get(0).getName());
        assertEquals("jackson", game1.getGolfers().get(1).getName());
        assertEquals("carlo", game2.getGolfers().get(0).getName());
        assertEquals("bobby", game2.getGolfers().get(1).getName());
        assertEquals("carlo", game3.getGolfers().get(0).getName());
        assertEquals("timmy", game3.getGolfers().get(1).getName());
        assertEquals("bobby", game3.getGolfers().get(2).getName());
        
    }
}
