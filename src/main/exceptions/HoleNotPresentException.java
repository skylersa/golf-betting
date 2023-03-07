package exceptions;

import model.game.Hole;

// Thrown when a given Hole does not exist where it ought to
public class HoleNotPresentException extends RuntimeException {
    
    public HoleNotPresentException() {
        super();
    }
    
    public HoleNotPresentException(Hole hole) {
        super(hole.toString(), null);
    }
    
    public HoleNotPresentException(Throwable e) {
        super(e);
    }
    
    public HoleNotPresentException(Hole hole, Throwable e) {
        super(hole.toString(), e);
    }
}
