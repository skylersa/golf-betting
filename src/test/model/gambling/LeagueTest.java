package model.gambling;

import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import model.game.Course;
import model.game.Golfer;
import model.game.Hole;
import model.performance.GameAllPerformance;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO: finish Json test + some exception throws
public class LeagueTest {
    private League l0, l1;
    private Course westC, eastC, nootC;
    private Golfer carloG, timmyG, bobbyG, jackyG;
    private List<Golfer> carloTimmyGolferList, bobbyJackyGolferList;
    
    @BeforeEach
    public void setup() {
        l0 = new League();
        l1 = new League();
        
        setupLeagueAndCourses();
        setupGolfers();
        setupPerformances();
    }
    
    private void setupPerformances() {
        carloTimmyGolferList = new ArrayList<>(Arrays.asList(carloG, timmyG));
        bobbyJackyGolferList = new ArrayList<>(Arrays.asList(bobbyG, jackyG));
        
        l1.addPerformance(new GameAllPerformance(
                Arrays.asList(westC.getHoles()),
                carloTimmyGolferList,
                "west"));
        l1.addPerformance(new GameAllPerformance(
                Arrays.asList(eastC.getHoles()),
                bobbyJackyGolferList,
                "east"));
    }
    
    private void setupLeagueAndCourses() {
        try {
            l1.addCourse("west", 6);
            l1.addCourse("east", 1);
            l1.addCourse("noot", 15);
        } catch (RepeatCourseException e) {
            throw new Error(e);
        }
        
        westC = l1.getCourse("west");
        eastC = l1.getCourse("east");
        nootC = l1.getCourse("noot");
    }
    
    private void setupGolfers() {
        try {
            l1.addGolfer("carlo");
            l1.addGolfer("timmy");
            l1.addGolfer("bobby");
            l1.addGolfer("jacky");
        } catch (RepeatGolferException e) {
            throw new Error(e);
        }
        
        carloG = l1.getGolfer("carlo");
        timmyG = l1.getGolfer("timmy");
        bobbyG = l1.getGolfer("bobby");
        jackyG = l1.getGolfer("jacky");
    }
    
    @Test
    public void constructorTest() {
        assertEquals(0, l0.getCourses().size());
        assertEquals(0, l0.getGolfers().size());
    }
    
    @Test
    public void addGolferTestNormalExecution() {
        assertEquals("carlo", l1.getGolfers().get(0).getName());
        assertEquals("timmy", l1.getGolfers().get(1).getName());
        assertEquals("bobby", l1.getGolfers().get(2).getName());
        assertEquals("jacky", l1.getGolfers().get(3).getName());
    }
    
    @Test
    public void addGolferTestThrowsRepeatGolfer() {
        try {
            l1.addGolfer("carlo");
            fail();
        } catch (RepeatGolferException e) {
            // Do nothing, pass
        }
        //Do nothing, pass
    }
    
    @Test
    public void addCourseTestNumNormalExecution() {
        assertEquals("west", l1.getCourses().get(0).getName());
        assertEquals("east", l1.getCourses().get(1).getName());
        assertEquals("noot", l1.getCourses().get(2).getName());
        
        assertEquals(6, l1.getCourses().get(0).getNumHoles());
        assertEquals(1, l1.getCourses().get(1).getNumHoles());
        assertEquals(15, l1.getCourses().get(2).getNumHoles());
    }
    
    @Test
    public void addCourseTestNumThrowsRepeatCourse() {
        try {
            l1.addCourse("west", 4);
            fail();
        } catch (RepeatCourseException e) {
            // Do nothing, pass
        }
        // Do nothing, pass
    }
    
    @Test
    public void addCourseTestListNormalExecution() {
        List<Hole> holes = new ArrayList<>(Arrays.asList(
                new Hole(4),
                new Hole(6),
                new Hole(7)));
        try {
            l1.addCourse("tommy's", holes);
            // Do nothing, pass
            
        } catch (RepeatCourseException e) {
            fail();
        }
        // Do nothing, pass
        
        assertEquals("tommy's", l1.getCourses().get(3).getName());
        assertEquals(holes, Arrays.asList(l1.getCourses().get(3).getHoles()));
        
        
    }
    
    @Test
    public void addCourseTestListThrowsRepeatCourse() {
        List<Hole> holes = new ArrayList<>(Arrays.asList(
                new Hole(4),
                new Hole(6),
                new Hole(7)));
        try {
            l1.addCourse("west", holes);
            fail();
        } catch (RepeatCourseException e) {
            // Do nothing, pass
        }
        // Do nothing, pass
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
        
        assertEquals(Arrays.asList(westC.getHoles()), l1.getGolferHistory("carlo").get(1).getHoles());
        assertEquals(Arrays.asList(westC.getHoles()), l1.getGolferHistory("timmy").get(1).getHoles());
        assertEquals(Arrays.asList(westC.getHoles()), l1.getGolferHistory("bobby").get(1).getHoles());
        assertEquals(Arrays.asList(westC.getHoles()), l1.getGolferHistory("jacky").get(1).getHoles());
        assertEquals(Arrays.asList(eastC.getHoles()), l1.getGolferHistory("carlo").get(2).getHoles());
        assertEquals(Arrays.asList(eastC.getHoles()), l1.getGolferHistory("bobby").get(2).getHoles());
        assertEquals(Arrays.asList(nootC.getHoles()), l1.getGolferHistory("carlo").get(3).getHoles());
        assertEquals(Arrays.asList(nootC.getHoles()), l1.getGolferHistory("timmy").get(2).getHoles());
        assertEquals(Arrays.asList(nootC.getHoles()), l1.getGolferHistory("bobby").get(3).getHoles());
        
        // TODO: test for storage in league
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
    
    @Test
    public void getSetGamblerTest() {
        Gambler g = new Gambler(100);
        l1.setGambler(g);
        assertEquals(g, l1.getGambler());
    }
    
    @Test
    public void toJsonTest() {
        JSONObject json = l1.toJson();
        JSONArray golfersJson = json.getJSONArray("golfers");
        
        assertEquals("carlo", golfersJson.getJSONObject(0).getString("name"));
        assertEquals("timmy", golfersJson.getJSONObject(1).getString("name"));
        assertEquals("bobby", golfersJson.getJSONObject(2).getString("name"));
        assertEquals("jacky", golfersJson.getJSONObject(3).getString("name"));
        
        JSONArray coursesJson = json.getJSONArray("courses");
        
        assertEquals("west", coursesJson.getJSONObject(0).getString("name"));
        assertEquals("east", coursesJson.getJSONObject(1).getString("name"));
        assertEquals("noot", coursesJson.getJSONObject(2).getString("name"));
        
        assertEquals(westC.getHole(0).getPar(),
                coursesJson.getJSONObject(0)
                        .getJSONArray("holes")
                        .getJSONObject(0)
                        .getInt("par"));
        
        assertEquals(eastC.getHole(0).getPar(),
                coursesJson.getJSONObject(1)
                        .getJSONArray("holes")
                        .getJSONObject(0)
                        .getInt("par"));
        
        assertEquals(nootC.getHole(0).getPar(),
                coursesJson.getJSONObject(2)
                        .getJSONArray("holes")
                        .getJSONObject(0)
                        .getInt("par"));
        
        // TODO: test performance retrieval
    }
    
}
