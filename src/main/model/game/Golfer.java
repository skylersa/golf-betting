package model.game;

import model.performance.GameGolferPerformance;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a golfer with a name and a history of play
 */
public class Golfer {
    private final String name;
    private List<GameGolferPerformance> gamePerformanceHistory;
    
    // REQUIRES: name is unique to this golfer
    // EFFECTS: create new golfer with given name
    public Golfer(String name) {
        this.name = name;
        gamePerformanceHistory = new ArrayList<>();
    }
    
    // MODIFIES: this
    // EFFECTS: add given performance to golfer's history
    public void addGamePerformance(GameGolferPerformance performance) {
        this.gamePerformanceHistory.add(performance);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<GameGolferPerformance> getGamePerformanceHistory() {
        return gamePerformanceHistory;
    }
}
