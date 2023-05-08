import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class EnglishDictionary extends JFrame implements ActionListener {

    private final JTextField wordInputField;
    private final JTextArea meaningOutputArea;
    private final HashMap<String, String> dictionary;

    public EnglishDictionary() {
        dictionary = new HashMap<>();
        dictionary.put("java", "자바");
        dictionary.put("school", "학교");
        dictionary.put("map", "지도");

        JLabel wordInputLabel = new JLabel("단어 입력:");
        wordInputField = new JTextField(20);
        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(this);

        JPanel wordInputPanel = new JPanel(new FlowLayout());
        wordInputPanel.add(wordInputLabel);
        wordInputPanel.add(wordInputField);
        wordInputPanel.add(searchButton);

        meaningOutputArea = new JTextArea(10, 20);
        meaningOutputArea.setEditable(false);
        JScrollPane meaningOutputScrollPane = new JScrollPane(meaningOutputArea);

        // 단어 입력창과 출력창의 크기를 조절
        wordInputPanel.setPreferredSize(new Dimension(280, 100));
        meaningOutputScrollPane.setPreferredSize(new Dimension(280, 80));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(wordInputPanel, BorderLayout.NORTH);
        contentPane.add(meaningOutputScrollPane, BorderLayout.CENTER);

        setTitle("영어 사전");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EnglishDictionary();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = wordInputField.getText().trim();
        String meaning = dictionary.get(word);
        if (meaning != null) {
            meaningOutputArea.setText(meaning);
        } else {
            meaningOutputArea.setText("단어를 찾을 수 없습니다.");
        }
        wordInputField.requestFocus();
        wordInputField.selectAll();
    }
}
