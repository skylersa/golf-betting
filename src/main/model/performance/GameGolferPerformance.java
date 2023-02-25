package model.performance;

import model.game.Golfer;
import model.game.Game;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the performance of one golfer in a game in that entire game.
 * Notably, it holds the performance of this golfer on each of the game's holes
 * See Performance structure.jpg for details the structure of performance holders
 */
public class GameGolferPerformance implements Writable {
    private final Game game;
    private final Golfer golfer;
    private List<HoleGolferPerformance> holeGolferPerformances;
    
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
    
    public Golfer getGolfer() {
        return golfer;
    }
    
    public Game getGame() {
        return game;
    }
    
    public List<HoleGolferPerformance> getHoleGolferPerformances() {
        return holeGolferPerformances;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray hgpJson = new JSONArray();
        for (HoleGolferPerformance hgp : this.holeGolferPerformances) {
            hgpJson.put(hgp.toJson());
        }
    
        JSONObject json = new JSONObject();
        json.put("game", this.game.toJson());
        json.put("golfer", this.golfer.toJson());
        json.put("holeGolferPerformances", hgpJson);
        
        return json;
    }
}
