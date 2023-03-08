package model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GolferTest {
    private Golfer g0, g1, g2, g3;
    
    @BeforeEach
    public void setup() {
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
    }
    
    @Test
    public void constructorTest() {
        assertEquals("g0", g0.getName());
        assertEquals("g1", g1.getName());
        assertEquals("g2", g2.getName());
        assertEquals("g3", g3.getName());
    }
    
    @Test
    public void toJsonTest() {
        assertEquals("g0", g0.toJson().getString("name"));
        assertEquals("g1", g1.toJson().getString("name"));
        assertEquals("g2", g2.toJson().getString("name"));
        assertEquals("g3", g3.toJson().getString("name"));
    }
}
