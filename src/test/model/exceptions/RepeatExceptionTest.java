package model.exceptions;

import exceptions.NegativeBetException;
import exceptions.RepeatCourseException;
import exceptions.RepeatGolferException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatExceptionTest {
    RepeatGolferException repeatGolferException;
    RepeatCourseException repeatCourseException;
    NegativeBetException negativeBetException;
    
    @BeforeEach
    public void setup() {
        repeatGolferException = new RepeatGolferException("bob");
        repeatCourseException = new RepeatCourseException("west");
        negativeBetException = new NegativeBetException();
    }
    
    @Test
    public void constructorTest() {
        assertEquals("bob is already in the league", repeatGolferException.getMessage());
        assertEquals("west is already in the league", repeatCourseException.getMessage());
    }
}
