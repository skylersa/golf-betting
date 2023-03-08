package model.persistence;

import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import model.gambling.League;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.HoleGolferPerformance;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO
public class JsonWriterTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Pass
        }
        // Pass
    }
    
    @Test
    public void testWriterEmptyWorkroom() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json");
            writer.open();
            writer.write(league);
            writer.close();
            
            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json");
            league = reader.read();
            
            assertEquals(0, league.getGolfers().size());
            assertEquals(0, league.getPerformances().size());
            assertEquals(0, league.getCourses().size());
            assertEquals(5000, league.getGambler().getBalance());
            // Pass
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        // Pass
    }
    
    @Test
    public void testWriterGeneralWorkroom() {
        try {
            League league = new League();
            prepLeague(league);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeague.json");
            writer.open();
            writer.write(league);
            writer.close();
            
            JsonReader reader = new JsonReader("./data/testWriterGeneralLeague.json");
            league = reader.read();
    
            checkLeague(league);
    
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    
    private void checkLeague(League league) {
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
        
        assertEquals(7, league.getPerformances().get(0).getStrokes()[0][0]);
        assertEquals(8, league.getPerformances().get(0).getStrokes()[0][1]);
        assertEquals(9, league.getPerformances().get(0).getStrokes()[1][0]);
        assertEquals(10, league.getPerformances().get(0).getStrokes()[1][1]);
        
        assertEquals(4342, league.getGambler().getBalance());
    }
    
    private void prepLeague(League league) {
        try {
            league.getGambler().bet(false, 5000 - 4342);
            league.addCourse("WestField Golf",
                    Arrays.asList(new Hole(1),
                            new Hole(2),
                            new Hole(3),
                            new Hole(4)));
            league.addCourse("Albuquerque's finest",
                    Arrays.asList(new Hole(5), new Hole(6)));
            league.addGolfer("Bob Odenkirk");
            league.addGolfer("Bryan Cranston");
            GameAllPerformance perf = new GameAllPerformance(
                    Arrays.asList(league
                            .getCourse("Albuquerque's finest")
                            .getHoles()),
                    league.getGolfers(),
                    "Albuquerque's finest");
            perf.addHolePerformance(new HoleGolferPerformance(league
                    .getCourse("Albuquerque's finest")
                    .getHole(0), league.getGolfer("Bob Odenkirk"), 7));
            perf.addHolePerformance(new HoleGolferPerformance(league
                    .getCourse("Albuquerque's finest")
                    .getHole(0), league.getGolfer("Bryan Cranston"), 8));
            perf.addHolePerformance(new HoleGolferPerformance(league
                    .getCourse("Albuquerque's finest")
                    .getHole(1), league.getGolfer("Bob Odenkirk"), 9));
            perf.addHolePerformance(new HoleGolferPerformance(league
                    .getCourse("Albuquerque's finest")
                    .getHole(1), league.getGolfer("Bryan Cranston"), 10));
            league.addPerformance(perf);
        }  catch (RepeatGolferException e) {
            fail("League is Broken, golfer wise");
        } catch (RepeatCourseException e) {
            fail("League is Broken, course wise");
        }
    }
}
