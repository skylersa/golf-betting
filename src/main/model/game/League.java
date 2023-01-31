package model.game;

import java.util.ArrayList;

public class League {
    private ArrayList<Golfer> golfers;
    private ArrayList<Course> courses;
    
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
    public Game makeGame(String courseName, ArrayList<String> golferNames) {
        Course courseToAdd = new Course("placeholder course name", 69);
        for (Course course : this.courses) {
            if (course.getName().equals(courseName)) {
                courseToAdd = course;
                break;
            }
        }
    
        ArrayList<Golfer> golfersToAdd = new ArrayList<>();
        for (Golfer golfer : this.golfers) {
            if (golferNames.contains(golfer.getName())) {
                golfersToAdd.add(golfer);
            }
        }
        
        return new Game(courseToAdd, golfersToAdd);
    }
    
    //TODO make tournament function?
    
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public ArrayList<Golfer> getGolfers() {
        return golfers;
    }
}
