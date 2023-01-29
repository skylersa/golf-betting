package model.player;

import model.game.Course;

import java.util.ArrayList;

/*
 * INVARIANT: each index of players corresponds to the same index in strokes, they are the same length
 * represents the performance (in the form of strokes) of all the players in a game on all the holes on the course
 */
//TODO refactor to spec of UML
public class Performance {
    private final Course course;
    private ArrayList<Player> players;
    private ArrayList<ArrayList<Integer>> strokes;
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public Performance(Course course) {
        this.course = course;
        this.players = new ArrayList<>();
        this.strokes = new ArrayList<>();
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public void addPlay(Player player, int playStrokes) {
        int playerIndex = players.indexOf(player);
    
        if (playerIndex == -1) { // Given player is not in list yet
            players.add(player);
            strokes.add((new ArrayList<>(playStrokes)));
        } else {
            strokes.get(playerIndex).add(playStrokes);
        }
    }
}

