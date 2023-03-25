package ui.gui.components;

import model.game.Course;

import javax.swing.*;

/*
 * a JLabel that stores and is labelled with the given course
 */
public class CourseJLabel extends JLabel {
    private Course course;
    
    // EFFECTS: creates new CourseJLabel with given course
    public CourseJLabel(Course course) {
        super(course.getName() + ", " + course.getNumHoles());
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }
}
