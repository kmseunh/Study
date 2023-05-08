import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HundredDaysCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("생년월일을 입력하세요(yyyy-MM-dd): ");
        String birthdateString = sc.nextLine();

        try {
            LocalDate birthdate = LocalDate.parse(birthdateString);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthdate, currentDate);

            System.out.println("생일: " + birthdateString);
            System.out.println("오늘 날짜: " + currentDate);
            System.out.println("생일부터 오늘까지 경과된 일: " + period.getDays() + "일");
            System.out.println("생일부터 오늘까지 경과된 연도: " + period.getYears() + "년");
            System.out.println();

            LocalDate hundredthDate = birthdate.plusYears(100);
            period = Period.between(currentDate, hundredthDate);

            System.out.println("100살까지 남은 날: " + period.getDays() + "일");
            System.out.println("100살까지 남은 주: " + period.toTotalMonths() / 12 * 4 + "주");
            System.out.println("100살까지 남은 일: " + period.toTotalMonths() * 30 + "일");

        } catch (DateTimeParseException e) {
            System.out.println("잘못된 입력입니다. 생년월일을 yyyy-MM-dd 형식으로 입력해주세요.");
        }
    }
}
