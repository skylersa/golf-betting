package model.game;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a golfer with a name
 */
public class Golfer implements Writable {
    private final String name;
    
    // REQUIRES: name is unique to this golfer
    // EFFECTS: create new golfer with given name
    public Golfer(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public JSONObject toJson() {
        return new JSONObject().put("name", this.name);
    }
}
