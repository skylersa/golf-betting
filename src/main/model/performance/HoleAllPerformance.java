package model.performance;

import model.game.Golfer;
import model.game.Hole;

import java.util.*;

/*
 * Represents the performance of many golfers on one hole
 * Transient type only, not for storage.
 */
public final class HoleAllPerformance implements Iterable<HoleGolferPerformance> {
    private Hole hole;
    private List<Golfer> golfers;
    private List<Integer> strokes;
    
    // EFFECTS: create new HoleAllPerformance on given hole
    public HoleAllPerformance(Hole hole, List<Golfer> golfers, List<Integer> strokes) {
        if (golfers.size() != strokes.size()) {
            throw new InputMismatchException();
        }
        this.hole = hole;
        this.golfers = new ArrayList<>(golfers);
        this.strokes = new ArrayList<>(strokes);
        // todo, take and store hole, simplify BetOnHolePanel
    }
    
    // EFFECTS: returns the golfer with the lowest score
    public Golfer getBestGolfer() {
        List<Integer> sortedList = new ArrayList<>(strokes);
        Collections.sort(sortedList);
        return golfers.get(strokes.indexOf(sortedList.get(0)));
    }
    
    public List<Golfer> getGolfers() {
        return golfers;
    }
    
    public List<Integer> getStrokes() {
        return strokes;
    }
    
    public int getStroke(int index) {
        return strokes.get(index);
    }
    
    public Golfer getGolfer(int index) {
        return golfers.get(index);
    }
    
    public int size() {
        return golfers.size();
    }
    
    @Override
    public Iterator<HoleGolferPerformance> iterator() {
        return new HapItr();
    }
    
    private class HapItr implements Iterator<HoleGolferPerformance> {
        Iterator<Golfer> golferItr = golfers.iterator();
        Iterator<Integer> strokesItr = strokes.iterator();
        
        @Override
        public boolean hasNext() {
            return golferItr.hasNext();
        }
    
        @Override
        public HoleGolferPerformance next() {
            return new HoleGolferPerformance(hole, golferItr.next(), strokesItr.next());
        }
    }
}