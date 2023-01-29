package model.game;

public class Course {
    private final String name;
    private final int numHoles;
    Hole[] holes;
    
    public Course(String name, int numHoles) {
        this.name = name;
        this.numHoles = numHoles;
    
        holes = new Hole[numHoles];
        for (int i = 0; i < this.numHoles; i++) {
            holes[i] = new Hole();
        }
    }
    
    public int getNumHoles() {
        return numHoles;
    }
    
    public String getName() {
        return name;
    }
    
    public Hole getHole(int holeIndex) {
        return holes[holeIndex];
    }
}
