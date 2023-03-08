package model.performance;

import model.game.Golfer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/*
 * Represents the performance of many golfers on one hole
 * Transient type only, not for storage.
 */
public final class HoleAllPerformance {
    private List<Golfer> golfers;
    private List<Integer> strokes;
    
    // EFFECTS: create new HoleAllPerformance on given hole
    public HoleAllPerformance(List<Golfer> golfers, List<Integer> strokes) {
        if (golfers.size() != strokes.size()) {
            throw new InputMismatchException();
        }
        this.golfers = new ArrayList<>(golfers);
        this.strokes = new ArrayList<>(strokes);
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    public List<Integer> getStrokes() {
        return strokes;
    }
    
    public int getStroke(int index) {
        return strokes.get(index);
    }
    
    public Golfer getGolfer(int index) {
        return golfers.get(index);
    }
    
    public int size() {
        return golfers.size();
    }
}