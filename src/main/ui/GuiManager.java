package ui;

import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import model.gambling.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GuiManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int X = 300;
    private static final int Y = 100;
    
    private League league;
    
    private JFrame frame;
    private CardLayout totalCard;
    private JPanel totalPanel;
    private JPanel mainMenuPanel;
    private JPanel golfersPanel;
    
    private ActionListener mainMenuListener;
    private ActionListener golfersMenuListener;
    
    private JButton startGameButton;
    private JButton golfersButton;
    private JButton coursesButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    
    List<JButton> golferButtons;
    List<String> golfersNames;
    
    private JLabel addGolferLabel;
    private JTextField addGolferTextField;
    private JButton addGolferSubmitButton;
    
    private GuiManager() {
        setupLeague();
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
    
    private void setupMainMenuListeners() {
        mainMenuListener = new ActionListener() {
            @SuppressWarnings("checkstyle:EmptyBlock") //TODO: REMOVE
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (startGameButton == source) {
                
                } else if (golfersButton == source) {
                    totalCard.show(totalPanel, "golfers");
                    totalPanel.setVisible(true);
                } else if (coursesButton == source) {
                
                } else if (saveButton == source) {
                
                } else if (loadButton == source) {
                
                } else if (quitButton == source) {
                
                }
            }
        };
        
        startGameButton.addActionListener(mainMenuListener);
        golfersButton.addActionListener(mainMenuListener);
        coursesButton.addActionListener(mainMenuListener);
        saveButton.addActionListener(mainMenuListener);
        loadButton.addActionListener(mainMenuListener);
        quitButton.addActionListener(mainMenuListener);
    }
    
    private void setupGolfersMenu() {
        golfersPanel = new JPanel(new GridLayout(1, 2));
        golfersNames = league.getGolferNames();
        golferButtons = new ArrayList<>();
//        JPanel golfersViewListPanel = new JPanel(new BoxLayout(golfersPanel, BoxLayout.X_AXIS));
        JPanel golfersViewListPanel = new JPanel(new GridLayout(golfersNames.size(), 1));
        for (String name : golfersNames) {
            JButton button = new JButton(name);
            golferButtons.add(button);
            golfersViewListPanel.add(button);
        }
        
        JPanel addGolferPanel = new JPanel(new GridLayout(3, 1));
        addGolferLabel = new JLabel("What is their name?");
        addGolferTextField = new JTextField();
        addGolferSubmitButton = new JButton("Add golfer");
        
        addGolferPanel.add(addGolferLabel);
        addGolferPanel.add(addGolferTextField);
        addGolferPanel.add(addGolferSubmitButton);
        
        golfersPanel.add(golfersViewListPanel);
        golfersPanel.add(addGolferPanel);
    
        totalPanel.add("golfers", golfersPanel);
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
                }
            }
        };
        
        addGolferSubmitButton.addActionListener(golfersMenuListener);
    }
    
    public static void main(String[] args) {
        GuiManager gm = new GuiManager();
        gm.setupGui();
    }
}
