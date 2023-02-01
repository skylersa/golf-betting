package ui;

import model.gambling.Gambler;
import model.game.Course;
import model.game.Game;
import model.game.League;

import java.util.Scanner;

/*
 * CONTAINS MAIN METHOD
 * Represents and manages the console-based ui for this program
 */
public class ConsoleManager {
    private Scanner kboard = new Scanner(System.in).useDelimiter("\n");
    private final League league;
    private final Gambler gambler;
    
    // EFFECTS: creates new consoleManager with a league and gambler
    private ConsoleManager() {
        gambler = new Gambler();
        league = new League();
        
        
        
        
        league.addGolfer("Bob Odenkirk");
        league.addGolfer("Bryan Cranston");
        league.addGolfer("Arron Paul");
        league.addGolfer("Billiam Zero");
        league.addCourse("WestField Golf", 18);
        league.addCourse("Albuquerque's finest", 9);
        league.addCourse("Tiny Golf!", 1);
        
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
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
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private void coursesMenu() {
        System.out.println("1 - Add a course\n2 - Return to main menu");
        if (league.getCourses().size() != 0) {
            System.out.println("Courses:");
        }
        
        for (Course course : league.getCourses()) {
            System.out.println("- " + course.getName() + " with " + course.getNumHoles() + " holes");
        }
        
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
        System.out.println("What would you like this course's name to be?");
        String inName = kboard.next();
        
        int inNumHoles = -1;
        while (!(inNumHoles > 0)) {
            System.out.println("How many holes does it have? (positive integer)");
            
            try {
                inNumHoles = kboard.nextInt();
            } catch (Exception e) {
                continue;
            }
        }
        
        league.addCourse(inName, inNumHoles);
        
        coursesMenu();
    }
    
    private void golfersMenu() {
        System.out.println("1 - Add a golfer\n2 - Return to main menu");
        if (league.getGolfers().size() != 0) {
            System.out.println("Golfers: (select with name)");
        }
        
        for (String name : league.getGolferNames()) {
            System.out.println("- " + name);
        }
        
        String choice = kboard.next();
        switch (choice) {
            case "1":
                addCourseMenu();
                break;
            case "2":
                mainMenu();
                break;
            default:
                if (!league.getGolferNames().contains(choice)) {
                    break;
                }
                //TODO golfersMenu is a stub
        }
    }
    
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewGolferMenu(String golferName) {
        //TODO viewGolferMenu is a stub
    }
    
    private void startGameMenu() {
        //TODO startGameMenu is a stub
    }
    
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.mainMenu();
    }
}
