package model.game;

import model.performance.GameGolferPerformance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GolferTest {
    private Golfer g0, g1, g2, g3;
    private Game game0, game1;
    private GameGolferPerformance g0g0p, g0g1p, g1g0p, g1g1p, g2g0p, g2g1p, g3g0p, g3g1p;
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
        
        List<Golfer> golfers = new ArrayList<>(Arrays.asList(g0, g1, g2, g3)) ;
        game0 = new Game(new Course("", 8), golfers);
        game1 = new Game(new Course("", 7), golfers);

        
        g0g0p = new GameGolferPerformance(game0, g0);
        g0g1p = new GameGolferPerformance(game1, g0);
        g1g0p = new GameGolferPerformance(game0, g1);
        g1g1p = new GameGolferPerformance(game1, g1);
        g2g0p = new GameGolferPerformance(game0, g2);
        g2g1p = new GameGolferPerformance(game1, g2);
        g3g0p = new GameGolferPerformance(game0, g3);
        g3g1p = new GameGolferPerformance(game1, g3);
    }
    
    @Test
    public void constructorTest() {
        assertEquals("g0", g0.getName());
        assertEquals("g1", g1.getName());
        assertEquals("g2", g2.getName());
        assertEquals("g3", g3.getName());
    }
    
    @Test
    public void addGamePerformanceTest() {
        g0.addGamePerformance(g0g0p);
        g0.addGamePerformance(g0g1p);
        g1.addGamePerformance(g1g0p);
        g1.addGamePerformance(g1g1p);
        g2.addGamePerformance(g2g0p);
        g2.addGamePerformance(g2g1p);
        g3.addGamePerformance(g3g0p);
        g3.addGamePerformance(g3g1p);
    
        assertEquals(g0g0p, g0.getPerformanceHistory().get(0));
        assertEquals(g0g1p, g0.getPerformanceHistory().get(1));
        assertEquals(g1g0p, g1.getPerformanceHistory().get(0));
        assertEquals(g1g1p, g1.getPerformanceHistory().get(1));
        assertEquals(g2g0p, g2.getPerformanceHistory().get(0));
        assertEquals(g2g1p, g2.getPerformanceHistory().get(1));
        assertEquals(g3g0p, g3.getPerformanceHistory().get(0));
        assertEquals(g3g1p, g3.getPerformanceHistory().get(1));
    }
}
