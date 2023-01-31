package model.gambling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamblerTest {
    private Gambler g0;
    
    @BeforeEach
    public void setup() {
        g0 = new Gambler();
    }
    
    @Test
    public void constructorTest() {
        assertEquals(Gambler.INITIAL_BALANCE, g0.getBalance());
    }
    
    @Test
    public void betTest() {
        int expectedBal0 = g0.getBalance();
        g0.bet(100, false);
        expectedBal0 -= 100;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(100, true);
        expectedBal0 += 100;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(1000, true);
        expectedBal0 += 1000;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(1, false);
        expectedBal0 -= 1;
        assertEquals(expectedBal0, g0.getBalance());
        
        g0.bet(expectedBal0, true);
        expectedBal0 *= 2;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(expectedBal0, false);
        expectedBal0 = 0;
        assertEquals(expectedBal0, g0.getBalance());
    }
}
