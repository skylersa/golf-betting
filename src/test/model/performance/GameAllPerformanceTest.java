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

public class GameAllPerformanceTest {
    private GameAllPerformance gap0, gap1, gap2;
    private Game game0, game1, game2;
    private Golfer g0, g1, g2, g3, g4, g5;
    private GameGolferPerformance g0g0, g0g1;
    private GameGolferPerformance g1g2, g1g3, g1g4, g1g5;
    private GameGolferPerformance g2g3, g2g4;
    
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
        g4 = new Golfer("g4");
        g5 = new Golfer("g5");
        
        ArrayList<Golfer> golfers0 = new ArrayList<>(Arrays.asList(g0, g1));
        ArrayList<Golfer> golfers1 = new ArrayList<>(Arrays.asList(g2, g3, g4, g5));
        ArrayList<Golfer> golfers2 = new ArrayList<>(Arrays.asList(g3, g4));
        
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
        assertEquals(game0, gap0.getGame());
        assertEquals(game1, gap1.getGame());
        assertEquals(game2, gap2.getGame());
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
        
        assertEquals(g0g0, gap0.getGameGolferPerformances().get(0));
        assertEquals(g0g1, gap0.getGameGolferPerformances().get(1));
        
        assertEquals(g1g2, gap1.getGameGolferPerformances().get(0));
        assertEquals(g1g3, gap1.getGameGolferPerformances().get(1));
        assertEquals(g1g4, gap1.getGameGolferPerformances().get(2));
        assertEquals(g1g5, gap1.getGameGolferPerformances().get(3));
        
        assertEquals(g2g3, gap2.getGameGolferPerformances().get(0));
        assertEquals(g2g4, gap2.getGameGolferPerformances().get(1));
    }
    
    @Test
    public void addHoleAllPerformance() {
        Hole h0, h1, h2;
        h0 = new Hole();
        h1 = new Hole();
        h2 = new Hole();
        
        HoleAllPerformance hap0, hap1, hap2;
        hap0 = new HoleAllPerformance(h0);
        hap1 = new HoleAllPerformance(h1);
        hap2 = new HoleAllPerformance(h2);
    
        gap0.addHoleAllPerformance(hap0);
        gap0.addHoleAllPerformance(hap1);
        gap0.addHoleAllPerformance(hap2);
        gap1.addHoleAllPerformance(hap2);
        gap2.addHoleAllPerformance(hap2);
        gap2.addHoleAllPerformance(hap1);
    
        assertEquals(hap0, gap0.getHoleAllPerformances().get(0));
        assertEquals(hap1, gap0.getHoleAllPerformances().get(1));
        assertEquals(hap2, gap0.getHoleAllPerformances().get(2));
        assertEquals(hap2, gap1.getHoleAllPerformances().get(0));
        assertEquals(hap2, gap2.getHoleAllPerformances().get(0));
        assertEquals(hap1, gap2.getHoleAllPerformances().get(1));
    
    }
    
    @Test
    public void addHoleAllPerformances() {
        Hole h0, h1, h2;
        h0 = new Hole();
        h1 = new Hole();
        h2 = new Hole();
        
        HoleAllPerformance hap0, hap1, hap2;
        hap0 = new HoleAllPerformance(h0);
        hap1 = new HoleAllPerformance(h1);
        hap2 = new HoleAllPerformance(h2);
        List<HoleAllPerformance> haps0 = new ArrayList<>();
        List<HoleAllPerformance> haps1 = new ArrayList<>();
        List<HoleAllPerformance> haps2 = new ArrayList<>();
        
        haps0.add(hap0);
        haps0.add(hap1);
        haps0.add(hap2);
        haps1.add(hap2);
        haps2.add(hap2);
        haps2.add(hap1);
    
        gap0.addHoleAllPerformances(haps0);
        gap1.addHoleAllPerformances(haps1);
        gap2.addHoleAllPerformances(haps2);
        
        assertEquals(hap0, gap0.getHoleAllPerformances().get(0));
        assertEquals(hap1, gap0.getHoleAllPerformances().get(1));
        assertEquals(hap2, gap0.getHoleAllPerformances().get(2));
        assertEquals(hap2, gap1.getHoleAllPerformances().get(0));
        assertEquals(hap2, gap2.getHoleAllPerformances().get(0));
        assertEquals(hap1, gap2.getHoleAllPerformances().get(1));
        
    }
}
