package model.exceptions;

import exceptions.CourseNotPresentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourseNotPresentExceptionTest {
    private CourseNotPresentException courseNotPresentException0;
    private CourseNotPresentException courseNotPresentExceptionName;
    private CourseNotPresentException courseNotPresentExceptionE;
    private CourseNotPresentException courseNotPresentExceptionNameE;
    
    @BeforeEach
    public void setup() {
        courseNotPresentException0 = new CourseNotPresentException();
        courseNotPresentExceptionName = new CourseNotPresentException("west1");
        courseNotPresentExceptionE = new CourseNotPresentException(new NullPointerException("null pointer 1"));
        courseNotPresentExceptionNameE = new CourseNotPresentException("west2",
                new NullPointerException("null pointer 2"));
    }
    
    @Test
    public void constructorTest() {
        assertNull(courseNotPresentException0.getMessage());
        assertEquals("west1", courseNotPresentExceptionName.getMessage());
        assertEquals("west2", courseNotPresentExceptionNameE.getMessage());
        assertEquals("null pointer 1", courseNotPresentExceptionE.getCause().getMessage());
        assertEquals("null pointer 2", courseNotPresentExceptionNameE.getCause().getMessage());
    }
}
