package model.game;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

/*
 * Represents a golf course with some holes to play golf on
 */
public class Course  implements Writable {
    private final String name;
    private final int numHoles;
    private Hole[] holes;
    
    // REQUIRES: numHoles > 0
    // EFFECTS: create new Course with given name and and number of holes
    public Course(String name, int numHoles) {
        this.name = name;
        this.numHoles = numHoles;
    
        holes = new Hole[numHoles];
        for (int i = 0; i < this.numHoles; i++) {
            holes[i] = new Hole();
        }
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public Course(String name, List<Hole> holes) {
        this.name = name;
        this.holes = new Hole[holes.size()];
        for (int i = 0; i < holes.size(); i++) {
            this.holes[i] = holes.get(i);
        }
        
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumHoles() {
        return numHoles;
    }
    
    public Hole[] getHoles() {
        return this.holes;
    }
    
    public Hole getHole(int holeIndex) {
        return holes[holeIndex];
    }
    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("name", this.name);
        
        JSONArray holesJson = new JSONArray();
        for (Hole hole : holes) {
            holesJson.put(hole.toJson());
        }
        json.put("holes", holesJson);
        
        return json;
    }
}
