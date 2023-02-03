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
        g0.bet(false, 100);
        expectedBal0 -= 100;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(true, 100);
        expectedBal0 += 100;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(true, 1000);
        expectedBal0 += 1000;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(false, 1);
        expectedBal0 -= 1;
        assertEquals(expectedBal0, g0.getBalance());
        
        g0.bet(true, expectedBal0);
        expectedBal0 *= 2;
        assertEquals(expectedBal0, g0.getBalance());
    
        g0.bet(false, expectedBal0);
        expectedBal0 = 0;
        assertEquals(expectedBal0, g0.getBalance());
    }
}
