package model.gambling;

import exceptions.CourseNotPresentException;
import exceptions.GolferNotPresentException;
import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import logging.Event;
import logging.EventLog;
import model.game.Course;
import model.game.Golfer;
import model.game.Hole;
import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Represents a league of golfers, course that they can play on and their histories of perforances.
 * Gives a friendlier manner with which to interact
 *th the various list of things (using strings)
 */
public class League implements Writable {
    private List<Golfer> golfers; //todo change to maps
    private List<Course> courses;
    private Gambler gambler;
    
    private List<GameAllPerformance> performances;
    
    
    // EFFECTS: new league with no players or courses, logs
    public League() {
        EventLog.getInstance().logEvent(new Event("Made new league"));
        this.golfers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.gambler = new Gambler();
        this.performances = new ArrayList<>();
    }
    
    // REQUIRES: name is unique
    // MODIFIES: this
    // EFFECTS: adds golfer with given name, logs
    public void addGolfer(String name) throws RepeatGolferException {
        if (getGolferNames().contains(name)) {
            throw new RepeatGolferException(name);
        } else if (name.length() == 0) {
            throw new InputMismatchException();
        } else {
            EventLog.getInstance().logEvent(new Event("Added new golfer: " + name));
            this.golfers.add(new Golfer(name));
        }
    }
    
    // REQUIRES: name is unique, numHoles > 0
    // MODIFIES: this
    // EFFECTS: adds course with given name and number of holes, logs
    public void addCourse(String name, int numHoles) throws RepeatCourseException {
        if (courses.stream().map(Course::getName).collect(Collectors.toList()).contains(name)) {
            throw new RepeatCourseException(name);
        } else if (numHoles <= 0) {
            throw new InputMismatchException();
        } else {
            EventLog.getInstance().logEvent(new Event("Added new course: " + name));
            this.courses.add(new Course(name, numHoles));
        }
    }
    
    // MODIFIES: this
    // EFFECTS: adds course with given name and holes, logs
    public void addCourse(String name, List<Hole> holes) throws RepeatCourseException {
        if (courses.stream().map(Course::getName).collect(Collectors.toList()).contains(name)) {
            throw new RepeatCourseException(name);
        } else {
            EventLog.getInstance().logEvent(new Event("Added new course: " + name));
            this.courses.add(new Course(name, holes));
        }
    }
    
    // MODIFIES: this
    // EFFECTS: stores given performance, logs
    // throws input mismatch error if given performance's golfers and course
    //      are not present in this league
    public void addPerformance(GameAllPerformance gap) {
        if (!getCourseNames()
                .contains(gap.getCourseName())) {
            throw new CourseNotPresentException();
        } else if (!this.golfers.containsAll(gap.getGolfers())) {
            throw new GolferNotPresentException();
        } else {
            EventLog.getInstance().logEvent(new Event("Added new GAP on " + gap.getCourseName()));
            performances.add(gap);
        }
    }
    
    
    // MODIFIES: this
    // EFFECTS: sets this gambler as given gambler, logs
    public void setGambler(Gambler gambler) {
        EventLog.getInstance().logEvent(new Event("Set gambler with balance " + gambler.getBalance()));
        this.gambler = gambler;
    }
    
    // REQUIRES: golferNames have been added as golfers
    //           courseName has been added as course
    // EFFECTS: plays game with the given golfers on the given course
    //          returns resulting GAP, logs
    public GameAllPerformance playGame(String courseName, List<String> golferNames) {
        Course course = null;
        List<Golfer> golfers = new ArrayList<>();
        
        for (Course c : this.courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        for (Golfer g : this.golfers) {
            if (golferNames.contains(g.getName())) {
                golfers.add(g);
            }
        }
        
        if (course == null) {
            throw new CourseNotPresentException();
        }
        if (golferNames.size() != golfers.size()) {
            throw new GolferNotPresentException();
        }
        
        
        GameAllPerformance gap = new GameAllPerformance(Arrays.asList(course.getHoles()), golfers, course.getName());
    
        EventLog.getInstance().logEvent(new Event("played game on " + courseName));
        return playGame(gap);
    }
    
    // MODIFIES: this, gap
    // EFFECTS: completes the given performance by generating a performance for
    //     each slot in it. Also add given gap to this
    private GameAllPerformance playGame(GameAllPerformance gap) {
        for (Hole hole : gap.getHoles()) {
            for (Golfer golfer : gap.getGolfers()) {
                gap.addHolePerformance(hole.playHole(golfer));
            }
        }
        
        addPerformance(gap);
        return gap;
    }
    
    
    // EFFECTS: returns all stored game performances for given golfer, logs
    public List<GameGolferPerformance> getGolferHistory(String golferName) {
        Golfer golfer = this.getGolfer(golferName);
        
        List<GameGolferPerformance> result = new ArrayList<>();
        for (GameAllPerformance performance : performances) {
            if (performance.getGolfers().contains(golfer)) {
                result.add(performance.getGolferPerformance(golfer));
            }
        }
    
        EventLog.getInstance().logEvent(new Event("retrieved history for " + golferName));
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
        return this.courses.stream()
                .map(Course::getName)
                .collect(Collectors
                        .toList());
    }
    
    // EFFECTS: return the names of the golfers in the league
    public List<String> getGolferNames() {
        return this.golfers.stream()
                .map(Golfer::getName)
                .collect(Collectors
                        .toList());
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    public List<GameAllPerformance> getPerformances() {
        return performances;
    }
    
    public Gambler getGambler() {
        return gambler;
    }
    
    @Override
    public JSONObject toJson() {
        JSONArray golfersJson = new JSONArray();
        JSONArray coursesJson = new JSONArray();
        JSONArray totalStrokesJson = new JSONArray();
        
        for (Golfer golfer : this.golfers) {
            golfersJson.put(golfer.toJson());
        }
        
        for (Course course : this.courses) {
            coursesJson.put(course.toJson());
        }
        
        for (GameAllPerformance gap : this.performances) {
            totalStrokesJson.put(gap.toJson());
            
        }
        
        JSONObject json = new JSONObject();
        json.put("golfers", golfersJson);
        json.put("courses", coursesJson);
        json.put("performances", totalStrokesJson);
        json.put("gambler", gambler.toJson());
        
        return json;
    }
}
