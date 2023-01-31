package model.gambling;

public class Gambler {
    private static final int INITIAL_BALANCE = 5000;
    private int balance;
    
    public Gambler() {
        balance = INITIAL_BALANCE;
    }
    
    // REQUIRES: amountPlaced > 0
    // MODIFIES: this
    // EFFECTS: adds or removes amountPlaced from this balance based on whether thy won.
    public void bet(int amountPlaced, boolean won) {
        if (won) {
            balance += amountPlaced;
        } else {
            balance -= amountPlaced;
        }
    }
    
    public int getBalance() {
        return balance;
    }
}
