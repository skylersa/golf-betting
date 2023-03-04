package model.performance;

import exceptions.GolferNotPresentException;
import exceptions.HoleNotPresentException;
import model.game.Game;
import model.game.Golfer;
import model.game.Hole;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the performance of all the golfers in a game in that entire game.
 * Holds a 2d List of HoleGolferPerformances. The dimensions hold
 *     - 1st holes vertically
 *     - 2nd players horizontally
 */
public class GameAllPerformance implements Writable {
    private List<Hole> holes;
    private List<Golfer> golfers;
    private Integer[][] performances;
    
    // REQUIRES: game must be completed or in progress
    // EFFECTS: create new GameAllPerformance on given game
    public GameAllPerformance(List<Hole> holes, List<Golfer> golfers) {
        this.holes = new ArrayList<>(holes);
        this.golfers = new ArrayList<>(golfers);
        performances = new Integer[this.holes.size()][golfers.size()];
    }
    
    // MODIFIES: this
    // EFFECTS: stores given performance
    // If given golfer or hole is not present, throw relevant notPresentException
    public void addHolePerformance(HoleGolferPerformance hgp) {
        int golferIndex = golfers.indexOf(hgp.getGolfer());
        int holeIndex = holes.indexOf(hgp.getHole());
        if (golferIndex == -1) {
            throw new GolferNotPresentException();
        }
        if (holeIndex == -1) {
            throw new HoleNotPresentException();
        }
        
        performances[holeIndex][golferIndex] = hgp.getStrokes();
        
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public Golfer getBestPerformingGolfer() {
        //TODO getBestPerformingGolfer is a stub
    }
    
    // MODIFIES: //TODO
    // EFFECTS:
    public Integer[] getHolePerformance(Hole hole) {
        int holeIndex = holes.indexOf(Hole);
        if (holeIndex == -1) {
            throw new HoleNotPresentException();
        }
    
        return performances[holeIndex];
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public Integer[] getGolferPerformance(Golfer golfer) {
        int golferIndex = golfers.indexOf(golfer);
        if (golferIndex == -1) {
            throw new GolferNotPresentException();
        }
        
        Integer[] result = new Integer[];
        for (int i = 0; i < holes.size(); i++) {
            result[i] = performances[i][golferIndex];
        }
        
        return result;
    }
    
    public List<Hole> getHoles() {
        return holes;
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray holesJson = new JSONArray();
        JSONArray golfersJson = new JSONArray();
        JSONArray
        
        for (Hole hole : holes) {
            holesJson.put(hole.toJson());
        }
        
        for (Golfer golfer : golfers) {
            golfersJson.put(golfer.toJson());
        }
        //TODO toJson is a stub
    }
}

