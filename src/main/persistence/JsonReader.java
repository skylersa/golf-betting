package persistence;

import model.gambling.League;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        addGolfers(league, jsonObject);
        addCourses(league, jsonObject);
        addPerformances(league, jsonObject);
        return league;
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    private void addGolfers(League league, JSONObject json) {
        //TODO addGolfers is a stub
    }
    
    // REQUIRES: TODO
    // MODIFIES: 
    // EFFECTS: 
    private void addCourses(League league, JSONObject json) {
        //TODO addCourses is a stub
    }
    
    // REQUIRES: TODO
    // MODIFIES: 
    // EFFECTS: 
    private void addPerformances(League league,JSONObject json) {
        //TODO addPerformances is a stub
    }
}