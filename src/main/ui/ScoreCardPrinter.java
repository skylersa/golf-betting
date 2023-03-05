package ui;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;

public class ScoreCardPrinter {
    private static final String SPACER = "  ";
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public void printScoreCard(GameAllPerformance gameAllPerformance) {
        //TODO printScoreCard is a stub
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public void printScoreCard(GameGolferPerformance ggp) {
        StringBuilder parString = new StringBuilder("Par: ");
        StringBuilder strokeString = new StringBuilder("Strokes: ");
        for (int i = 0; i < ggp.size(); i++) {
            parString.append(SPACER).append(ggp.getHole(i).getPar());
            strokeString.append(SPACER).append(ggp.getStroke(i));
        }
        System.out.println(parString);
        System.out.println(strokeString);
    }
    
    // REQUIRES: TODO
    // MODIFIES:
    // EFFECTS:
    public void printScoreCard(HoleAllPerformance hap) {
        System.out.println("STROKES" + SPACER + SPACER + "GOLFER");
        for (int i = 0; i < hap.size(); i++) {
            System.out.println(SPACER + hap.getStroke(i)
                    + ".........." + hap.getGolfer(i).getName());
        }
    }
}
