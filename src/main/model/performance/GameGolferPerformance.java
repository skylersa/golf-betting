package model.performance;

import model.game.Golfer;
import model.game.Game;

import java.util.ArrayList;

/*
 * Represents the performance of one golfer in a game in that entire game.
 * Notably, it holds the performance of this golfer on each of the game's holes
 * See Performance structure.jpg for details the structure of performance holders
 */
public class GameGolferPerformance {
    private final Game game;
    private final Golfer golfer;
    private ArrayList<HoleGolferPerformance> holeGolferPerformances;
    
    // REQUIRES: game.isComplete()
    // EFFECTS: creates new GameGolferPerformance with given game and golfer
    public GameGolferPerformance(Game game, Golfer golfer) {
        this.game = game;
        this.golfer = golfer;
        holeGolferPerformances = new ArrayList<>();
    }
    
    // MODIFIES: this
    // EFFECTS: store given performance
    public void addGolferPerformance(HoleGolferPerformance performance) {
        this.holeGolferPerformances.add(performance);
    }
    
    
    //TODO test and implement getBestPerformingHole
//    // REQUIRES: at least one hole performance stored
//    // EFFECTS: returns the hole in which this player performed the best
//    public Hole getBestPerformingHole() {
//        return null;
//
//    }
    
    public Golfer getGolfer() {
        return golfer;
    }
    
    public Game getGame() {
        return game;
    }
    
    public ArrayList<HoleGolferPerformance> getHoleGolferPerformances() {
        return holeGolferPerformances;
    }
}
