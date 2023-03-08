package model.performance;

import model.game.Hole;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/*
 * Represents the performance of one golfer in a game in that entire game.
 * Transient class only, not for storage.
 */
public final class GameGolferPerformance {
    private List<Hole> holes;
    private List<Integer> strokes;
    private String courseName;
    
    // EFFECTS: Creates new GameGolferPerformance on given holes with given strokes on given course name
    //       number of holes and number of strokes must be equal, otherwise, will throw InputMismatchException
    public GameGolferPerformance(List<Hole> holes, List<Integer> strokes, String courseName) {
        if (holes.size() != strokes.size()) {
            throw new InputMismatchException();
        }
        this.holes = new ArrayList<>(holes);
        this.strokes = new ArrayList<>(strokes);
        this.courseName = courseName;
    }
    
    public List<Hole> getHoles() {
        return holes;
    }
    
    public List<Integer> getStrokes() {
        return strokes;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public Hole getHole(int index) {
        return holes.get(index);
    }
    
    public int getStroke(int index) {
        return strokes.get(index);
    }
    
    public int size() {
        return holes.size();
    }
}