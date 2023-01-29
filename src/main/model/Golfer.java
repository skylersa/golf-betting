package model;

import model.game.Game;
import model.performance.GameGolferPerformance;

import java.util.ArrayList;

public class Golfer {
    private final String name;
    ArrayList<GameGolferPerformance> gamePerformanceHistory;
    //    private int luck;
    //    private int injuryState;
    //    private int weatherresistance;
    
    // REQUIRES: name is a real person's name
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
    
    // REQUIRES: at least one game played
    // EFFECTS: returns the game in which golfer performed their best
    public Game getBestGame() {
        return null;
        //TODO getBestGame is a stub
    }
}
