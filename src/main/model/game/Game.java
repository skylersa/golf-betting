package model.game;

import model.performance.GameAllPerformance;
import model.Golfer;
import model.performance.GameGolferPerformance;

import java.util.ArrayList;

public class Game {
    private boolean isComplete = false;
    private Course course;
    private ArrayList<Golfer> golfers;
    
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
        for (Golfer golfer : golfers) {
            GameGolferPerformance resultsGolfer = new GameGolferPerformance(this, golfer);
            
            for (Hole hole : course.holes) {
                resultsGolfer.addGolferPerformance(hole.playHole(golfer));
            }
            resultsAll.addGameGolferPerformance(resultsGolfer);
        }
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
