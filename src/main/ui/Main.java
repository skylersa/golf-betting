package ui;

import model.game.Game;
import model.game.League;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        League league = new League();
        league.addGolfer("Bob Odenkirk");
        league.addGolfer("Billiam Zero");
        league.addCourse("WestField Golf", 18);
        
        
        Game game = league.makeGame("WestField Golf",
                new ArrayList<>(List.of("Bob Odenkirk", "Billiam Zero")));
        System.out.println(game.playGame());
    }
}
