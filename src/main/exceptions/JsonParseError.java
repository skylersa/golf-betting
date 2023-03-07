package exceptions;

// Thrown when some other error makes it clear that the given Json is improper in some way
public class JsonParseError extends Error {
    public JsonParseError(String message) {
        super(message);
    }
    
    public JsonParseError(Throwable e) {
        super(e);
    }
}
