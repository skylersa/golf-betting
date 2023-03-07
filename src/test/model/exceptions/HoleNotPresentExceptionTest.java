package model.exceptions;

import exceptions.HoleNotPresentException;
import model.game.Hole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HoleNotPresentExceptionTest {
    private HoleNotPresentException holeNotPresentException0;
    private HoleNotPresentException holeNotPresentExceptionName;
    private HoleNotPresentException holeNotPresentExceptionE;
    private HoleNotPresentException holeNotPresentExceptionNameE;
    
    Hole h0 = new Hole(73);
    Hole h1 = new Hole(74);
    
    @BeforeEach
    public void setup() {
        holeNotPresentException0 = new HoleNotPresentException();
        holeNotPresentExceptionName = new HoleNotPresentException(h0);
        holeNotPresentExceptionE = new HoleNotPresentException(new NullPointerException("null pointer 1"));
        holeNotPresentExceptionNameE = new HoleNotPresentException(h1,
                new NullPointerException("null pointer 2"));
    }
    
    @Test
    public void constructorTest() {
        assertNull(holeNotPresentException0.getMessage());
        assertEquals(h0.toString(), holeNotPresentExceptionName.getMessage());
        assertEquals(h1.toString(), holeNotPresentExceptionNameE.getMessage());
        assertEquals("null pointer 1", holeNotPresentExceptionE.getCause().getMessage());
        assertEquals("null pointer 2", holeNotPresentExceptionNameE.getCause().getMessage());
    }
}
