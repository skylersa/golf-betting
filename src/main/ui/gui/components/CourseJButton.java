package ui.gui.components;

import model.game.Course;

import javax.swing.*;

/*
 * A JButton which stores and is labelled with the given course
 */
public class CourseJButton extends JButton {
    private Course course;
    
    // EFFECTS: creates new CourseJButton with given course
    public CourseJButton(Course course) {
        super(course.getName() + ", " + course.getNumHoles());
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }
}
