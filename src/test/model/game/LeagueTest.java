package model.game;

import model.gambling.League;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.exceptions.RepeatGolferException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueTest {
    private League l0, l1;
    
    @BeforeEach
    public void setup() {
        l0 = new League();
        l1 = new League();
    
        l1.addCourse("west", 6);
        l1.addCourse("east", 1);
        l1.addCourse("north", 15);
        
        try {
            l1.addGolfer("carlo");
            l1.addGolfer("timmy");
            l1.addGolfer("bobby");
            l1.addGolfer("jackson");
        } catch (RepeatGolferException e) {
            throw new Error(e);
        }
    
    }
    
    @Test
    public void constructorTest() {
        assertEquals(0, l0.getCourses().size());
        assertEquals(0, l0.getGolfers().size());
    }
    
    @Test
    public void addGolferTest() {
        assertEquals("carlo", l1.getGolfers().get(0).getName());
        assertEquals("timmy", l1.getGolfers().get(1).getName());
        assertEquals("bobby", l1.getGolfers().get(2).getName());
        assertEquals("jackson", l1.getGolfers().get(3).getName());
    }
    
    @Test
    public void addCourseTest() {
        assertEquals("west", l1.getCourses().get(0).getName());
        assertEquals("east", l1.getCourses().get(1).getName());
        assertEquals("north", l1.getCourses().get(2).getName());
        
        assertEquals(6, l1.getCourses().get(0).getNumHoles());
        assertEquals(1, l1.getCourses().get(1).getNumHoles());
        assertEquals(15, l1.getCourses().get(2).getNumHoles());
    }
    
    @Test
    public void makeGameL1Test() {
        
        Game game0 = l1.makeGame("west", new ArrayList<>(Arrays.asList("carlo", "timmy")));
        Game game1 = l1.makeGame("west", new ArrayList<>(Arrays.asList("bobby", "jackson")));
        Game game2 = l1.makeGame("east", new ArrayList<>(Arrays.asList("carlo", "bobby")));
        Game game3 = l1.makeGame("north", new ArrayList<>(Arrays.asList("carlo", "timmy", "bobby")));
        
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
    
    @Test
    public void getCourseTest() {
        assertEquals(l1.getCourses().get(0), l1.getCourse("west"));
        assertEquals(l1.getCourses().get(1), l1.getCourse("east"));
        assertEquals(l1.getCourses().get(2), l1.getCourse("north"));
    }
    
    @Test
    public void getGolferTest() {
        assertEquals(l1.getGolfers().get(0), l1.getGolfer("carlo"));
        assertEquals(l1.getGolfers().get(1), l1.getGolfer("timmy"));
        assertEquals(l1.getGolfers().get(2), l1.getGolfer("bobby"));
        assertEquals(l1.getGolfers().get(3), l1.getGolfer("jackson"));
    }
    
    @Test
    public void getGolfersTest() {
        List<String> expectedGolfers = new ArrayList<>();
        expectedGolfers.add("carlo");
        expectedGolfers.add("timmy");
        expectedGolfers.add("bobby");
        expectedGolfers.add("jackson");
        
        assertEquals(expectedGolfers, l1.getGolferNames());
    }
    
    @Test
    public void getGolferNamesTest() {
        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("west");
        expectedCourses.add("east");
        expectedCourses.add("north");
        
        assertEquals(expectedCourses, l1.getCourseNames());
    }
}
