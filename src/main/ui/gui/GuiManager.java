package ui.gui;

import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import logging.Event;
import logging.EventLog;
import model.gambling.League;
import model.game.Course;
import model.game.Golfer;
import model.performance.GameAllPerformance;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/*
 * Setups the gui manager, listens for events and reacts accordingly
 */
public class GuiManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int X = 300;
    private static final int Y = 100;
    private static final String JSON_STORE = "./data/league.json";
    private static final int DEFAULT_BET = 100;
    
    // Game operation fields
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    // High level fame operation fields
    private JFrame frame;
    private CardLayout totalCard;
    private JPanel totalPanel;
    private JPanel mainMenuPanel;
    private JPanel golfersPanel;
    private JPanel coursesPanel;
    private JPanel gameStartPanel;
    private JPanel gameFlowPanel;
    private JPanel gameOverPanel;
    
    // Per-menu actionListeners
    private ActionListener mainMenuListener;
    private ActionListener golfersMenuListener;
    private ActionListener coursesMenuListener;
    private ActionListener gameStartMenuListener;
    private ActionListener gameFlowMenuListener;
    
    // Main menu pieces
    private JButton startGameButton;
    private JButton golfersButton;
    private JButton coursesButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    
    // Golfers menu pieces
    private JLabel addGolferLabel;
    private JTextField addGolferTextField;
    private JButton addGolferSubmitButton;
    private JButton returnToMainMenuFromGolfersButton;
    
    // Courses menu pieces
    private JLabel addCourseLabel;
    private JTextField addCourseTextField;
    private JSpinner addCourseSpinner;
    private JButton addCourseSubmitButton;
    private JButton returnToMainMenuFromCoursesButton;
    
    // Game Start menu pieces
    private List<GolferJCheckBox> golferCheckBoxes;
    private List<CourseJButton> courseSelectButtons;
    
    // Game Over menu pieces
    private JButton gameOverQuitButton;
    
    // Game flow menu pieces
    private GolferListButtonJPanel golferButtonsPanel;
    private GamePerformancePanel flowPerformancePanel;
    
    
    // MODIFIES: this
    // EFFECTS: creates a new GuiManager with the highest level JPanel and CardLayout
    private GuiManager() {
        setupLeague();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame();
        totalCard = new CardLayout();
        totalPanel = new JPanel(totalCard);
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the league with some default golfers and courses
    private void setupLeague() {
        league = new League();
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
    // EFFECTS: sets up primary gui elements
    private void setupGui() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Skyler's Golfer Betting");
        frame.setBounds(X, Y, WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(totalPanel);
        
        setupMainMenu();
        setupGolfersMenu();
        setupCoursesMenu();
        setupGameOverMenu();
        
        totalCard.show(totalPanel, "mainMenu");
        frame.setVisible(true);
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the JPanel for the main menu, adds action listener and adds itself to the totalPanel
    private void setupMainMenu() {
        mainMenuPanel = new JPanel(new GridLayout(6, 1));
        startGameButton = new JButton("Start Game!");
        golfersButton = new JButton("View or add golfers");
        coursesButton = new JButton("View or add course");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");
        
        setupMainMenuListeners();
        
        mainMenuPanel.add(startGameButton);
        mainMenuPanel.add(golfersButton);
        mainMenuPanel.add(coursesButton);
        mainMenuPanel.add(saveButton);
        mainMenuPanel.add(loadButton);
        mainMenuPanel.add(quitButton);
        
        totalPanel.add("mainMenu", mainMenuPanel);
        
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the JPanel for the golfers menu, adds action listener and adds itself to the totalPanel
    private void setupGolfersMenu() {
        golfersPanel = new JPanel(new GridLayout(1, 2));
        
        GolferListButtonJPanel golfersViewListPanel = new GolferListButtonJPanel(league.getGolfers());
        
        JPanel addGolferPanel = new JPanel(new GridLayout(4, 1));
        addGolferLabel = new JLabel("What is their name?");
        addGolferTextField = new JTextField();
        addGolferSubmitButton = new JButton("Add golfer");
        returnToMainMenuFromGolfersButton = new JButton("Main menu");
        
        addGolferPanel.add(returnToMainMenuFromGolfersButton);
        addGolferPanel.add(addGolferLabel);
        addGolferPanel.add(addGolferTextField);
        addGolferPanel.add(addGolferSubmitButton);
        
        golfersPanel.add(golfersViewListPanel);
        golfersPanel.add(addGolferPanel);
        
        totalPanel.add("golfers", golfersPanel);
        setupGolfersMenuListeners();
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the JPanel for the courses menu, adds action listener and adds itself to the totalPanel
    private void setupCoursesMenu() {
        coursesPanel = new JPanel(new GridLayout(1, 2));
        
        JPanel coursesViewListPanel = new JPanel(new GridLayout(league.getCourses().size(), 1));
        
        for (Course course : league.getCourses()) {
            coursesViewListPanel.add(new CourseJLabel(course));
        }
        
        JPanel addCoursePanel = new JPanel(new GridLayout(5, 1));
        addCourseLabel = new JLabel("What is its name?");
        addCourseTextField = new JTextField();
        addCourseSpinner = new JSpinner();
        addCourseSubmitButton = new JButton("Add course");
        returnToMainMenuFromCoursesButton = new JButton("Main menu");
        
        addCoursePanel.add(addCourseLabel);
        addCoursePanel.add(addCourseTextField);
        addCoursePanel.add(addCourseSpinner);
        addCoursePanel.add(addCourseSubmitButton);
        addCoursePanel.add(returnToMainMenuFromCoursesButton);
        
        coursesPanel.add(coursesViewListPanel);
        coursesPanel.add(addCoursePanel);
        
        totalPanel.add("courses", coursesPanel);
        setupCoursesMenuListeners();
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the JPanel for the game start menu, adds action listener and adds itself to the totalPanel
    private void setupGameStartMenu() {
        gameStartPanel = new JPanel(new GridLayout(1, 2));
        JPanel golfersSelectListPanel = new JPanel(new GridLayout(league.getGolfers().size(), 1));
        JPanel coursesSelectListPanel = new JPanel(new GridLayout(league.getCourses().size(), 1));
        golferCheckBoxes = new ArrayList<>();
        courseSelectButtons = new ArrayList<>();
        
        for (Golfer golfer : league.getGolfers()) {
            GolferJCheckBox checkBox = new GolferJCheckBox(golfer);
            checkBox.setSelected(true);
            golferCheckBoxes.add(checkBox);
            golfersSelectListPanel.add(checkBox);
        }
        
        for (Course course : league.getCourses()) {
            CourseJButton button = new CourseJButton(course);
            courseSelectButtons.add(button);
            coursesSelectListPanel.add(button);
        }
        
        gameStartPanel.add(golfersSelectListPanel);
        gameStartPanel.add(coursesSelectListPanel);
        
        
        totalPanel.add("gameStart", gameStartPanel);
        setupGameStartMenuListeners();
    }
    
    // MODIFIES: this
    // EFFECTS: sets up the JPanel for the game over menu, adds action listener and adds itself to the totalPanel
    private void setupGameOverMenu() {
        gameOverPanel = new JPanel();
        gameOverPanel.add(new JLabel("You ran out of Money!"));
        gameOverQuitButton = new JButton("Quit");
        gameOverPanel.add(gameOverQuitButton);
        
        gameOverQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-15);
            }
        });
        totalPanel.add("gameOver", gameOverPanel);
    }
    
    // MODIFIES: this
    // EFFECTS: sets up a JPanel for a game flow, adds action listener and adds itself to the totalPanel
    private void gameFlowMenu(GameAllPerformance gap) {
        gameFlowPanel = new JPanel(new GridLayout(1, 3));
        List<Golfer> golfers = gap.getGolfers();
        
        golferButtonsPanel = new GolferListButtonJPanel(golfers);
        
        flowPerformancePanel = new GamePerformancePanel(gap);
        
        gameFlowPanel.add(golferButtonsPanel);
        gameFlowPanel.add(flowPerformancePanel);
        try {
            gameFlowPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("./data/golfer.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        totalPanel.add("gameFlow", gameFlowPanel);
        setupGameFlowMenuListeners();
        
        totalCard.show(totalPanel, "gameFlow");
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: creates action listener for the main menu
    private void setupMainMenuListeners() {
        mainMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (startGameButton == source) {
                    setupGameStartMenu();
                    totalCard.show(totalPanel, "gameStart");
                } else if (golfersButton == source) {
                    totalCard.show(totalPanel, "golfers");
                } else if (coursesButton == source) {
                    totalCard.show(totalPanel, "courses");
                } else if (saveButton == source) {
                    saveThis(); // TODO: add message
                } else if (loadButton == source) {
                    loadThis(); // TODO: add message
                } else if (quitButton == source) {
                    exitThis();
                }
            }
        };
        addMainMenuButtonsToListener();
    }
    
    // MODIFIES: this
    // EFFECTS: prints current EventLog and shuts dow application
    private void exitThis() {
        for (Event ev : EventLog.getInstance()) {
            System.out.println(ev.getDescription());
        }
        System.exit(16);
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: adds main menu buttons to action listener
    private void addMainMenuButtonsToListener() {
        startGameButton.addActionListener(mainMenuListener);
        golfersButton.addActionListener(mainMenuListener);
        coursesButton.addActionListener(mainMenuListener);
        saveButton.addActionListener(mainMenuListener);
        loadButton.addActionListener(mainMenuListener);
        quitButton.addActionListener(mainMenuListener);
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: creates action listener for the golfers menu
    private void setupGolfersMenuListeners() {
        golfersMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (addGolferSubmitButton == source) {
                    try {
                        league.addGolfer(addGolferTextField.getText());
                        setupGolfersMenu();
                        totalCard.show(totalPanel, "golfers");
                    } catch (RepeatGolferException | InputMismatchException ignored) {
                        // simply continue
                    }
                } else if (returnToMainMenuFromGolfersButton == source) {
                    totalCard.show(totalPanel, "mainMenu");
                }
            }
        };
        
        addGolferSubmitButton.addActionListener(golfersMenuListener);
        returnToMainMenuFromGolfersButton.addActionListener(golfersMenuListener);
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: creates action listener for the courses menu
    private void setupCoursesMenuListeners() {
        coursesMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (addCourseSubmitButton == source) {
                    try {
                        league.addCourse(addCourseTextField.getText(), (int) addCourseSpinner.getValue());
                        setupCoursesMenu();
                        totalCard.show(totalPanel, "courses");
                    } catch (RepeatCourseException | InputMismatchException ignored) {
                        //  continue
                    }
                } else if (returnToMainMenuFromCoursesButton == source) {
                    totalCard.show(totalPanel, "mainMenu");
                }
            }
        };
        
        addCourseSubmitButton.addActionListener(coursesMenuListener);
        returnToMainMenuFromCoursesButton.addActionListener(coursesMenuListener);
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: creates action listener for the game start menu
    private void setupGameStartMenuListeners() {
        gameStartMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                
                if (courseSelectButtons.contains((CourseJButton) source)) {
                    String courseName = ((CourseJButton) source).getCourse().getName();
                    List<String> golferNames = new ArrayList<>();
                    for (GolferJCheckBox cb : golferCheckBoxes) {
                        if (cb.isSelected()) {
                            golferNames.add(cb.getGolfer().getName());
                        }
                    }
                    gameFlowMenu(league.playGame(courseName, golferNames));
                }
            }
        };
        
        for (JButton b : courseSelectButtons) {
            b.addActionListener(gameStartMenuListener);
        }
    }
    
    // REQUIRES: buttons are not null
    // MODIFIES: this
    // EFFECTS: creates action listener for the current game flow menu
    private void setupGameFlowMenuListeners() {
        gameFlowMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                
                if (golferButtonsPanel.getButtons().contains((GolferJButton) source)) {
                    String golferName = ((GolferJButton) source).getGolfer().getName();
                    
                    bet(flowPerformancePanel.getGap().getBestPerformingGolfer() == league.getGolfer(golferName));
                    if (flowPerformancePanel.next()) {
                        totalCard.show(totalPanel, "mainMenu");
                    }
                }
            }
        };
        for (GolferJButton b : golferButtonsPanel.getButtons()) {
            b.addActionListener(gameFlowMenuListener);
        }
    }
    
    // EFFECTS: saves instance of this to JSON_STORE
    private void saveThis() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.league);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could find file " + JSON_STORE);
        }
    }
    
    // EFFECTS: loads instance of this from JSON_STORE, reloads GUI
    private void loadThis() {
        try {
            this.league = jsonReader.read();
            setupGui();
            System.out.println("Loaded league from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: bets the default amount with the gambler
    //    if the gambler's balance drops below 0, ends the game
    private void bet(boolean won) {
        this.league.getGambler().bet(won, DEFAULT_BET);
        if (league.getGambler().getBalance() <= 0) {
            totalCard.show(totalPanel, "gameOver");
        }
    }
    
    // MODIFIES: gm
    // EFFECTS: sets up the gui and prepares it to be operated by the user
    public static void main(String[] args) {
        GuiManager gm = new GuiManager();
        gm.setupGui();
    }
}
