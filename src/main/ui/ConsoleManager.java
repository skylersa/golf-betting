package ui;

import model.gambling.Gambler;
import model.game.Course;
import model.game.Game;
import model.game.League;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleGolferPerformance;

import java.util.ArrayList;
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
        league.addCourse("WestField Golf", 4);
        league.addCourse("Albuquerque's finest", 3);
        league.addCourse("Tiny Golf!", 1);
        
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private void mainMenu() {
        System.out.println("Main menu\n1 - Start Game!\n2 - View or add golfers\n3 - View or add course");
        switch (kboard.nextInt()) {
            case 1:
                startGameFlow();
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
    
    // REQUIRES: /TODO
    // MODIFIES:
    // EFFECTS:
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
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private void golfersMenu() {
        System.out.println("1 - Add a golfer\n2 - Return to main menu");
        if (league.getGolfers().size() != 0) {
            System.out.println("Golfers:");
        }
        
        for (int i = 0; i < league.getGolferNames().size(); i++) {
            System.out.println((i + 3) + " - " + league.getGolferNames().get(i));
        }
        
        int choice = -1;
        while (!(0 < choice && choice < league.getGolferNames().size() + 3)) {
            choice = kboard.nextInt();
        }
        switch (choice) {
            case 1:
                addCourseMenu();
                break;
            case 2:
                mainMenu();
                break;
            default:
                viewGolferMenu(league.getGolferNames().get(choice - 3));
        }
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private void viewGolferMenu(String golferName) {
        System.out.println(golferName + "'s stats");
        ArrayList<GameGolferPerformance> golferHistory = league.getGolfer(golferName).getGamePerformanceHistory();
        for (GameGolferPerformance perf : golferHistory) {
            System.out.println("Game at " + perf.getGame().getCourse().getName());
            System.out.println("Hole Par    ");
            for (HoleGolferPerformance holePerf : perf.getHoleGolferPerformances()) {
                System.out.print(holePerf.getHole().getPar() + "  ");
            }
            System.out.println("Performance ");
            for (HoleGolferPerformance holePerf : perf.getHoleGolferPerformances()) {
                System.out.print(holePerf.getStrokes() + "  ");
            }
        }
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private void startGameFlow() {
        // Select a course
        System.out.println("Select a course:");
        for (int i = 0; i < league.getCourseNames().size(); i++) {
            System.out.println((i + 1) + " - " + league.getCourseNames().get(i));
        }
        
        int courseChoiceIndex = -1;
        while (!(0 < courseChoiceIndex && courseChoiceIndex < league.getCourseNames().size() + 1)) {
            courseChoiceIndex = kboard.nextInt();
        }
        String courseChoice = league.getCourseNames().get(courseChoiceIndex + 1);
        
        
//        // Select golfers
//        int golferChoice = -2;
        ArrayList<String> golferChoices = new ArrayList<>();
//        while (golferChoice != -1) {
//            golferChoice = selectGolferMenu(golferChoiceIndexes.size() >= 2);
//            golferChoiceIndexes.add(golferChoice);
//        }
        golferChoices.addAll(league.getGolferNames());
        
        // Place pre-game bets
        System.out.println("Who will win? ($100 bet)");
        for (int i = 0; i < league.getGolferNames().size(); i++) {
            System.out.println(i + " - " + league.getGolferNames().get(i));
        }
        
        int winnerChoice = -1;
        while (1 < winnerChoice && winnerChoice < league.getGolfers().size() + 1) {
            winnerChoice = kboard.nextInt();
        }
        
        // Main game loop
        Game game = league.makeGame(league.getCourseNames().get(courseChoiceIndex),
                golferChoices);
        GameAllPerformance gameAllPerformance = game.playGame();
        for (int i = 0; i < gameAllPerformance.getHoleAllPerformances().size(); i++) {
            // ask for bets
            // print scorecard
            // print bet result + final balance
        }
        
        
    }
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    private int selectGolferMenu(boolean allowZero) {
        int golferChoice = -1;
        int minSelection = 1;
        System.out.println("Select golfer: ");
        if (allowZero) {
            System.out.println("0 - No more golfers!");
            minSelection = 0;
        }
        for (int i = 0; i < league.getCourseNames().size(); i++) {
            System.out.println((i + 1) + " - " + league.getGolferNames().get(i));
        }
        while (!(minSelection <= golferChoice && golferChoice < league.getCourseNames().size() + 1)) {
            golferChoice = kboard.nextInt();
        }
        return golferChoice - 1;
    }
    
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.mainMenu();
    }
}
