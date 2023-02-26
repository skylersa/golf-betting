package model.game;

import model.performance.GameGolferPerformance;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a golfer with a name and a history of play
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
