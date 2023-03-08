package model.game;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {
    Course c0, c1;
    
    @BeforeEach
    public void setup() {
        c0 = new Course("Mini course golf", 1);
        c1 = new Course("Westfield Golf", 18);
        
    }
    
    @Test
    public void constructorTest() {
        assertEquals("Mini course golf", c0.getName());
        assertEquals("Westfield Golf", c1.getName());
        assertEquals(1, c0.getNumHoles());
        assertEquals(18, c1.getNumHoles());
        
        
        for (int holeIndex = 0; holeIndex < 1; holeIndex++) {
            int par = c0.getHole(holeIndex).getPar();
            assertTrue(Hole.MIN_PAR <= par && par <= Hole.MAX_PAR);
        }
        
        for (int holeIndex = 0; holeIndex < 18; holeIndex++) {
            int par = c1.getHole(holeIndex).getPar();
            assertTrue(Hole.MIN_PAR <= par && par <= Hole.MAX_PAR);
        }
    }
    
    @Test
    public void toJsonTest() {
        JSONObject c1Json = c1.toJson();
        
        List<Integer> c1ParList = Arrays.stream(c1.getHoles()).map(Hole::getPar).collect(Collectors.toList());
        
        
        assertEquals(c1.getName(), c1Json.getString("name"));
        
        JSONArray c1JsonParList = c1Json.getJSONArray("holes");
        List<Integer> c1JsonParListExtracted = new ArrayList<>();
        
        for (Object obj : c1JsonParList) {
            JSONObject json = (JSONObject) obj;
            c1JsonParListExtracted.add(json.getInt("par"));
        }
        
        assertEquals(c1ParList.toString(), c1JsonParListExtracted.toString());
    }
}
