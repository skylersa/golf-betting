package model.performance;

import model.game.Game;

import java.util.ArrayList;

/*
 * represents the performance (in the form of strokes) of all the players in a game on all the holes on the course
 */
public class GameAllPerformance {
    private final Game game;
    private ArrayList<GameGolferPerformance> gameGolferPerformances = new ArrayList<>();
    private ArrayList<HoleAllPerformance> holeAllPerformances = new ArrayList<>();
    
    // REQUIRES: game must be completed or in progress
    // EFFECTS: create new GameAllPerformance on given game
    public GameAllPerformance(Game game) {
        this.game = game;
    }
    
    // REQUIRES: performance.getGolfer() is in this game
    // MODIFIES: this
    // EFFECTS: stores given performance
    public void addGameGolferPerformance(GameGolferPerformance performance) {
        this.gameGolferPerformances.add(performance);
    }
    
    
    // REQUIRES: performance.getHole() is in this games course
    // MODIFIES: this
    // EFFECTS: stores given performance
    public void addHoleAllPerformance(HoleAllPerformance performance) {
        this.holeAllPerformances.add(performance);
    }
    
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void addGameGolferPerformances(ArrayList<GameGolferPerformance> performances) {
        //TODO addGameGolferPerformances is a stub
    }
    
    // REQUIRES: all performances holes are in this games course
    // MODIFIES: this
    // EFFECTS: stores given performances
    public void addHoleAllPerformances(ArrayList<HoleAllPerformance> performances) {
        this.holeAllPerformances.addAll(performances);
    }
    
    //TODO implement and test getBestPerformingGolfer
//    // REQUIRES: at least one player's performance stored
//    // EFFECTS: return the player that had the best performance in this game
//    public Golfer getBestPerformingGolfer() {
//        return null;
//    }
    
    public Game getGame() {
        return game;
    }
    
    public ArrayList<GameGolferPerformance> getGameGolferPerformances() {
        return this.gameGolferPerformances;
    }
    
    public ArrayList<HoleAllPerformance> getHoleAllPerformances() {
        return this.holeAllPerformances;
    }
}

