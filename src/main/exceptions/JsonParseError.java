package exceptions;

public class JsonParseError extends Error {
    public JsonParseError(String message) {
        super(message);
    }
    
    public JsonParseError(Throwable e) {
        super(e);
    }
}
