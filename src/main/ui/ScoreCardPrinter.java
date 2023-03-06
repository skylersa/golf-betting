package ui;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardPrinter {
    private static final String SPACER = "  ";
    
    // REQUIRES: //TODO
    // MODIFIES:
    // EFFECTS:
    public void printScoreCard(GameAllPerformance gap) {
        StringBuilder parString = new StringBuilder("Par: ");
        List<StringBuilder> strokeStrings = new ArrayList<>();
        for (int i = 0; i < gap.getGolfers().size(); i++) {
            strokeStrings.add(new StringBuilder(gap.getGolfers().get(i).getName() + ": "));
        }
        for (int i = 0; i < gap.getHoles().size(); i++) {
            parString.append(SPACER).append(gap.getHoles().get(i).getPar());
            for (int j = 0; j < strokeStrings.size(); j++) {
                StringBuilder strokeString = strokeStrings.get(j);
                strokeString.append(SPACER).append(gap.getPerformances()[i][j]);
            }
        }
        System.out.println(parString);
        for (StringBuilder strokeString : strokeStrings) {
            System.out.println(strokeString);
        }
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
