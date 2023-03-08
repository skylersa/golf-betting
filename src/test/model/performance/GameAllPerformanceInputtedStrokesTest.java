package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameAllPerformanceInputtedStrokesTest {
    private GameAllPerformance gap1x1, gap2x2, gap3x4, gap4x3; // holesxgolfers
    private Golfer g0, g1, g2, g3;
    private Hole h0, h1, h2, h3;
    private List<Hole> holes1, holes2, holes3, holes4;
    private List<Golfer> golfers1, golfers2, golfers4, golfers3;
    
    @BeforeEach
    public void setup() {
        h0 = new Hole();
        h1 = new Hole();
        h2 = new Hole();
        h3 = new Hole();
        
        g0 = new Golfer("g0");
        g1 = new Golfer("g1");
        g2 = new Golfer("g2");
        g3 = new Golfer("g3");
        
        holes1 = new ArrayList<>(Arrays.asList(h3));
        holes2 = new ArrayList<>(Arrays.asList(h3, h2));
        holes3 = new ArrayList<>(Arrays.asList(h2, h3, h1));
        holes4 = new ArrayList<>(Arrays.asList(h3, h2, h1, h0));
        
        golfers1 = new ArrayList<>(Arrays.asList(g1));
        golfers2 = new ArrayList<>(Arrays.asList(g2, g3));
        golfers4 = new ArrayList<>(Arrays.asList(g0, g1, g2, g3));
        golfers3 = new ArrayList<>(Arrays.asList(g1, g2, g3));
        
        gap1x1 = new GameAllPerformance(holes1, golfers1, "1x1 course");
        gap2x2 = new GameAllPerformance(holes2, golfers2, "2x2 course");
        gap3x4 = new GameAllPerformance(holes3, golfers4, "3x4 course");
        gap4x3 = new GameAllPerformance(holes4, golfers3, "4x3 course");
        
        gap1x1.addHolePerformance(new HoleGolferPerformance(h3, g1, 0));
        
        gap2x2.addHolePerformance(new HoleGolferPerformance(h3, g2, 1));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h3, g3, 2));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h2, g2, 3));
        gap2x2.addHolePerformance(new HoleGolferPerformance(h2, g3, 4));
        
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g0, 5));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g1, 6));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g2, 7));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h2, g3, 8));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g0, 9));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g1, 10));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g2, 11));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h3, g3, 12));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g0, 13));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g1, 14));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g2, 15));
        gap3x4.addHolePerformance(new HoleGolferPerformance(h1, g3, 16));
        
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g1, 17));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g2, 18));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h3, g3, 19));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g1, 20));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g2, 21));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h2, g3, 22));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g1, 23));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g2, 24));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h1, g3, 25));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g1, 26));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g2, 27));
        gap4x3.addHolePerformance(new HoleGolferPerformance(h0, g3, 28));
    }
    
    @Test
    public void testHoleStrokes() {
        assertEquals(0, gap1x1.getHolePerformance(h3).getStrokes().get(0));
        
        assertEquals(1, gap2x2.getHolePerformance(h3).getStrokes().get(0));
        assertEquals(2, gap2x2.getHolePerformance(h3).getStrokes().get(1));
        assertEquals(3, gap2x2.getHolePerformance(h2).getStrokes().get(0));
        assertEquals(4, gap2x2.getHolePerformance(h2).getStrokes().get(1));
        
        assertEquals(5, gap3x4.getHolePerformance(h2).getStrokes().get(0));
        assertEquals(6, gap3x4.getHolePerformance(h2).getStrokes().get(1));
        assertEquals(7, gap3x4.getHolePerformance(h2).getStrokes().get(2));
        assertEquals(8, gap3x4.getHolePerformance(h2).getStrokes().get(3));
        assertEquals(9, gap3x4.getHolePerformance(h3).getStrokes().get(0));
        assertEquals(10, gap3x4.getHolePerformance(h3).getStrokes().get(1));
        assertEquals(11, gap3x4.getHolePerformance(h3).getStrokes().get(2));
        assertEquals(12, gap3x4.getHolePerformance(h3).getStrokes().get(3));
        assertEquals(13, gap3x4.getHolePerformance(h1).getStrokes().get(0));
        assertEquals(14, gap3x4.getHolePerformance(h1).getStrokes().get(1));
        assertEquals(15, gap3x4.getHolePerformance(h1).getStrokes().get(2));
        assertEquals(16, gap3x4.getHolePerformance(h1).getStrokes().get(3));
        
        assertEquals(17, gap4x3.getHolePerformance(h3).getStrokes().get(0));
        assertEquals(18, gap4x3.getHolePerformance(h3).getStrokes().get(1));
        assertEquals(19, gap4x3.getHolePerformance(h3).getStrokes().get(2));
        assertEquals(20, gap4x3.getHolePerformance(h2).getStrokes().get(0));
        assertEquals(21, gap4x3.getHolePerformance(h2).getStrokes().get(1));
        assertEquals(22, gap4x3.getHolePerformance(h2).getStrokes().get(2));
        assertEquals(23, gap4x3.getHolePerformance(h1).getStrokes().get(0));
        assertEquals(24, gap4x3.getHolePerformance(h1).getStrokes().get(1));
        assertEquals(25, gap4x3.getHolePerformance(h1).getStrokes().get(2));
        assertEquals(26, gap4x3.getHolePerformance(h0).getStrokes().get(0));
        assertEquals(27, gap4x3.getHolePerformance(h0).getStrokes().get(1));
        assertEquals(28, gap4x3.getHolePerformance(h0).getStrokes().get(2));
    }
    
    @Test
    public void testGolferStrokesByIndexGet() {
        assertEquals(0, gap1x1.getGolferPerformance(0).getStrokes().get(0));
        
        assertEquals(1, gap2x2.getGolferPerformance(0).getStrokes().get(0));
        assertEquals(2, gap2x2.getGolferPerformance(1).getStrokes().get(0));
        assertEquals(3, gap2x2.getGolferPerformance(0).getStrokes().get(1));
        assertEquals(4, gap2x2.getGolferPerformance(1).getStrokes().get(1));
        
        assertEquals(5, gap3x4.getGolferPerformance(0).getStrokes().get(0));
        assertEquals(6, gap3x4.getGolferPerformance(1).getStrokes().get(0));
        assertEquals(7, gap3x4.getGolferPerformance(2).getStrokes().get(0));
        assertEquals(8, gap3x4.getGolferPerformance(3).getStrokes().get(0));
        assertEquals(9, gap3x4.getGolferPerformance(0).getStrokes().get(1));
        assertEquals(10, gap3x4.getGolferPerformance(1).getStrokes().get(1));
        assertEquals(11, gap3x4.getGolferPerformance(2).getStrokes().get(1));
        assertEquals(12, gap3x4.getGolferPerformance(3).getStrokes().get(1));
        assertEquals(13, gap3x4.getGolferPerformance(0).getStrokes().get(2));
        assertEquals(14, gap3x4.getGolferPerformance(1).getStrokes().get(2));
        assertEquals(15, gap3x4.getGolferPerformance(2).getStrokes().get(2));
        assertEquals(16, gap3x4.getGolferPerformance(3).getStrokes().get(2));
        
        assertEquals(17, gap4x3.getGolferPerformance(0).getStrokes().get(0));
        assertEquals(18, gap4x3.getGolferPerformance(1).getStrokes().get(0));
        assertEquals(19, gap4x3.getGolferPerformance(2).getStrokes().get(0));
        assertEquals(20, gap4x3.getGolferPerformance(0).getStrokes().get(1));
        assertEquals(21, gap4x3.getGolferPerformance(1).getStrokes().get(1));
        assertEquals(22, gap4x3.getGolferPerformance(2).getStrokes().get(1));
        assertEquals(23, gap4x3.getGolferPerformance(0).getStrokes().get(2));
        assertEquals(24, gap4x3.getGolferPerformance(1).getStrokes().get(2));
        assertEquals(25, gap4x3.getGolferPerformance(2).getStrokes().get(2));
        assertEquals(26, gap4x3.getGolferPerformance(0).getStrokes().get(3));
        assertEquals(27, gap4x3.getGolferPerformance(1).getStrokes().get(3));
        assertEquals(28, gap4x3.getGolferPerformance(2).getStrokes().get(3));
        
    }
    
    @Test
    public void testGolferStrokesByGolferGet() {
        assertEquals(0, gap1x1.getGolferPerformance(g1).getStrokes().get(0));
        
        assertEquals(1, gap2x2.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals(2, gap2x2.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals(3, gap2x2.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals(4, gap2x2.getGolferPerformance(g3).getStrokes().get(1));
        
        assertEquals(5, gap3x4.getGolferPerformance(g0).getStrokes().get(0));
        assertEquals(6, gap3x4.getGolferPerformance(g1).getStrokes().get(0));
        assertEquals(7, gap3x4.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals(8, gap3x4.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals(9, gap3x4.getGolferPerformance(g0).getStrokes().get(1));
        assertEquals(10, gap3x4.getGolferPerformance(g1).getStrokes().get(1));
        assertEquals(11, gap3x4.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals(12, gap3x4.getGolferPerformance(g3).getStrokes().get(1));
        assertEquals(13, gap3x4.getGolferPerformance(g0).getStrokes().get(2));
        assertEquals(14, gap3x4.getGolferPerformance(g1).getStrokes().get(2));
        assertEquals(15, gap3x4.getGolferPerformance(g2).getStrokes().get(2));
        assertEquals(16, gap3x4.getGolferPerformance(g3).getStrokes().get(2));
        
        assertEquals(17, gap4x3.getGolferPerformance(g1).getStrokes().get(0));
        assertEquals(18, gap4x3.getGolferPerformance(g2).getStrokes().get(0));
        assertEquals(19, gap4x3.getGolferPerformance(g3).getStrokes().get(0));
        assertEquals(20, gap4x3.getGolferPerformance(g1).getStrokes().get(1));
        assertEquals(21, gap4x3.getGolferPerformance(g2).getStrokes().get(1));
        assertEquals(22, gap4x3.getGolferPerformance(g3).getStrokes().get(1));
        assertEquals(23, gap4x3.getGolferPerformance(g1).getStrokes().get(2));
        assertEquals(24, gap4x3.getGolferPerformance(g2).getStrokes().get(2));
        assertEquals(25, gap4x3.getGolferPerformance(g3).getStrokes().get(2));
        assertEquals(26, gap4x3.getGolferPerformance(g1).getStrokes().get(3));
        assertEquals(27, gap4x3.getGolferPerformance(g2).getStrokes().get(3));
        assertEquals(28, gap4x3.getGolferPerformance(g3).getStrokes().get(3));
    }
    
    @Test
    public void testGetBestPerformingGolfer() {
        assertEquals(g1, gap1x1.getBestPerformingGolfer());
        assertEquals(g2, gap2x2.getBestPerformingGolfer());
        assertEquals(g0, gap3x4.getBestPerformingGolfer());
        assertEquals(g1, gap4x3.getBestPerformingGolfer());
    }
    
    @Test
    public void testGetStrokes() {
        Integer[][] gap1x1Strokes = {{0}};
        Integer[][] gap2x2Strokes = {{1, 2}, {3, 4}};
        Integer[][] gap3x4Strokes = {{5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Integer[][] gap4x3Strokes = {{17, 18, 19}, {20, 21, 22}, {23, 24, 25}, {26, 27, 28}};
        
        assertEquals(Arrays.deepToString(gap1x1Strokes), Arrays.deepToString(gap1x1.getStrokes()));
        assertEquals(Arrays.deepToString(gap2x2Strokes), Arrays.deepToString(gap2x2.getStrokes()));
        assertEquals(Arrays.deepToString(gap3x4Strokes), Arrays.deepToString(gap3x4.getStrokes()));
        assertEquals(Arrays.deepToString(gap4x3Strokes), Arrays.deepToString(gap4x3.getStrokes()));
    }
    
    @Test
    public void toJsonTest() {
        JSONObject json = gap3x4.toJson();
        JSONArray golfersJson = json.getJSONArray("golfers");
        JSONArray holesJson = json.getJSONArray("holes");
        
        for (int i = 0; i < gap3x4.getGolfers().size(); i++) {
            assertEquals(golfers4.get(i).getName(), golfersJson.getJSONObject(i).getString("name"));
        }
        for (int i = 0; i < gap3x4.getHoles().size(); i++) {
            assertEquals(gap3x4.getHoles().get(i).getPar(), holesJson.getJSONObject(i).getInt("par"));
        }
        for (int i = 0; i < gap3x4.getHoles().size(); i++) {
            for (int j = 0; j < gap3x4.getGolfers().size(); j++) {
                 assertEquals(gap3x4.getStrokes()[i][j],
                         json.getJSONArray("strokes")
                                 .getJSONArray(i)
                                 .getInt(j));
                
            }
        }
        
        assertEquals("3x4 course", json.getString("courseName"));
        
        
    }
}
