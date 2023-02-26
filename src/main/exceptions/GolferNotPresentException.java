package exceptions;

public class GolferNotPresentException extends RuntimeException {
    
    public GolferNotPresentException() {
        super();
    }
    
    public GolferNotPresentException(String golferName) {
        super(golferName, null);
    }
    
    public GolferNotPresentException(Throwable e) {
        super(e);
    }
    
    public GolferNotPresentException(String golferName, Throwable e) {
        super(golferName, e);
    }
}
