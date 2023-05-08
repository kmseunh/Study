import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CountDownTimerGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> arrayList;
    private Queue<Integer> queue;
    private JLabel label;

    public CountDownTimerGUI() {
        this.arrayList = new ArrayList<Integer>();
        int count = 10;
        while (count > 0) {
            this.arrayList.add(count);
            count--;
        }
        this.queue = new LinkedList<Integer>();
        this.queue.addAll(this.arrayList);

        this.label = new JLabel("Countdown starts...");
        this.add(this.label);

        this.setSize(200, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startTimer() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (!queue.isEmpty()) {
                    int count = queue.poll();
                    label.setText(count + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                label.setText("Countdown finished!");
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        CountDownTimerGUI timer = new CountDownTimerGUI();
        timer.startTimer();
    }
}
