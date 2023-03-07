package exceptions;

// Exception is thrown when a golfer with the given name is already in the league
public class RepeatCourseException extends Exception {
    public RepeatCourseException(String courseName) {
        super(courseName + " is already in the league");
    }
}
