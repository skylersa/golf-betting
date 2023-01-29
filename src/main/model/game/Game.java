package model.game;

import model.player.Performance;
import model.player.Player;

import java.util.ArrayList;

public class Game {
    Course course;
    ArrayList<Player> players;
    
    public Game(Course course, ArrayList<Player> players) {
        this.course = course;
        this.players = players;
    }
    
    public Performance playGame() {
        Performance results = new Performance(this.course);
        for (Player player : players) {
            for (int i = 0; i < course.getNumHoles(); i++) {
                playHole(course.getHole(i), player);
            }
        }
        return results;
    }
    
    // TODO: implement player by player performance...
    private int playHole(Hole hole, Player player) {
        int maxDeviation = hole.getPar() - 1;
        int deviation = (int)(Math.random() * (maxDeviation) * 2) - maxDeviation;
        int strokes = hole.getPar() + deviation;
        
        return strokes;
    }
}
