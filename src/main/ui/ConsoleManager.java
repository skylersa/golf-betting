package ui;

import exceptions.NegativeBetException;
import exceptions.RepeatGolferException;
import exceptions.RepeatCourseException;
import model.gambling.League;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/*
 * CONTAINS MAIN METHOD
 * Represents and manages the console-based ui for this program
 */
public class ConsoleManager {
    private static ConsoleManager consoleManager;
    private static ScoreCardPrinter scoreCardPrinter;
    private static final String JSON_STORE = "./data/league.json";
    
    private Scanner kboard = new Scanner(System.in).useDelimiter("\n");
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    private League league;
    
    // EFFECTS: creates new consoleManager with a league
    private ConsoleManager() {
        this.league = new League();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        
        try {
            league.addGolfer("Bob Odenkirk");
            league.addGolfer("Bryan Cranston");
            league.addGolfer("Aaron Paul");
            league.addGolfer("Billiam Zero");
        } catch (RepeatGolferException e) {
            throw new Error(e);
        }
        try {
            league.addCourse("WestField Golf", 4);
            league.addCourse("Albuquerque's finest", 3);
            league.addCourse("Tiny Golf!", 1);
        } catch (RepeatCourseException e) {
            throw new Error(e);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: starts console interface with main menu, initiating full game flow
    private void mainMenu() {
        List<String> options = new ArrayList<>();
        options.add("Start Game!");
        options.add("View or add golfers");
        options.add("View or add course");
        options.add("Save, Load, or Quit");
        
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
            case "Save, Load, or Quit":
                saveLoadQuitMenu();
                break;
        }
    }
    
    // EFFECTS: takes user to the options to save, load, and quit the program
    private void saveLoadQuitMenu() {
        List<String> options = new ArrayList<>();
        options.add("Save");
        options.add("Load");
        options.add("Quit");
    
        switch (selectFromListMenu(options)) {
            case "Save":
                saveThis();
                mainMenu();
                break;
            case "Load":
                loadThis();
                mainMenu();
                break;
            case "Quit":
                System.exit(15);
                break;
        }
    }
    
    // EFFECTS: saves instance of this to JSON_STORE
    private void saveThis() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.league);
            jsonWriter.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Could find file " + JSON_STORE);
        }
    }
    
    // EFFECTS: loads instance of this from JSON_STORE
    private void loadThis() {
        try {
            this.league = jsonReader.read();
            System.out.println("Loaded league from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
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
        System.out.println("How many holes does it have?");
        
        while (!(inNumHoles > 0)) {
            
            try {
                inNumHoles = Integer.parseInt(kboard.next());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive number");
                inNumHoles = -1;
            }
        }
        
        try {
            league.addCourse(inName, inNumHoles);
            coursesMenu();
        } catch (RepeatCourseException e) {
            System.out.println(e.getMessage());
            addCourseMenu();
        }
        
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
        System.out.println(golferName + "'s stats\n");
        for (GameGolferPerformance performance : league.getGolferHistory(golferName)) {
            scoreCardPrinter.printScoreCard(performance, true);
            System.out.println();
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
        GameAllPerformance gap = league.playGame(courseChoice, golferChoices);
        
        for (Hole hole : gap.getHoles()) {
            System.out.println("Par at this hole is "
                    + hole.getPar()
                    + ". Who will win this hole?");
            String holeWinnerChoice = selectFromListMenu(league.getGolferNames());
            
            int holeWinnerBetAmount = takeBetAmount();
    
            scoreCardPrinter.printScoreCard(gap.getHolePerformance(hole));
            
            boolean won = holeWinnerChoice.equals(gap.getBestPerformingGolfer().getName());
            settleBet(won, holeWinnerBetAmount);
        }
        
        scoreCardPrinter.printScoreCard(gap);
        System.out.println("ENTER to continue");
        kboard.next();
        
        return winnerChoice.equals(gap.getBestPerformingGolfer().getName());
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
        System.out.println("Your balance was $" + league.getGambler().getBalance());
        league.getGambler().bet(won, betAmount);
        if (won) {
            System.out.println("You won $" + betAmount);
        } else {
            System.out.println("You lost $" + betAmount);
        }
        
        if (league.getGambler().getBalance() <= 0) {
            System.out.println("You're out of money! Game over.");
            System.exit(1);
        }
        
        System.out.println("Your new balance is $" + league.getGambler().getBalance() + "\nENTER to continue");
        kboard.next();
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
        consoleManager = new ConsoleManager();
        scoreCardPrinter = new ScoreCardPrinter();
        consoleManager.mainMenu();
    }
}
