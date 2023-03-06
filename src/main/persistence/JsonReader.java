package persistence;

import exceptions.RepeatGolferException;
import model.gambling.League;
import model.game.Hole;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        String name = jsonObject.getString("name");
        League league = new League();
        addGolfers(league, jsonObject.getJSONArray("golfers"));
        addCourses(league, jsonObject.getJSONArray("courses"));
        addPerformances(league, jsonObject.getJSONObject("performances"));
        return league;
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
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
    // EFFECTS: adds the courses contained in given JSONArray to the league
    private void addCourses(League league, JSONArray coursesJson) {
        for (Object golferJson : coursesJson) {
            JSONObject golferJsonCasted = (JSONObject) golferJson;
            addCourse(league, golferJsonCasted);
        }
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    private void addCourse(League league, JSONObject json) {
        List<Hole> holes = new ArrayList<>();
        JSONArray holesJson = json.getJSONArray("holes");
        for (Object holeJson : holesJson) {
            JSONObject holeJsonCasted = (JSONObject) holeJson;
            holes.add(new Hole(holeJsonCasted.getInt("par")));
        }
        
        league.addCourse(json.getString("name"), holes);
    }
    
    // REQUIRES: TODO: specify/ensure golfers and holes exist in league already
    // MODIFIES: 
    // EFFECTS: 
    private void addPerformances(League league, JSONObject performancesJson) {
        //TODO addPerformances is a stub
    }
}