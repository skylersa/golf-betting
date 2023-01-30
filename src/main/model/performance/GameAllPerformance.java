package model.performance;

import model.Golfer;
import model.game.Course;
import model.game.Game;

import java.util.ArrayList;

/*
 * represents the performance (in the form of strokes) of all the players in a game on all the holes on the course
 */
public class GameAllPerformance {
    private final Game game;
    private ArrayList<GameGolferPerformance> gameGolferPerformances = new ArrayList<>();
    private ArrayList<HoleAllPerformance> holeAllPerformances = new ArrayList<>();
    
    
    // REQUIRES: game.isComplete()
    // EFFECTS: create new GameAllPerformance on given game
    public GameAllPerformance(Game game) {
        this.game = game;
    }
    
    // MODIFIES: this
    // EFFECTS: stores given performance
    public void addGameGolferPerformance(GameGolferPerformance performance) {
        this.gameGolferPerformances.add(performance);
    }
    
    // MODIFIES: this
    // EFFECTS: stores given performance
    public void addHoleAllPerformance(HoleAllPerformance performance) {
        this.holeAllPerformances.add(performance);
    }
    
    // REQUIRES: at least one player's performance stored
    // EFFECTS: return the player that had the best performance in this game
    public Golfer getBestPerformingGolfer() {
        return null;
        //TODO getBestPerformingGolfer is a stub
    }
    
    public ArrayList<GameGolferPerformance> getGameGolferPerformances() {
        return this.gameGolferPerformances;
    }
    
    public ArrayList<HoleAllPerformance> getHoleAllPerformances() {
        return this.holeAllPerformances;
    }
}

