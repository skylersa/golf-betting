package model.performance;

import exceptions.GolferNotPresentException;
import exceptions.HoleNotPresentException;
import model.game.Golfer;
import model.game.Hole;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Represents the performance of all the golfers in a game in that entire game.
 * Holds a 2d List of HoleGolferPerformances. The dimensions hold
 *     - 1st holes vertically
 *     - 2nd players horizontally
 * TODO simplify name
 * TODO add addNext for performant construction
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
    
    // MODIFIES: //TODO
    // EFFECTS:
    public HoleAllPerformance getHolePerformance(Hole hole) {
        int holeIndex = holes.indexOf(hole);
        if (holeIndex == -1) {
            throw new HoleNotPresentException();
        }
    
        return new HoleAllPerformance(golfers, Arrays.asList(performances[holeIndex]));
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public GameGolferPerformance getGolferPerformance(Golfer golfer) {
        int golferIndex = golfers.indexOf(golfer);
        if (golferIndex == -1) {
            throw new GolferNotPresentException();
        }
        
        return getGolferPerformance(golferIndex);
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public GameGolferPerformance getGolferPerformance(int index) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < holes.size(); i++) {
            result.add(performances[i][index]);
        }
    
        return new GameGolferPerformance(holes, result);
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public Golfer getBestPerformingGolfer() {
        int bestGolferIndex = -1; // PLACEHOLDER VALUES
        int bestGolferRes = 100000;
    
        for (int i = 0; i < golfers.size(); i++) {
            List<Integer> holePerf = getGolferPerformance(i).getStrokes();
            // TODO simplify with iterator
            int golferRes = 0;
            for (int stroke : holePerf) {
                golferRes += stroke;
            }
            if (golferRes < bestGolferRes) {
                bestGolferIndex = i;
                bestGolferRes = golferRes;
            }
        }
        
        return golfers.get(bestGolferIndex);
    }
    
    public List<Hole> getHoles() {
        return holes;
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    public Integer[][] getPerformances() {
        return performances.clone();
        
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray holesJson = new JSONArray();
        JSONArray golfersJson = new JSONArray();
        JSONArray performancesJson = new JSONArray();
        
        for (Hole hole : holes) {
            holesJson.put(hole.toJson());
        }
        
        for (Golfer golfer : golfers) {
            golfersJson.put(golfer.toJson());
        }
        
        for (Integer[] holePerf : performances) {
            JSONArray holePerfJson = new JSONArray();
            for (Integer strokeCount : holePerf) {
                holePerfJson.put(strokeCount);
            }
            performancesJson.put(holePerfJson);
        }
        
        JSONObject json = new JSONObject();
        json.put("holes", holesJson);
        json.put("golfers", golfersJson);
        json.put("performances", performancesJson);
        
        return json;
    }
}

