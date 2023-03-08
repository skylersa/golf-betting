package model.persistence;

import exceptions.JsonParseError;
import model.gambling.League;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League league = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Pass
        }
        // Pass
    }
    
    @Test
    void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json");
        try {
            League league = reader.read();
            
            assertEquals(0, league.getGolfers().size());
            assertEquals(0, league.getPerformances().size());
            assertEquals(0, league.getCourses().size());
            assertEquals(5000, league.getGambler().getBalance());
            // Pass
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        // Pass
    }
    
    @Test
    void testReaderExtraGolferInPerfLeague() {
        JsonReader reader = new JsonReader("./data/testReaderExtraGolferInPerf.json");
        try {
            League league = reader.read();
            fail("read file when it shouldn't be able to");
        } catch (JsonParseError e) {
            // Pass
        } catch (IOException e) {
            fail("couldn't read file");
        }
        // Pass
    }
    
    @Test
    void testReaderRepeatGolferLeague() {
        JsonReader reader = new JsonReader("./data/testReaderRepeatGolfer.json");
        try {
            League league = reader.read();
            fail("read file when it shouldn't be able to");
        } catch (JsonParseError e) {
            // Pass
        } catch (IOException e) {
            fail("couldn't read file");
        }
        // Pass
    }
    
    @Test
    void testReaderRepeatCourseLeague() {
        JsonReader reader = new JsonReader("./data/testReaderRepeatCourse.json");
        try {
            League league = reader.read();
            fail("read file when it shouldn't be able to");
        } catch (JsonParseError e) {
            // Pass
        } catch (IOException e) {
            fail("couldn't read file");
        }
        // Pass
    }
    
    @Test
    void testReaderGeneralLeague() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLeague.json");
        try {
            League league = reader.read();
    
            assertEquals("WestField Golf", league.getCourses().get(0).getName());
            assertEquals("Albuquerque's finest", league.getCourses().get(1).getName());
            
            assertEquals(1, league.getCourses().get(0).getHole(0).getPar());
            assertEquals(2, league.getCourses().get(0).getHole(1).getPar());
            assertEquals(3, league.getCourses().get(0).getHole(2).getPar());
            assertEquals(4, league.getCourses().get(0).getHole(3).getPar());
            
            assertEquals(5, league.getCourses().get(1).getHole(0).getPar());
            assertEquals(6, league.getCourses().get(1).getHole(1).getPar());
            
            assertEquals("Bob Odenkirk", league.getGolfers().get(0).getName());
            assertEquals("Bryan Cranston", league.getGolfers().get(1).getName());
            
            assertEquals("Bob Odenkirk", league.getPerformances().get(0).getGolfers().get(0).getName());
            assertEquals("Bryan Cranston", league.getPerformances().get(0).getGolfers().get(1).getName());
            
            assertEquals( 7, league.getPerformances().get(0).getStrokes()[0][0]);
            assertEquals( 8, league.getPerformances().get(0).getStrokes()[0][1]);
            assertEquals( 9, league.getPerformances().get(0).getStrokes()[1][0]);
            assertEquals(10, league.getPerformances().get(0).getStrokes()[1][1]);
            
            assertEquals(4342, league.getGambler().getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
