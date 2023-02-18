package ui;

import model.gambling.Gambler;
import model.game.Game;
import model.gambling.League;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;
import model.performance.HoleGolferPerformance;
import ui.exceptions.NegativeBetException;
import ui.exceptions.RepeatGolferException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.*;

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
        
        try {
            league.addGolfer("Bob Odenkirk");
            league.addGolfer("Bryan Cranston");
            league.addGolfer("Aaron Paul");
            league.addGolfer("Billiam Zero");
        } catch (RepeatGolferException e) {
            throw new Error(e);
        }
        league.addCourse("WestField Golf", 4);
        league.addCourse("Albuquerque's finest", 3);
        league.addCourse("Tiny Golf!", 1);
    }
    
    // MODIFIES: this
    // EFFECTS: starts console interface with main menu, initiating full game flow
    private void mainMenu() {
        List<String> options = new ArrayList<>();
        options.add("Start Game!");
        options.add("View or add golfers");
        options.add("View or add course");
        
        System.out.println("Main menu");
        switch (selectFromListMenu(options)) {
            case "Start Game!":
                startGameFlow();
                break;
            case "View or add golfers":
                golfersMenu();
                break;
            case "View or add course":
                coursesMenu();
                break;
        }
    }
    
    // MODIFIES: this
    // EFFECTS: lets user view and add courses
    private void coursesMenu() {
        List<String> options = new ArrayList<>();
        options.add("Add a course");
        options.add("Return to main menu");
        
        printList(league.getCourseNames());
        
        switch (selectFromListMenu(options)) {
            case "Add a course":
                addCourseMenu();
                break;
            case "Return to main menu":
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
            System.out.println("How many holes does it have?");
            
            try {
                inNumHoles = kboard.nextInt();
            } catch (Exception e) {
                System.out.println("How many holes does it have?");
            }
        }
        
        league.addCourse(inName, inNumHoles);
        
        coursesMenu();
    }
    
    // MODIFIES: this
    // EFFECTS: lets user select golfers to view more in-depth as well as add golfers
    private void golfersMenu() {
        List<String> options = new ArrayList<>();
        options.add("Add a golfer");
        options.add("View a golfer's stats");
        options.add("Return to main menu");
        
        switch (selectFromListMenu(options)) {
            case "Add a golfer":
                addGolferMenu();
                break;
            case "View a golfer's stats":
                viewGolferMenu(selectFromListMenu(league.getGolferNames()));
                break;
            case "Return to main menu":
                mainMenu();
        }
    }
    
    // MODIFIES: this
    // EFFECTS: adds a golfer of user's choice name, sends back to golfersMenu
    private void addGolferMenu() {
        System.out.println("What's their name?");
        try {
            league.addGolfer(kboard.next());
            golfersMenu();
        } catch (RepeatGolferException e) {
            System.out.println("Please choose a unique name");
            addGolferMenu();
        }
    }
    
    // REQUIRES: given golfer is in league
    // EFFECTS: prints a golfer's scorecard in each of their games
    private void viewGolferMenu(String golferName) {
        System.out.println(golferName + "'s stats");
        for (GameGolferPerformance performance : league.getGolfer(golferName).getPerformanceHistory()) {
            System.out.println("=================================");
            printScoreCard(performance);
        }
        System.out.println("\nENTER to continue");
        kboard.next();
        golfersMenu();
    }
    
    // MODIFIES: this
    // EFFECTS: begins flow of a golf game
    private void startGameFlow() {
        // Select a course
        System.out.println("Select a course:");
        String courseChoice = selectFromListMenu(league.getCourseNames());
        
        // Place pre-game bets
        System.out.println("Who will win?");
        String gameWinnerBetChoice = selectFromListMenu(league.getGolferNames());
        int winnerChoiceBetAmount = takeBetAmount();
        
        boolean won = mainGameLoop(courseChoice, league.getGolferNames(), gameWinnerBetChoice);
        
        settleBet(won,winnerChoiceBetAmount);
        mainMenu();
    }
    
    // REQUIRES: courseChoice is in this league
    //           golferChoices has >= 2 golfers in it
    //           winnerChoice must be in golferChoices
    // MODIFIES: this
    // EFFECTS: operates main game loop, take bets and play hole for the result of those bets
    //          updates gambler's balance accordingly
    //          returns true if the winner of the game is winnerChoice, otherwise false
    private boolean mainGameLoop(String courseChoice, List<String> golferChoices, String winnerChoice) {
        Game game = league.makeGame(courseChoice, golferChoices);
        GameAllPerformance gameAllPerformance = game.playGame();
        
        for (HoleAllPerformance performance : gameAllPerformance.getHoleAllPerformances()) {
            System.out.println("Par at this hole is "
                    + performance.getHole().getPar()
                    + ". Who will do best on this hole?");
            String holeWinnerChoice = selectFromListMenu(league.getGolferNames());
            
            int holeWinnerBetAmount = takeBetAmount();
    
            printScoreCard(performance);
            
            boolean won = holeWinnerChoice.equals(performance.getBestPerformingGolfer().getName());
            settleBet(won, holeWinnerBetAmount);
        }
        
        printScoreCard(gameAllPerformance);
        System.out.println("ENTER to continue");
        kboard.next();
        
        return winnerChoice.equals(gameAllPerformance.getBestPerformingGolfer().getName());
    }
    
    // EFFECTS: prints "How much you wanna bet?" and returns entered bet amount
    //    returned amount is always non-negative, not always within player budget
    private int takeBetAmount() {
        System.out.println("How much you wanna bet?");
        int betAmount;
        while (true) {
            try {
                betAmount = parseInt(kboard.next());
                if (betAmount < 0) {
                    throw new NegativeBetException();
                }
            
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number:");
                betAmount = -1;
            } catch (NegativeBetException e) {
                System.out.println("Please enter a positive number");
                betAmount = -1;
            }
        }
        return betAmount;
    }
    
    
    // REQUIRES: betAmount >= 0
    // MODIFIES: this
    // EFFECTS: changes gambler placing according to bet performance, print results to console
    private void settleBet(boolean won, int betAmount) {
        System.out.println("Your balance was $" + gambler.getBalance());
        gambler.bet(won, betAmount);
        if (won) {
            System.out.println("You won $" + betAmount);
        } else {
            System.out.println("You lost $" + betAmount);
        }
        
        if (gambler.getBalance() <= 0) {
            System.out.println("You're out of money! Game over.");
            System.exit(1);
        }
        
        System.out.println("Your new balance is $" + gambler.getBalance() + "\nENTER to continue");
        kboard.next();
    }
    
    // REQUIRES: gameAllPerformance is full of GameGolferPerformances and HoleAllPerformances
    // EFFECTS: prints out a scorecard of the game, players down the side and holes across the top
    private void printScoreCard(GameAllPerformance gameAllPerformance) {
        String spacer = "  ";
        StringBuilder parHeader = new StringBuilder("PAR:    ");
        List<StringBuilder> strokesBody = new ArrayList<>();
    
        for (Hole hole : gameAllPerformance.getGame().getCourse().getHoles()) {
            parHeader.append(spacer).append(hole.getPar());
        }
        for (GameGolferPerformance gameGolferPerformance : gameAllPerformance.getGameGolferPerformances()) {
            StringBuilder currentHoleLine = new StringBuilder(gameGolferPerformance.getGolfer().getName());
            for (HoleGolferPerformance holeGolferPerformance : gameGolferPerformance.getHoleGolferPerformances()) {
                currentHoleLine.append(spacer).append(holeGolferPerformance.getStrokes());
            }
            strokesBody.add(currentHoleLine);
        }
    
        System.out.println(parHeader);
        for (StringBuilder strokesBodyPart : strokesBody) {
            System.out.println(strokesBodyPart);
        }
    }
    
    
    // REQUIRES: performance is full of HoleGolferPerformances
    // EFFECTS: prints out a scorecard of given performance horizontally
    private void printScoreCard(GameGolferPerformance performance) {
        String spacer = "  ";
        StringBuilder parHeader = new StringBuilder("PAR:    ");
        StringBuilder strokesBody = new StringBuilder("STROKES:");
        
        for (HoleGolferPerformance eachPerformance : performance.getHoleGolferPerformances()) {
            parHeader.append(spacer).append(eachPerformance.getHole().getPar());
            strokesBody.append(spacer).append(eachPerformance.getStrokes());
        }
        
        System.out.println("Game on " + performance.getGame().getCourse().getName());
        System.out.println(parHeader);
        System.out.println(strokesBody);
    }
    
    // REQUIRES: performance is full of HoleGolferPerformances
    // EFFECTS: prints out a scorecard of given performance vertically
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
    private String selectFromListMenu(List<String> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + " - " + list.get(i - 1));
        }
        
        int choice = -1;
        while (true) {
            try {
                choice = parseInt(kboard.next()) - 1;
                if (!(0 <= choice && choice < list.size())) {
                    throw new IndexOutOfBoundsException();
                }
                
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                choice = -1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a number on the list");
                choice = -1;
            }
        }
        
        return list.get(choice);
    }
    
    // EFFECTS: prints list with bullet points
    private void printList(List<String> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(" - " + list.get(i - 1));
        }
    }
    
    // EFFECTS: Begins game at main menu
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.mainMenu();
    }
}
