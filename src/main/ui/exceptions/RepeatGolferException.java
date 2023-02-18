package ui.exceptions;

public class RepeatGolferException extends Exception {
    public RepeatGolferException(String golferName) {
        super(golferName + " is already in the league");
    }
}
