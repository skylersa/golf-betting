package persistence;

import exceptions.JsonParseError;
import exceptions.RepeatGolferException;
import exceptions.RepeatCourseException;
import model.gambling.Gambler;
import model.gambling.League;
import model.game.Course;
import model.game.Golfer;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.HoleGolferPerformance;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads league from JSON data stored in file
// Takem from JsonSerializationDemo, Thank you!
public class JsonReader {
    private String source;
    
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }
    
    // EFFECTS: reads league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }
    
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        
        return contentBuilder.toString();
    }
    
    // EFFECTS: parses league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        League league = new League();
        addGolfers(league, jsonObject.getJSONArray("golfers"));
        addCourses(league, jsonObject.getJSONArray("courses"));
        addPerformances(league, jsonObject.getJSONArray("performances"));
        addGambler(league, jsonObject.getJSONObject("gambler"));
        return league;
    }
    
    // MODIFIES: league
    // EFFECTS: adds golfers from given JSONArray to the given league
    private void addGolfers(League league, JSONArray golfersJson) {
        for (Object golferJson : golfersJson) {
            JSONObject golferJsonCasted = (JSONObject) golferJson;
            try {
                league.addGolfer(golferJsonCasted.getString("name"));
            } catch (RepeatGolferException e) {
                throw new Error("Json edited between runs?", e);
            }
        }
    }
    
    // MODIFIES: league
    // EFFECTS: adds the courses contained in given JSONArray to the League
    private void addCourses(League league, JSONArray coursesJson) {
        for (Object golferJson : coursesJson) {
            JSONObject golferJsonCasted = (JSONObject) golferJson;
            addCourse(league, golferJsonCasted);
        }
    }
    
    // MODIFIES: league
    // EFFECTS: Adds course in given JSONObject to given League
    private void addCourse(League league, JSONObject json) {
        List<Hole> holes = new ArrayList<>();
        JSONArray holesJson = json.getJSONArray("holes");
        for (Object holeJson : holesJson) {
            JSONObject holeJsonCasted = (JSONObject) holeJson;
            holes.add(new Hole(holeJsonCasted.getInt("par")));
        }
        try {
            league.addCourse(json.getString("name"), holes);
        } catch (RepeatCourseException e) {
            throw new JsonParseError(e);
        }
    }
    
    // MODIFIES: league
    // EFFECTS: Adds performances from given JSONArray to given league
    //     if any performance courses and golfers are not already represented in the given
    //     League, throws JsonParseError
    private void addPerformances(League league, JSONArray performancesJson) {
        for (Object performanceJson : performancesJson) {
            addPerformance(league, (JSONObject) performanceJson);
        }
    }
    
    // MODIFIES: league
    // EFFECTS: adds performance from given JSONObject to given league
    //      if performance courses and golfers are not already represented in the given
    //     League, throws JsonParseError
    private void addPerformance(League league, JSONObject performanceJson) {
        Course course = league.getCourse(performanceJson.getString("courseName"));
        
        List<Golfer> golfers = parseGolfers(league, performanceJson.getJSONArray("golfers"));
        List<Hole> holes = Arrays.asList(course.getHoles());
        
        GameAllPerformance gap = new GameAllPerformance(holes, golfers, course.getName());
        JSONArray strokesJson = performanceJson.getJSONArray("strokes");
        for (int holeIndex = 0; holeIndex < strokesJson.length(); holeIndex++) {
            JSONArray holeStrokeJson = strokesJson.getJSONArray(holeIndex);
            Hole hole = holes.get(holeIndex);
            
            for (int golferIndex = 0; golferIndex < holeStrokeJson.length(); golferIndex++) {
                Golfer golfer = golfers.get(golferIndex);
                // TODO: make more efficeient, good lord. maybe an index adder on gap
                gap.addHolePerformance(new HoleGolferPerformance(
                        hole,
                        golfer,
                        holeStrokeJson.getInt(golferIndex)));
            }
        }
        try {
            league.addPerformance(gap);
        } catch (InputMismatchException e) {
            throw new JsonParseError(e);
        }
    }
    
    // EFFECTS: Retrieves the Golfer objects from the league that are defined in the given JSONArray
    private List<Golfer> parseGolfers(League league, JSONArray golfersJson) {
        List<Golfer> result = new ArrayList<>();
        for (Object golferObject : golfersJson) {
            JSONObject golferJson = (JSONObject) golferObject;
            result.add(league.getGolfer(golferJson.getString("name")));
        }
        
        if (golfersJson.length() != result.size()) {
            throw new JsonParseError("Golfer from performance is missing in league");
        } else {
            return result;
        }
    }
    
    
    // MODIFIES: league
    // EFFECTS: Sets given League's gambler as defined in given JSONObject
    private void addGambler(League league, JSONObject gamblerJson) {
        league.setGambler(new Gambler(gamblerJson.getInt("balance")));
    }
}