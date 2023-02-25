package model.game;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;
import model.performance.HoleGolferPerformance;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a game of golf on a course, played by the list of golfers
 */
public class Game implements Writable {
    private boolean isComplete = false;
    private final Course course;
    private final List<Golfer> golfers;
    
    // REQUIRES: golfers !empty
    // EFFECTS: create new game to be played on given course by given golfers
    public Game(Course course, List<Golfer> golfers) {
        this.course = course;
        this.golfers = golfers;
    }
    
    // REQUIRES: !this.isComplete()
    // MODIFIES: this
    // EFFECTS: returns the performance of all the golfers in the game, adds golfers' respective performances to their
    // performance history
    public GameAllPerformance playGame() {
        GameAllPerformance resultsAll = new GameAllPerformance(this);
        List<HoleAllPerformance> resultsHoles = new ArrayList<>();
        for (Hole hole : course.getHoles()) {
            resultsHoles.add(new HoleAllPerformance(hole));
        }
        for (Golfer golfer : golfers) {
            GameGolferPerformance resultsGolfer = new GameGolferPerformance(this, golfer);
            
            for (int i = 0; i < course.getNumHoles(); i++) {
                HoleGolferPerformance resultHole = course.getHole(i).playHole(golfer);
                resultsHoles.get(i).addHoleGolferPerformance(resultHole);
                resultsGolfer.addGolferPerformance(resultHole);
            }
            resultsAll.addGameGolferPerformance(resultsGolfer);
        }
        resultsAll.addHoleAllPerformances(resultsHoles);
        this.isComplete = true;
        return resultsAll;
    }
    
    public boolean isComplete() {
        return isComplete;
    }
    
    public List<Golfer> getGolfers() {
        return this.golfers;
    }
    
    public Course getCourse() {
        return this.course;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray golfersJson = new JSONArray();
        for (Golfer golfer : golfers) {
            golfersJson.put(golfer.toJson());
        }
        
        JSONObject json = new JSONObject();
        json.put("complete", this.isComplete);
        json.put("course", this.course);
        json.put("golfers", golfersJson);
        
        return json;
    }
}
