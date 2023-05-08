import java.util.HashMap;
import java.util.Scanner;

public class EnglishDictionaryConsole implements Runnable {

    private final HashMap<String, String> dictionary;

    public EnglishDictionaryConsole() {
        dictionary = new HashMap<>();
        dictionary.put("java", "자바");
        dictionary.put("school", "학교");
        dictionary.put("map", "지도");
    }

    public static void main(String[] args) {
        EnglishDictionaryConsole app = new EnglishDictionaryConsole();
        app.run();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("1")) {
            System.out.print("단어 입력 (종료하려면 1 입력): ");
            input = scanner.nextLine().trim();

            if (input.equals("1")) {
                System.out.println("프로그램을 종료합니다.");
            } else {
                String meaning = dictionary.get(input);
                if (meaning != null) {
                    System.out.println(input + "의 뜻은 " + meaning + "입니다.");
                } else {
                    System.out.println("단어를 찾을 수 없습니다.");
                }
            }
        }
    }
}
