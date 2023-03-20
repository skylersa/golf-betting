package ui.components;

import model.game.Course;

import javax.swing.*;

public class CourseJButton extends JButton {
    private Course course;
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public CourseJButton(Course course) {
        super(course.getName() + ", " + course.getNumHoles());
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }
}
