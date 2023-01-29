package model.game;

public class Hole {
    private final int par;
    //private int obstacleLevel;
    
    
    public Hole() {
        this.par = (int)(Math.random() * 5) + 1;
    }
    
    public Hole(int par) {
        this.par = par;
    }
    
    public int getPar() {
        return this.par;
    }
}
