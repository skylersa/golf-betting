package model.game;

import model.performance.GameAllPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Course course0, course1, course2;
    private List<Golfer> golfers0, golfers1, golfers2, golfers3;
    private Game game0, game1, game2, game3;
    
    @BeforeEach
    public void setup() {
        Golfer g0, g1, g2, g3, g4, g5, g6, g7, g8;
        g0 = new Golfer("bob");
        g1 = new Golfer("bill");
        g2 = new Golfer("jack");
        g3 = new Golfer("sammy");
        g4 = new Golfer("beatrice");
        g5 = new Golfer("loui");
        g6 = new Golfer("jackson");
        g7 = new Golfer("jackman");
        g8 = new Golfer("tommy");
        
        golfers0 = new ArrayList<>(Arrays.asList(g0));
        golfers1 = new ArrayList<>(Arrays.asList(g0, g1, g2, g3, g4, g5));
        golfers2 = new ArrayList<>(Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8));
        golfers3 = new ArrayList<>(Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8));
        
        course0 = new Course("westfield golf", 18);
        course1 = new Course("boomtrap", 9);
        course2 = new Course("borneio", 14);
        
        game0 = new Game(course0, golfers0);
        game1 = new Game(course1, golfers1);
        game2 = new Game(course2, golfers2);
        game3 = new Game(course1, golfers3);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(golfers0, game0.getGolfers());
        assertEquals(golfers1, game1.getGolfers());
        assertEquals(golfers2, game2.getGolfers());
        
        assertEquals(course0, game0.getCourse());
        assertEquals(course1, game1.getCourse());
        assertEquals(course2, game2.getCourse());
        
        assertFalse(game0.isComplete());
        assertFalse(game1.isComplete());
        assertFalse(game2.isComplete());
    }
    
    @Test
    public void playGameTest() {
        GameAllPerformance gap0 = game0.playGame();
        GameAllPerformance gap1 = game1.playGame();
        GameAllPerformance gap2 = game2.playGame();
        GameAllPerformance gap3 = game3.playGame();
        
        testGameAllPerformanceHoleAndGolferCorrectness(gap0);
        testGameAllPerformanceHoleAndGolferCorrectness(gap1);
        testGameAllPerformanceHoleAndGolferCorrectness(gap2);
        testGameAllPerformanceHoleAndGolferCorrectness(gap3);
    
        assertTrue(game0.isComplete());
        assertTrue(game1.isComplete());
        assertTrue(game2.isComplete());
        assertTrue(game3.isComplete());
    }
    
    private void testGameAllPerformanceHoleAndGolferCorrectness(GameAllPerformance gap) {
        Course course = gap.getGame().getCourse();
        List<Golfer> golfers = gap.getGame().getGolfers();
        
        for (int holeI = 0; holeI < course.getNumHoles(); holeI++) {
            for (int golferI = 0; golferI < golfers.size(); golferI++) {
                Golfer golferActual = gap.getHoleAllPerformances().get(holeI).
                        getHoleGolferPerformances().get(golferI).getGolfer();
                Golfer golferExpected = golfers.get(golferI);
                Hole holeActual = gap.getHoleAllPerformances().get(holeI).
                        getHoleGolferPerformances().get(golferI).getHole();
                Hole holeExpected = course.getHole(holeI);
                
                assertEquals(golferExpected, golferActual);
                assertEquals(holeExpected, holeActual);
                
                golferActual = gap.getGameGolferPerformances().get(golferI).getGolfer();
                holeActual = gap.getGameGolferPerformances().get(golferI).
                        getHoleGolferPerformances().get(holeI).getHole();
                
                assertEquals(golferExpected, golferActual);
                assertEquals(holeExpected, holeActual);
                
                assertTrue(golfers.get(golferI).getPerformanceHistory().
                        contains(gap.getGameGolferPerformances().get(golferI)));
            }
        }
    }
}
