package model.performance;

import model.game.Hole;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/*
 * Represents the performance of one golfer in a game in that entire game.
 * //TODO simplify name
 */
public final class GameGolferPerformance {
    private List<Hole> holes;
    private List<Integer> strokes;
    
    // REQUIRES: game.isComplete()
    // EFFECTS: creates new GameGolferPerformance with given game and golfer
    public GameGolferPerformance(List<Hole> holes, List<Integer> strokes) {
        if (holes.size() != strokes.size()) {
            throw new InputMismatchException();
        }
        this.holes = new ArrayList<>(holes);
        this.strokes = new ArrayList<>(strokes);
    }
    
    public List<Hole> getHoles() {
        return holes;
    }
    
    public List<Integer> getStrokes() {
        return strokes;
    }
    
    public Hole getHole(int index) {
        return holes.get(index);
    }
    
    public int getStroke(int index) {
        return strokes.get(index);
    }
    
    public int size() {
        assert holes.size() == strokes.size();
        return holes.size();
    }
}