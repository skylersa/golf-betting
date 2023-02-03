package model.performance;

import model.game.Game;
import model.game.Golfer;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the performance of all the golfers in a game in that entire game.
 * Notably, it holds
 *     a list of each of the golfers' performances in the game
 *     a list of all the golfers' performances on each hole
 * See Performance structure.jpg for details the structure of performance holders
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
    
    
    // REQUIRES: performance.getHole() is in this games course, performance's golfers are the same as this game's
    // MODIFIES: this
    // EFFECTS: stores given performance
    public void addHoleAllPerformance(HoleAllPerformance performance) {
        this.holeAllPerformances.add(performance);
    }
    
    // REQUIRES: all performances.getGame() is this game, performance.getGolfer() is in this game
    // MODIFIES: this
    // EFFECTS: sotres given performances
    public void addGameGolferPerformances(List<GameGolferPerformance> performances) {
        this.gameGolferPerformances.addAll(performances);
    }
    
    // REQUIRES: all performances holes are in this games course
    // MODIFIES: this
    // EFFECTS: stores given performances
    public void addHoleAllPerformances(List<HoleAllPerformance> performances) {
        this.holeAllPerformances.addAll(performances);
    }
    
    // REQUIRES: at least one player's performance stored
    // EFFECTS: return the player that had the best performance in this game
    public Golfer getBestPerformingGolfer() {
        List<Golfer> bestGolfersPerHole = new ArrayList<>();
        for (HoleAllPerformance performance : holeAllPerformances) {
            bestGolfersPerHole.add(performance.getBestPerformingGolfer());
        }
        return mostOccurredGolfer(bestGolfersPerHole);
    
    }
    
    private Golfer mostOccurredGolfer(List<Golfer> bestGolfersPerHole) {
        List<Golfer> occurrencesGolfer = new ArrayList<>();
        List<Integer> occurrencesNumber = new ArrayList<>();
        
        for (Golfer golfer : bestGolfersPerHole) {
            int counterGolferIndex = occurrencesGolfer.indexOf(golfer);
            if (counterGolferIndex == -1) { // Not in counter yet
                occurrencesGolfer.add(golfer);
                occurrencesNumber.add(1);
            } else { // Already in counter
                occurrencesNumber.set(counterGolferIndex, occurrencesNumber.get(counterGolferIndex) + 1);
            }
        }
        
        int most = 0; // PLACEHOLDER VALUES
        int mostIndex = 200;
        for (int i = 0; i < occurrencesNumber.size(); i++) {
            if (occurrencesNumber.get(i) > most) {
                mostIndex = i;
                most = occurrencesNumber.get(i);
            }
        }
        return occurrencesGolfer.get(mostIndex);
    }
    
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

