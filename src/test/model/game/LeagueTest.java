package model.game;

import exceptions.RepeatGolferException;
import model.gambling.League;
import model.performance.GameAllPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueTest {
    private League l0, l1;
    
    @BeforeEach
    public void setup() {
        l0 = new League();
        l1 = new League();
    
        l1.addCourse("west", 6);
        l1.addCourse("east", 1);
        l1.addCourse("noot", 15);
        
        try {
            l1.addGolfer("carlo");
            l1.addGolfer("timmy");
            l1.addGolfer("bobby");
            l1.addGolfer("jacky");
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
        assertEquals("jacky", l1.getGolfers().get(3).getName());
    }
    
    @Test
    public void addCourseTest() {
        assertEquals("west", l1.getCourses().get(0).getName());
        assertEquals("east", l1.getCourses().get(1).getName());
        assertEquals("noot", l1.getCourses().get(2).getName());
        
        assertEquals(6, l1.getCourses().get(0).getNumHoles());
        assertEquals(1, l1.getCourses().get(1).getNumHoles());
        assertEquals(15, l1.getCourses().get(2).getNumHoles());
    }
    
    @Test
    public void playGameTest() {
        GameAllPerformance gap0 = l1.playGame("west", new ArrayList<>(Arrays.asList("carlo", "timmy")));
        GameAllPerformance gap1 = l1.playGame("west", new ArrayList<>(Arrays.asList("bobby", "jacky")));
        GameAllPerformance gap2 = l1.playGame("east", new ArrayList<>(Arrays.asList("carlo", "bobby")));
        GameAllPerformance gap3 = l1.playGame("noot", new ArrayList<>(Arrays.asList("carlo", "timmy", "bobby")));
        
        assertEquals("carlo", gap0.getGolfers().get(0).getName());
        assertEquals("timmy", gap0.getGolfers().get(1).getName());
        assertEquals("bobby", gap1.getGolfers().get(0).getName());
        assertEquals("jacky", gap1.getGolfers().get(1).getName());
        assertEquals("carlo", gap2.getGolfers().get(0).getName());
        assertEquals("bobby", gap2.getGolfers().get(1).getName());
        assertEquals("carlo", gap3.getGolfers().get(0).getName());
        assertEquals("timmy", gap3.getGolfers().get(1).getName());
        assertEquals("bobby", gap3.getGolfers().get(2).getName());
    }
    @Test
    public void getGolferPerformanceHistoryTest() {
        GameAllPerformance gap0 = l1.playGame("west", new ArrayList<>(Arrays.asList("carlo", "timmy")));
        GameAllPerformance gap1 = l1.playGame("west", new ArrayList<>(Arrays.asList("bobby", "jacky")));
        GameAllPerformance gap2 = l1.playGame("east", new ArrayList<>(Arrays.asList("carlo", "bobby")));
        GameAllPerformance gap3 = l1.playGame("noot", new ArrayList<>(Arrays.asList("carlo", "timmy", "bobby")));
        
        assertEquals(Arrays.asList(l1.getCourse("west").getHoles()), l1.getGolferHistory("carlo").get(0).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("west").getHoles()), l1.getGolferHistory("timmy").get(0).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("west").getHoles()), l1.getGolferHistory("bobby").get(0).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("west").getHoles()), l1.getGolferHistory("jacky").get(0).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("east").getHoles()), l1.getGolferHistory("carlo").get(1).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("east").getHoles()), l1.getGolferHistory("bobby").get(1).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("noot").getHoles()), l1.getGolferHistory("carlo").get(2).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("noot").getHoles()), l1.getGolferHistory("timmy").get(1).getHoles());
        assertEquals(Arrays.asList(l1.getCourse("noot").getHoles()), l1.getGolferHistory("bobby").get(2).getHoles());
    }
    
    @Test
    public void getGolferTest() {
        assertEquals(l1.getGolfers().get(0), l1.getGolfer("carlo"));
        assertEquals(l1.getGolfers().get(1), l1.getGolfer("timmy"));
        assertEquals(l1.getGolfers().get(2), l1.getGolfer("bobby"));
        assertEquals(l1.getGolfers().get(3), l1.getGolfer("jacky"));
    }
    
    @Test
    public void getCourseTest() {
        assertEquals(l1.getCourses().get(0), l1.getCourse("west"));
        assertEquals(l1.getCourses().get(1), l1.getCourse("east"));
        assertEquals(l1.getCourses().get(2), l1.getCourse("noot"));
    }
    
    @Test
    public void getCourseNamesTest() {
        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("west");
        expectedCourses.add("east");
        expectedCourses.add("noot");
        
        assertEquals(expectedCourses, l1.getCourseNames());
    }
    
    @Test
    public void getGolferNamesTest() {
        List<String> expectedGolfers = new ArrayList<>();
        expectedGolfers.add("carlo");
        expectedGolfers.add("timmy");
        expectedGolfers.add("bobby");
        expectedGolfers.add("jacky");
        
        assertEquals(expectedGolfers, l1.getGolferNames());
    }
    
    
}
