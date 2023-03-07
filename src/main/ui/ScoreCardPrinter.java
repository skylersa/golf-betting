package ui;

import model.performance.GameAllPerformance;
import model.performance.GameGolferPerformance;
import model.performance.HoleAllPerformance;

import java.util.ArrayList;
import java.util.List;

/*
 * Prints score cards of given types to the console
 * TODO: Improve alignment in printing
 */
public class ScoreCardPrinter {
    private static final String SPACER = "  ";
    
    // EFFECTS: prints given GAP to console
    //    par is across the top
    //    golfers down the side
    //    respective performances fill the middle
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
                strokeString.append(SPACER).append(gap.getStrokes()[i][j]);
            }
        }
        System.out.println(parString);
        for (StringBuilder strokeString : strokeStrings) {
            System.out.println(strokeString);
        }
    }
    
    // EFFECTS: prints give performance to the console
    //   shows par then strokes number
    //   printTitle determines if courseName is print with a bar under it
    public void printScoreCard(GameGolferPerformance ggp, boolean printTitle) {
        StringBuilder parString = new StringBuilder("Par: ");
        StringBuilder strokeString = new StringBuilder("Strokes: ");
        for (int i = 0; i < ggp.size(); i++) {
            parString.append(SPACER).append(ggp.getHole(i).getPar());
            strokeString.append(SPACER).append(ggp.getStroke(i));
        }
        
        if (printTitle) {
            System.out.println(ggp.getCourseName());
            System.out.println("=================================");
        }
        System.out.println(parString);
        System.out.println(strokeString);
    }
    
    // EFFECTS: prints given performance to the console
    //    shows golfers on the side and their respective performances
    public void printScoreCard(HoleAllPerformance hap) {
        System.out.println("STROKES" + SPACER + SPACER + "GOLFER");
        for (int i = 0; i < hap.size(); i++) {
            System.out.println(SPACER + hap.getStroke(i)
                    + ".........." + hap.getGolfer(i).getName());
        }
    }
}
