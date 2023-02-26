package exceptions;

// Exception is thrown when a golfer with the given name is already in the league
public class RepeatGolferException extends Exception {
    public RepeatGolferException(String golferName) {
        super(golferName + " is already in the league");
    }
}
