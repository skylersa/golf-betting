package ui;

import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import model.gambling.League;
import model.game.Course;
import model.game.Golfer;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.CourseJButton;
import ui.components.CourseJLabel;
import ui.components.GolferJButton;
import ui.components.GolferJCheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GuiManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int X = 300;
    private static final int Y = 100;
    private static final String JSON_STORE = "./data/league.json";
    
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
    
    // Per-menu actionListeners
    private ActionListener mainMenuListener;
    private ActionListener golfersMenuListener;
    private ActionListener coursesMenuListener;
    private ActionListener gameStartMenuListener;
    
    // Main menu JButtons
    private JButton startGameButton;
    private JButton golfersButton;
    private JButton coursesButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    
    // Golfers menu buttons
    private List<GolferJButton> golferButtons;
    private JLabel addGolferLabel;
    private JTextField addGolferTextField;
    private JButton addGolferSubmitButton;
    private JButton returnToMainMenuFromGolfersButton;
    
    // Courses menu buttons
    private JLabel addCourseLabel;
    private JTextField addCourseTextField;
    private JSpinner addCourseSpinner;
    private JButton addCourseSubmitButton;
    private JButton returnToMainMenuFromCoursesButton;
    
    // Start game menu buttons
    private List<GolferJCheckBox> golferCheckBoxes;
    private List<CourseJButton> courseSelectButtons;
    
    private GuiManager() {
        setupLeague();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame();
        totalCard = new CardLayout();
        totalPanel = new JPanel(totalCard);
    }
    
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
        
        totalCard.show(totalPanel, "mainMenu");
        frame.setVisible(true);
    }
    
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
    
    private void setupGolfersMenu() {
        golfersPanel = new JPanel(new GridLayout(1, 2));
        
        golferButtons = new ArrayList<>();
        JPanel golfersViewListPanel = new JPanel(new GridLayout(league.getGolfers().size(), 1));
        
        for (Golfer golfer: league.getGolfers()) {
            GolferJButton button = new GolferJButton(golfer);
            golferButtons.add(button);
            golfersViewListPanel.add(button);
        }
        
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
                    System.exit(16);
                }
            }
        };
        
        addMainMenuButtonsToListener();
    }
    
    private void addMainMenuButtonsToListener() {
        startGameButton.addActionListener(mainMenuListener);
        golfersButton.addActionListener(mainMenuListener);
        coursesButton.addActionListener(mainMenuListener);
        saveButton.addActionListener(mainMenuListener);
        loadButton.addActionListener(mainMenuListener);
        quitButton.addActionListener(mainMenuListener);
    }
    
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
                    league.playGame(courseName, golferNames);
                }
            }
        };
        
        for (JButton b : courseSelectButtons) {
            b.addActionListener(gameStartMenuListener);
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
    
    // EFFECTS: loads instance of this from JSON_STORE
    private void loadThis() {
        try {
            this.league = jsonReader.read();
            System.out.println("Loaded league from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
    public static void main(String[] args) {
        GuiManager gm = new GuiManager();
        gm.setupGui();
    }
}
