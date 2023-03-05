package exceptions;

public class CourseNotPresentException extends RuntimeException {
    
    public CourseNotPresentException() {
        super();
    }
    
    public CourseNotPresentException(String courseName) {
        super(courseName, null);
    }
    
    public CourseNotPresentException(Throwable e) {
        super(e);
    }
    
    public CourseNotPresentException(String courseName, Throwable e) {
        super(courseName, e);
    }
}
