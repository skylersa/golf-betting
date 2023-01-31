package model.gambling;

public class Gambler {
    public static final int INITIAL_BALANCE = 5000;
    private int balance;
    
    // EFFECTS: creates new gambler with a balance of INITIAL_BALANCE
    public Gambler() {
        balance = INITIAL_BALANCE;
    }
    
    // REQUIRES: 0 < amountPlaced <= this.getBalance()
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
