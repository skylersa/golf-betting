package model.performance;

import model.game.Golfer;
import model.game.Hole;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the performance of all the golfers in a game on one hole.
 * Like GameGolferPerformance, it holds a list of holeGolferPerformances. But, these are organized by hole (rather than
 * by golfer line in GameGolferPerformance)
 * See Performance structure.jpg for details the structure of performance holders
 */
public class HoleAllPerformance implements Writable {
    private final Hole hole;
    private List<HoleGolferPerformance> holeGolferPerformances;
    
    // EFFECTS: create new HoleAllPerformance on given hole
    public HoleAllPerformance(Hole hole) {
        this.hole = hole;
        holeGolferPerformances = new ArrayList<>();
    }
    
    // REQUIRES: performance.getHole() == this.getHole()
    // MODIFIES: this
    // EFFECTS: stores given golfer performance on this hole
    public void addHoleGolferPerformance(HoleGolferPerformance performance) {
        this.holeGolferPerformances.add(performance);
    }
    
    // REQUIRES: at least one performance stored
    // EFFECTS: returns the golfer with the best par deviation
    public Golfer getBestPerformingGolfer() {
        Golfer bestGolfer = new Golfer("PLACEHOLDER GOLFER");
        int bestStrokes = 1000;
        for (HoleGolferPerformance performance : holeGolferPerformances) {
            if (performance.getStrokes() < bestStrokes) {
                bestGolfer = performance.getGolfer();
                bestStrokes = performance.getStrokes();
            }
        }
        return bestGolfer;
    }
    
    public List<HoleGolferPerformance> getHoleGolferPerformances() {
        return holeGolferPerformances;
    }
    
    public Hole getHole() {
        return hole;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray hgpJson = new JSONArray();
        for (HoleGolferPerformance hgp : this.holeGolferPerformances) {
            hgpJson.put(hgp.toJson());
        }
    
        JSONObject json = new JSONObject();
        json.put("hole", this.hole.toJson());
        json.put("holeGolferPerformances", hgpJson);
    
        return json;
    }
}
