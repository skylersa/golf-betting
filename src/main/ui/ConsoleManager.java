package ui;

import model.gambling.Gambler;
import model.game.Course;
import model.game.Game;
import model.game.League;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;
import model.performance.HoleGolferPerformance;

import java.util.ArrayList;
import java.util.List;
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
    
    // MODIFIES: this
    // EFFECTS: starts console interface with main menu, initiating full game flow
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
    
    // MODIFIES: this
    // EFFECTS: lets user view and add courses
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
    
    // MODIFIES: this
    // EFFECTS: lets user add a course with a name and # of holes
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
    
    // MODIFIES: this
    // EFFECTS: lets user select golfers to view more in-depth as well as add golfers
    private void golfersMenu() {
        System.out.println("1 - Add a golfer\n2 - View a golfer's stats\n3 - Return to main menu");
        int choice = kboard.nextInt();
        switch (choice) {
            case 1:
                addCourseMenu();
                break;
            case 2:
                viewGolferMenu(selectMenu(league.getGolferNames()));
                break;
            default:
                mainMenu();
        }
    }
    
    // REQUIRES: given golfer is in league
    // EFFECTS: prints a golfer's scorecard in each of their games
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
        kboard.next();
    }
    
    // MODIFIES: this
    // EFFECTS: begins flow of a golf game
    private void startGameFlow() {
        // Select a course
        System.out.println("Select a course:");
        String courseChoice = selectMenu(league.getCourseNames());
        
        // Select golfers
//        int golferChoice = -2;
        ArrayList<String> golferChoices = new ArrayList<>();
//        while (golferChoice != -1) {
//            golferChoice = selectGolferMenu(golferChoiceIndexes.size() >= 2);
//            golferChoiceIndexes.add(golferChoice);
//        }
        golferChoices.addAll(league.getGolferNames());
        
        // Place pre-game bets
        System.out.println("Who will win? ($100 bet)"); //TODO make variable
        selectMenu(league.getGolferNames());
        
        int winnerChoice = -1;
        while (1 < winnerChoice && winnerChoice < league.getGolfers().size() + 1) {
            winnerChoice = kboard.nextInt();
        }
        
        mainGameLoop(courseChoice, golferChoices);
    }
    
    private void mainGameLoop(String courseChoice, ArrayList<String> golferChoices) {
        // Main game loop
        Game game = league.makeGame(courseChoice,
                golferChoices);
        GameAllPerformance gameAllPerformance = game.playGame();
        for (HoleAllPerformance performance : gameAllPerformance.getHoleAllPerformances()) {
            System.out.println("Par is " + performance.getHole().getPar() + ". Who will do best on this hole?");
            String holeWinnerChoice = selectMenu(league.getGolferNames());
            
            System.out.println("How much you wanna bet?");
            int holeWinnerBetAmount = kboard.nextInt();
            
            printScoreCard(performance);
            
            boolean won = holeWinnerChoice.equals(performance.getBestPerformingGolfer().getName());
            System.out.println("Your balance was $" + gambler.getBalance());
            gambler.bet(holeWinnerBetAmount, won);
            if (won) {
                System.out.println("You won $" + holeWinnerBetAmount);
            } else {
                System.out.println("You lost $" + holeWinnerBetAmount);
            }
            System.out.println("Your new balance is $" + gambler.getBalance() + "\nENTER TO CONTINUE");
            kboard.next();
        }
    }
    
    // REQUIRES: performance is full of HoleGolferPerformances
    // EFFECTS: prints out a scorecard of given
    private void printScoreCard(HoleAllPerformance performance) {
        String spacer = "   ";
        System.out.println("STROKES" + spacer + spacer + "GOLFER");
        for (HoleGolferPerformance eachPerformance : performance.getHoleGolferPerformances()) {
            System.out.println(spacer + eachPerformance.getStrokes()
                    + ".........." + eachPerformance.getGolfer().getName());
        }
        
    }
    
    // REQUIRES: List has at least one element
    // EFFECTS: prints a menu of the list and asks the user to select an option, returns the selected object
    private String selectMenu(List<String> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + " - " + list.get(i - 1));
        }
        // TODO: add bad input handling
        return list.get(kboard.nextInt() - 1);
    }

//    // REQUIRES: //TODO
//    // MODIFIES:
//    // EFFECTS:
//    private int selectGolferMenu(boolean allowZero) {
//        int golferChoice = -1;
//        int minSelection = 1;
//        System.out.println("Select golfer: ");
//        if (allowZero) {
//            System.out.println("0 - No more golfers!");
//            minSelection = 0;
//        }
//        for (int i = 0; i < league.getCourseNames().size(); i++) {
//            System.out.println((i + 1) + " - " + league.getGolferNames().get(i));
//        }
//        while (!(minSelection <= golferChoice && golferChoice < league.getCourseNames().size() + 1)) {
//            golferChoice = kboard.nextInt();
//        }
//        return golferChoice - 1;
//    }
    
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.mainMenu();
    }
}
