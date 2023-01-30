package model;

import model.game.Course;
import model.game.Game;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;
import model.performance.HoleGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Course course0, course1, course2;
    private Golfer p0, p1, p2, p3, p4, p5, p6, p7, p8;
    private ArrayList<Golfer> golfers0, golfers1, golfers2;
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
        
        golfers0 = new ArrayList<>(List.of(p0));
        golfers1 = new ArrayList<>(List.of(p0, p1, p2, p3, p4, p5));
        golfers2 = new ArrayList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        
        course0 = new Course("westfield golf", 18);
        course1 = new Course("boomtrap", 9);
        course2 = new Course("borneio", 14);
        
        g0 = new Game(course0, golfers0);
        g1 = new Game(course1, golfers1);
        g2 = new Game(course2, golfers2);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(g0.getGolfers(), golfers0);
        assertEquals(g1.getGolfers(), golfers1);
        assertEquals(g2.getGolfers(), golfers2);
        
        assertEquals(g0.getCourse(), course0);
        assertEquals(g1.getCourse(), course1);
        assertEquals(g2.getCourse(), course2);
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
        Hole[] hap2holesPredicted = new Hole[course2.getNumHoles()];
        
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
            hap2holesPredicted[i] = gap2.getHoleAllPerformances().get(i).getHole();
        }
        
        assertTrue(g0.isComplete());
        assertTrue(g1.isComplete());
        assertTrue(g2.isComplete());
    
        assertEquals(ggp0Golfers, golfers0);
        assertEquals(ggp1Golfers, golfers1);
        assertEquals(ggp2Golfers, golfers2);
    
        assertEquals(hap0HolesPredicted, course0.getHoles());
        assertEquals(hap1HolesPredicted, course0.getHoles());
        assertEquals(hap2holesPredicted, course0.getHoles());
    }
}
