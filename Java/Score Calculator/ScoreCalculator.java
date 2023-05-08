import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScoreCalculator {
    private static final int NUM_JUDGES = 10;
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 10.0;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");

    private List<Double> scores = new ArrayList<>();
    private double totalScore = 0.0;

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            inputScores(scanner);
            calculateTotalScore(scanner);
        }
    }

    private void inputScores(Scanner scanner) {
        for (int i = 1; i <= NUM_JUDGES; i++) {
            double score = -1.0;
            while (score < MIN_SCORE || score > MAX_SCORE) {
                try {
                    System.out.print("심사위원 " + i + "의 점수를 입력하세요(0~10): ");
                    score = scanner.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    scanner.nextLine(); // 버퍼 비우기
                }
            }
            scores.add(score);
            System.out.println("입력된 점수:\t" + DECIMAL_FORMAT.format(score));
        }
    }

    private void calculateTotalScore(Scanner scanner) {
        System.out.print("합계를 계산하려면 0을 입력하세요: ");
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input == 0) {
                    double maxScore = Collections.max(scores);
                    double minScore = Collections.min(scores);
                    scores.remove(maxScore);
                    scores.remove(minScore);
                    totalScore = scores.stream().mapToDouble(Double::doubleValue).sum();
                    System.out.println("합계:\t\t" + DECIMAL_FORMAT.format(totalScore));
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                scanner.nextLine(); // 버퍼 비우기
            }
        }
    }

    public static void main(String[] args) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        scoreCalculator.run();
    }
}
