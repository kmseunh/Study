import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class HundredDaysCalculatorGUI extends JFrame implements ActionListener {
    private final JTextField birthdateTextField;
    private final JButton currentButton;
    private final JButton hundredthButton;
    private final JLabel resultLabel;

    public HundredDaysCalculatorGUI() {
        setTitle("Hundred Days Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        birthdateTextField = new JTextField(10);
        add(birthdateTextField);

        currentButton = new JButton("현재");
        currentButton.addActionListener(this);
        add(currentButton);

        hundredthButton = new JButton("100살");
        hundredthButton.addActionListener(this);
        add(hundredthButton);

        resultLabel = new JLabel();
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == currentButton || e.getSource() == hundredthButton) {
            String birthdateString = birthdateTextField.getText();
            if (!birthdateString.isEmpty()) {
                LocalDate birthdate = LocalDate.parse(birthdateString);
                LocalDate currentDate = LocalDate.now();
                Period period = Period.between(birthdate, currentDate);

                String result = "생일: " + birthdateString + "<br>" +
                        "오늘 날짜: " + currentDate + "<br>" +
                        "생일부터 오늘까지 경과된 일: " + period.getDays() + "일<br>" +
                        "생일부터 오늘까지 경과된 연도: " + period.getYears() + "년";

                if (e.getSource() == hundredthButton) {
                    LocalDate hundredthDate = birthdate.plusYears(100);
                    period = Period.between(currentDate, hundredthDate);

                    result = "생일: " + birthdateString + "<br>" +
                            "100살까지 남은 날: " + period.getDays() + "일<br>" +
                            "100살까지 남은 주: " + period.toTotalMonths() / 12 * 4 + "주<br>" +
                            "100살까지 남은 일: " + period.toTotalMonths() * 30 + "일";
                }
                resultLabel.setText("<html>" + result + "</html>");
            } else {
                JOptionPane.showMessageDialog(this, "생년월일을 입력하세요.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HundredDaysCalculatorGUI::new);
    }
}
