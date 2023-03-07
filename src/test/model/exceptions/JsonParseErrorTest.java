package model.exceptions;

import exceptions.JsonParseError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParseErrorTest {
    private JsonParseError jsonParseErrorName;
    private JsonParseError jsonParseErrorE;
    
    @BeforeEach
    public void setup() {
        jsonParseErrorName = new JsonParseError("hello!");
        jsonParseErrorE = new JsonParseError(new NullPointerException("null pointer 1"));
    }
    
    @Test
    public void constructorTest() {
        assertEquals("hello!", jsonParseErrorName.getMessage());
        assertEquals("null pointer 1", jsonParseErrorE.getCause().getMessage());
    }
}
