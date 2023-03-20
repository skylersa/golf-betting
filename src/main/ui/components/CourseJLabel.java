package ui.components;

import model.game.Course;

import javax.swing.*;

public class CourseJLabel extends JLabel {
    private Course course;
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public CourseJLabel(Course course) {
        super(course.getName() + ", " + course.getNumHoles());
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }
}
