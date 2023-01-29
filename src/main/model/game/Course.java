package model.game;

public class Course {
    private final String name;
    private final int numHoles;
    Hole[] holes;
    
    // REQUIRES: numHoles > 0
    // EFFECTS: create new Course with given name and and number of holes
    public Course(String name, int numHoles) {
        this.name = name;
        this.numHoles = numHoles;
    
        holes = new Hole[numHoles];
        //TODO: discover foreach assignment behaviour
        for (Hole hole : holes) {
            hole = new Hole();
        }
//        for (int i = 0; i < this.numHoles; i++) {
//            holes[i] = new Hole();
//        }
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumHoles() {
        return numHoles;
    }
    
    public Hole getHole(int holeIndex) {
        return holes[holeIndex];
    }
}
