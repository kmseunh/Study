import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CountDownTimer {
    private ArrayList<Integer> arrayList;
    private Queue<Integer> queue;

    public CountDownTimer() {
        this.arrayList = new ArrayList<Integer>();
        int count = 10;
        while (count > 0) {
            this.arrayList.add(count);
            count--;
        }
        this.queue = new LinkedList<Integer>();
        this.queue.addAll(this.arrayList);
    }

    public void startTimer() {
        while (!queue.isEmpty()) {
            int count = queue.poll();
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Countdown finished!");
    }

    public static void main(String[] args) {
        CountDownTimer timer = new CountDownTimer();
        timer.startTimer();
    }
}
