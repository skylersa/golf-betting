package model.game;

/*
 * Represents a golfer with a name
 */
public class Golfer {
    private final String name;
    
    // REQUIRES: name is unique to this golfer
    // EFFECTS: create new golfer with given name
    public Golfer(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
