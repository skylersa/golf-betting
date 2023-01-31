package ui;

import model.gambling.Gambler;
import model.game.Course;
import model.game.Game;
import model.game.League;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleManager {
    private Scanner kboard = new Scanner(System.in);
    private final League league;
    private final Gambler gambler;
    
    private ConsoleManager() {
        gambler = new Gambler();
        league = new League();
        
        mainMenu();
        kboard.close();
        
        
        league.addGolfer("Bob Odenkirk");
        league.addGolfer("Billiam Zero");
        league.addCourse("WestField Golf", 18);
        
        
        Game game = league.makeGame("WestField Golf",
                new ArrayList<>(List.of("Bob Odenkirk", "Billiam Zero")));
        System.out.println(game.playGame());
    }
    
    
    private void mainMenu() {
        System.out.println("Main menu\n1 - Start Game!\n2 - View or add golfers\n3 - View or add course");
        switch (kboard.nextInt()) {
            case 1:
                startGameMenu();
                break;
            case 2:
                golfersMenu();
                break;
            case 3:
                coursesMenu();
                break;
        }
    }
    
    private void coursesMenu() {
        if (!(league.getCourses().size() == 0)) {
            for (Course course : league.getCourses()) {
                System.out.println(course.getName() + " with " + course.getNumHoles() + " holes");
            }
        }
        System.out.println("Courses menu\n1 - Add a course\n2 - Return to main menu");
        switch (kboard.nextInt()) {
            case 1:
                addCourseMenu();
                break;
            case 2:
                mainMenu();
                break;
        }
    }
    
    private void addCourseMenu() {
        //TODO addCourseMenu is a stub
    }
    
    private void golfersMenu() {
        //TODO golfersMenu is a stub
    }
    
    private void startGameMenu() {
        //TODO startGameMenu is a stub
    }
    
    public static void main(String[] args) {
        new ConsoleManager();
    }
}
