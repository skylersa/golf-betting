package model.gambling;

/*
 * Represents a gambler with a balance, the player of this game, who can win and lose bets
 * Note: does not handle bankruptcy, ui must handle game overs
 */
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
    public void bet(boolean won, int amountPlaced) {
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
