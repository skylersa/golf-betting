package model.game;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a league of golfers and course that they can play on. Gives a friendlier manner with which to interact
 * with the various list of things (using strings)
 */
public class League {
    private List<Golfer> golfers;
    private List<Course> courses;
    
    // EFFECTS: new league with no players or courses
    public League() {
        golfers = new ArrayList<>();
        courses = new ArrayList<>();
    }
    
    // REQUIRES: name is unique
    // MODIFIES: this
    // EFFECTS: adds golfer with given name
    public void addGolfer(String name) {
        this.golfers.add(new Golfer(name));
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
    public Game makeGame(String courseName, List<String> golferNames) {
        Course courseToAdd = new Course("placeholder course name", 69);
        for (Course course : this.courses) {
            if (course.getName().equals(courseName)) {
                courseToAdd = course;
            }
        }
    
        List<Golfer> golfersToAdd = new ArrayList<>();
        for (Golfer golfer : this.golfers) {
            if (golferNames.contains(golfer.getName())) {
                golfersToAdd.add(golfer);
            }
        }
        
        return new Game(courseToAdd, golfersToAdd);
    }
    
    // REQUIRES: given golfer is in this league
    // EFFECTS: returns the Golfer of given name
    public Golfer getGolfer(String name) {
        return this.golfers.get(this.getGolferNames().indexOf(name));
    
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
}
