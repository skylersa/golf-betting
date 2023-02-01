package model.game;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;
import model.performance.HoleGolferPerformance;

import java.util.ArrayList;

/*
 * Represents a game of golf on a course, played by the list of golfers
 */
public class Game {
    private boolean isComplete = false;
    private final Course course;
    private final ArrayList<Golfer> golfers;
    
    // REQUIRES: golfers !empty
    // EFFECTS: create new game to be played on given course by given golfers
    public Game(Course course, ArrayList<Golfer> golfers) {
        this.course = course;
        this.golfers = golfers;
    }
    
    // REQUIRES: !game.hasBeenPlayed()
    // MODIFIES: this
    // EFFECTS: returns the performance of the players
    public GameAllPerformance playGame() {
        GameAllPerformance resultsAll = new GameAllPerformance(this);
        ArrayList<HoleAllPerformance> resultsHoles = new ArrayList<>();
        for (Hole hole : course.getHoles()) {
            resultsHoles.add(new HoleAllPerformance(hole));
        }
        for (Golfer golfer : golfers) {
            GameGolferPerformance resultsGolfer = new GameGolferPerformance(this, golfer);
            
            for (int i = 0; i < course.getNumHoles(); i++) {
                HoleGolferPerformance resultHole = course.getHole(i).playHole(golfer);
                resultsHoles.get(i).addGolferPerformance(resultHole);
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
    
    public ArrayList<Golfer> getGolfers() {
        return this.golfers;
    }
    
    public Course getCourse() {
        return this.course;
    }
}
