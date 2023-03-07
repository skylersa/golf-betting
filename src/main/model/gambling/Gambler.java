package model.gambling;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a gambler with a balance, the player of this game, who can win and lose bets
 * Note: does not handle bankruptcy, ui must handle game overs
 */
public class Gambler implements Writable {
    public static final int INITIAL_BALANCE = 5000;
    private int balance;
    
    // EFFECTS: creates new gambler with a balance of INITIAL_BALANCE
    public Gambler() {
        this(INITIAL_BALANCE);
    }
    
    // REQUIRES: balance must be positive
    // EFFECTS: creates new gambler with given balance
    public Gambler(int balance) {
        this.balance = balance;
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
    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", this.balance);
        return json;
    }
}
