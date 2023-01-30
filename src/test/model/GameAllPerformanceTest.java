package model;

import model.game.Course;
import model.game.Game;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameAllPerformanceTest {
    private GameAllPerformance gap0, gap1, gap2;
    private Game game0, game1, game2;
    private Golfer g0, g1, g2, g3, g4, g5;
    private GameGolferPerformance g0g0, g0g1;
    private GameGolferPerformance g1g2, g1g3, g1g4, g1g5;
    private GameGolferPerformance g2g3, g2g4;
    
    
    @BeforeEach
    public void setup() {
        ArrayList<Golfer> golfers0 = new ArrayList<>(List.of(g0, g1));
        ArrayList<Golfer> golfers1 = new ArrayList<>(List.of(g2, g3, g4, g5));
        ArrayList<Golfer> golfers2 = new ArrayList<>(List.of(g3, g4));
        
        game0 = new Game(new Course("c", 4), golfers0);
        game1 = new Game(new Course("b", 3), golfers1);
        game2 = new Game(new Course("a", 6), golfers2);
        
        gap0 = new GameAllPerformance(game0);
        gap1 = new GameAllPerformance(game1);
        gap2 = new GameAllPerformance(game2);
        
        g0g0 = new GameGolferPerformance(game0, g0);
        g0g1 = new GameGolferPerformance(game0, g1);
        g1g2 = new GameGolferPerformance(game1, g2);
        g1g3 = new GameGolferPerformance(game1, g3);
        g1g4 = new GameGolferPerformance(game1, g4);
        g1g5 = new GameGolferPerformance(game1, g5);
        g2g3 = new GameGolferPerformance(game2, g3);
        g2g4 = new GameGolferPerformance(game2, g4);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(gap0.getGame(), game0);
        assertEquals(gap1.getGame(), game1);
        assertEquals(gap2.getGame(), game2);
    }
    
    @Test
    public void addGameGolferPerformanceTest() {
        gap0.addGameGolferPerformance(g0g0);
        gap0.addGameGolferPerformance(g0g1);
        
        gap1.addGameGolferPerformance(g1g2);
        gap1.addGameGolferPerformance(g1g3);
        gap1.addGameGolferPerformance(g1g4);
        gap1.addGameGolferPerformance(g1g5);
        
        gap2.addGameGolferPerformance(g2g3);
        gap2.addGameGolferPerformance(g2g4);
        
        assertEquals(gap0.getGameGolferPerformances().get(0), g0g0);
        assertEquals(gap0.getGameGolferPerformances().get(1), g0g1);
        
        assertEquals(gap1.getGameGolferPerformances().get(0), g1g2);
        assertEquals(gap1.getGameGolferPerformances().get(1), g1g3);
        assertEquals(gap1.getGameGolferPerformances().get(2), g1g4);
        assertEquals(gap1.getGameGolferPerformances().get(3), g1g5);
        
        assertEquals(gap2.getGameGolferPerformances().get(0), g2g3);
        assertEquals(gap2.getGameGolferPerformances().get(1), g2g4);
    }
    
    
}
