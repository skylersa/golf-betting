package model.exceptions;

import exceptions.GolferNotPresentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GolferNotPresentExceptionTest {
    private GolferNotPresentException golferNotPresentException0;
    private GolferNotPresentException golferNotPresentExceptionName;
    private GolferNotPresentException golferNotPresentExceptionE;
    private GolferNotPresentException golferNotPresentExceptionNameE;
    
    @BeforeEach
    public void setup() {
        golferNotPresentException0 = new GolferNotPresentException();
        golferNotPresentExceptionName = new GolferNotPresentException("bob1");
        golferNotPresentExceptionE = new GolferNotPresentException(new NullPointerException("null pointer 1"));
        golferNotPresentExceptionNameE = new GolferNotPresentException("bob2",
                new NullPointerException("null pointer 2"));
    }
    
    @Test
    public void constructorTest() {
        assertNull(golferNotPresentException0.getMessage());
        assertEquals("bob1", golferNotPresentExceptionName.getMessage());
        assertEquals("bob2", golferNotPresentExceptionNameE.getMessage());
        assertEquals("null pointer 1", golferNotPresentExceptionE.getCause().getMessage());
        assertEquals("null pointer 2", golferNotPresentExceptionNameE.getCause().getMessage());
    }
}
