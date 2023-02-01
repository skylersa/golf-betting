package model.performance;

import model.game.Golfer;
import model.game.Course;
import model.game.Game;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameGolferPerformanceTest {
    private GameGolferPerformance g0g0p, g1g0p, g2g0p, g0g1p, g1g1p, g2g1p, g0g2p, g1g2p, g2g2p;
    private Game game0, game1, game2;
    private Golfer g0, g1, g2;
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        ArrayList<Golfer> golfers = new ArrayList<>(Arrays.asList(g0, g1, g2));
        
        game0 = new Game(new Course("", 3), golfers);
        game1 = new Game(new Course("", 4), golfers);
        game2 = new Game(new Course("", 5), golfers);
        
        g0g0p = new GameGolferPerformance(game0, g0);
        g1g0p = new GameGolferPerformance(game1, g0);
        g2g0p = new GameGolferPerformance(game2, g0);
        g0g1p = new GameGolferPerformance(game0, g1);
        g1g1p = new GameGolferPerformance(game1, g1);
        g2g1p = new GameGolferPerformance(game2, g1);
        g0g2p = new GameGolferPerformance(game0, g2);
        g1g2p = new GameGolferPerformance(game1, g2);
        g2g2p = new GameGolferPerformance(game2, g2);
    }
    
    @Test
    public void constructorTest() {
        assertEquals(game0, g0g0p.getGame());
        assertEquals(game1, g1g0p.getGame());
        assertEquals(game2, g2g0p.getGame());
        assertEquals(game0, g0g1p.getGame());
        assertEquals(game1, g1g1p.getGame());
        assertEquals(game2, g2g1p.getGame());
        assertEquals(game0, g0g2p.getGame());
        assertEquals(game1, g1g2p.getGame());
        assertEquals(game2, g2g2p.getGame());
    
        assertEquals(g0, g0g0p.getGolfer());
        assertEquals(g0, g1g0p.getGolfer());
        assertEquals(g0, g2g0p.getGolfer());
        assertEquals(g1, g0g1p.getGolfer());
        assertEquals(g1, g1g1p.getGolfer());
        assertEquals(g1, g2g1p.getGolfer());
        assertEquals(g2, g0g2p.getGolfer());
        assertEquals(g2, g1g2p.getGolfer());
        assertEquals(g2, g2g2p.getGolfer());
    }
    
    @Test
    public void addGolferPerformanceTest() {
        Hole h1, h2, h3;
        h1 = new Hole(1);
        h2 = new Hole(2);
        h3 = new Hole(3);
    
        HoleGolferPerformance hgp0, hgp1, hgp2;
        hgp0 = new HoleGolferPerformance(h1, g0, 3);
        hgp1 = new HoleGolferPerformance(h2, g1, 3);
        hgp2 = new HoleGolferPerformance(h3, g2, 3);
    
        g0g0p.addGolferPerformance(hgp0);
        g0g0p.addGolferPerformance(hgp1);
        g1g0p.addGolferPerformance(hgp2);
    
        assertEquals(hgp0, g0g0p.getHoleGolferPerformances().get(0));
        assertEquals(hgp1, g0g0p.getHoleGolferPerformances().get(1));
        assertEquals(hgp2, g1g0p.getHoleGolferPerformances().get(0));
    
    }
}
