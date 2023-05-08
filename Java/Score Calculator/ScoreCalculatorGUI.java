import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreCalculatorGUI {
    private ArrayList<Double> scores = new ArrayList<>();
    private double totalScore = 0.0;
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public ScoreCalculatorGUI() {
        JFrame frame = new JFrame("심사위원 점수 집계 프로그램");
        JPanel panel = new JPanel(new GridLayout(11, 1));

        for (int i = 1; i <= 10; i++) {
            double score = new Random().nextDouble() * 10.0;
            scores.add(score);
            JLabel label = new JLabel("심사위원 " + i + "의 점수: " + decimalFormat.format(score));
            panel.add(label);
            totalScore += score;
        }

        double maxScore = Collections.max(scores);
        double minScore = Collections.min(scores);
        scores.remove(maxScore);
        scores.remove(minScore);
        totalScore -= maxScore;
        totalScore -= minScore;

        JLabel totalLabel = new JLabel("합계: " + decimalFormat.format(totalScore));
        panel.add(totalLabel);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ScoreCalculatorGUI();
    }
}
