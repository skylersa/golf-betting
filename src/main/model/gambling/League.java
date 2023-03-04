package model.gambling;

import exceptions.GolferNotPresentException;
import exceptions.RepeatGolferException;
import model.game.Course;
import model.game.Game;
import model.game.Golfer;
import model.performance.GameAllPerformance;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a league of golfers and course that they can play on and their histories of perforances.
 * Gives a friendlier manner with which to interact
 *th the various list of things (using strings)
 */
public class League implements Writable {
    private List<Golfer> golfers;
    private List<Course> courses;
    private final Gambler gambler;
    
    private List<GameAllPerformance> performances;
    
    
    // EFFECTS: new league with no players or courses
    public League() {
        this.golfers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.gambler = new Gambler();
        this.performances = new ArrayList<>();
    }
    
    // REQUIRES: name is unique
    // MODIFIES: this
    // EFFECTS: adds golfer with given name
    public void addGolfer(String name) throws RepeatGolferException {
        if (getGolferNames().contains(name)) {
            throw new RepeatGolferException(name);
        } else {
            this.golfers.add(new Golfer(name));
        }
    }
    
    // REQUIRES: name is unique, numHoles > 0
    // MODIFIES: this
    // EFFECTS: adds course with given name and number of holes
    public void addCourse(String name, int numHoles) {
        this.courses.add(new Course(name, numHoles));
    }
    
    // REQUIRES: golferNames have been added as golfers
    //           courseName has been added as course
    // EFFECTS: return a game with the given golfers on the given course
    //          does not play the game
    public GameAllPerformance playGame(String courseName, List<String> golferNames) {
        Course courseToAdd = null;
        
        for (Course course : this.courses) {
            if (course.getName().equals(courseName)) {
                courseToAdd = course;
            }
        }
        if (courseToAdd == null) {
            throw new NullPointerException();
        }
        
        
        List<Golfer> golfersToAdd = new ArrayList<>();
        for (String golferName : golferNames) {
            golfersToAdd.add(this.getGolfer(golferName));
        }
        if (golferNames.size() != golfersToAdd.size()) {
            throw new GolferNotPresentException();
        }
        
        Game game = new Game(courseToAdd, golfersToAdd);
        GameAllPerformance gameAllPerformance = game.playGame();
        
        performances.add(gameAllPerformance);
        return gameAllPerformance;
    }
    
    
    // EFFECTS: returns all stored game performances for given golfer
    public List<GameGolferPerformance> getGolferHistory(String golferName) {
        Golfer golfer = this.getGolfer(golferName);
        
        List<GameGolferPerformance> result = new ArrayList<>();
        for (GameAllPerformance gameAllPerformance : performances) {
            for (GameGolferPerformance gameGolferPerformance : gameAllPerformance.getGameGolferPerformances()) {
                if (gameGolferPerformance.getGolfer() == golfer) {
                    result.add(gameGolferPerformance);
                }
            }
        }
        
        return result;
    }
    
    // REQUIRES: given golfer is in this league
    // EFFECTS: returns the Golfer of given name
    public Golfer getGolfer(String name) {
        try {
            return this.golfers.get(this.getGolferNames().indexOf(name));
        } catch (IndexOutOfBoundsException e) {
            throw new GolferNotPresentException(name, e);
        }
    }
    
    
    // REQUIRES: given course is in this league
    // EFFECTS: returns the Course of given name
    public Course getCourse(String name) {
        return this.courses.get(this.getCourseNames().indexOf(name));
    }
    
    // EFFECTS: return the names of the courses in the league
    public List<String> getCourseNames() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : this.courses) {
            courseNames.add(course.getName());
        }
        return courseNames;
    }
    
    // EFFECTS: return the names of the golfers in the league
    public List<String> getGolferNames() {
        List<String> golferNames = new ArrayList<>();
        for (Golfer golfer : this.golfers) {
            golferNames.add(golfer.getName());
        }
        return golferNames;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    public Gambler getGambler() {
        return gambler;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray golfersJson = new JSONArray();
        JSONArray coursesJson = new JSONArray();
        JSONArray performancesJson = new JSONArray();
        
        for (Golfer golfer : this.golfers) {
            golfersJson.put(golfer.toJson());
        }
        
        for (Course course : this.courses) {
            coursesJson.put(course.toJson());
        }
        
        for (GameAllPerformance gap : this.performances) {
            performancesJson.put(gap.toJson());
    
        }
        
        JSONObject json = new JSONObject();
        json.put("golfers", golfersJson);
        json.put("courses", coursesJson);
        json.put("performances", performancesJson);
        json.put("gambler", gambler.toJson());
        
        return json;
    }
}
