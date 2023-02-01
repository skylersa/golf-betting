package model.game;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Course course0, course1, course2;
    private Golfer p0, p1, p2, p3, p4, p5, p6, p7, p8;
    private List<Golfer> golfers0, golfers1, golfers2;
    private Game g0, g1, g2;
    
    @BeforeEach
    public void setup() {
        p0 = new Golfer("bob");
        p1 = new Golfer("bill");
        p2 = new Golfer("jack");
        p3 = new Golfer("sammy");
        p4 = new Golfer("beatrice");
        p5 = new Golfer("loui");
        p6 = new Golfer("jackson");
        p7 = new Golfer("jackman");
        p8 = new Golfer("tommy");
        
        golfers0 = new ArrayList<>(Arrays.asList(p0));
        golfers1 = new ArrayList<>(Arrays.asList(p0, p1, p2, p3, p4, p5));
        golfers2 = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
        
        course0 = new Course("westfield golf", 18);
        course1 = new Course("boomtrap", 9);
        course2 = new Course("borneio", 14);
        
        g0 = new Game(course0, (ArrayList<Golfer>) golfers0);
        g1 = new Game(course1, (ArrayList<Golfer>) golfers1);
        g2 = new Game(course2, (ArrayList<Golfer>) golfers2);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(golfers0, g0.getGolfers());
        assertEquals(golfers1, g1.getGolfers());
        assertEquals(golfers2, g2.getGolfers());
        
        assertEquals(course0, g0.getCourse());
        assertEquals(course1, g1.getCourse());
        assertEquals(course2, g2.getCourse());
    }
    
    //TODO account for HoleAllPerformance coverage
    @Test
    public void playGameTest() {
        GameAllPerformance gap0 = g0.playGame();
        GameAllPerformance gap1 = g1.playGame();
        GameAllPerformance gap2 = g2.playGame();
        
        ArrayList<Golfer> ggp0Golfers = new ArrayList<>();
        ArrayList<Golfer> ggp1Golfers = new ArrayList<>();
        ArrayList<Golfer> ggp2Golfers = new ArrayList<>();
     
        Hole[] hap0HolesPredicted = new Hole[course0.getNumHoles()];
        Hole[] hap1HolesPredicted = new Hole[course1.getNumHoles()];
        Hole[] hap2HolesPredicted = new Hole[course2.getNumHoles()];
        
        for (GameGolferPerformance performance : gap0.getGameGolferPerformances()) {
            ggp0Golfers.add(performance.getGolfer());
        }
        
        for (GameGolferPerformance performance : gap1.getGameGolferPerformances()) {
            ggp1Golfers.add(performance.getGolfer());
        }
        
        for (GameGolferPerformance performance : gap2.getGameGolferPerformances()) {
            ggp2Golfers.add(performance.getGolfer());
        }
        
        for (int i = 0; i < gap0.getHoleAllPerformances().size(); i++) {
            hap0HolesPredicted[i] = gap0.getHoleAllPerformances().get(i).getHole();
        }
    
        for (int i = 0; i < gap1.getHoleAllPerformances().size(); i++) {
            hap1HolesPredicted[i] = gap1.getHoleAllPerformances().get(i).getHole();
        }
    
        for (int i = 0; i < gap2.getHoleAllPerformances().size(); i++) {
            hap2HolesPredicted[i] = gap2.getHoleAllPerformances().get(i).getHole();
        }
        
        assertTrue(g0.isComplete());
        assertTrue(g1.isComplete());
        assertTrue(g2.isComplete());
    
        assertEquals(golfers0, ggp0Golfers);
        assertEquals(golfers1, ggp1Golfers);
        assertEquals(golfers2, ggp2Golfers);
    
    
        for (int i = 0; i < course0.getHoles().length; i++) {
            assertEquals(course0.getHole(i), hap0HolesPredicted[i]);
        }
        for (int i = 0; i < course1.getHoles().length; i++) {
            assertEquals(course1.getHole(i), hap1HolesPredicted[i]);
        }
        for (int i = 0; i < course2.getHoles().length; i++) {
            assertEquals(course2.getHole(i), hap2HolesPredicted[i]);
        }
    }
}
