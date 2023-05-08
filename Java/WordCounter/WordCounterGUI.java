import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;

public class WordCounterGUI extends JFrame {
    private final JTextArea textArea;
    private File selectedFile;

    public WordCounterGUI() {
        super("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> openFile());
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            countWords();
        }
    }

    private void countWords() {
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(selectedFile);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);
                    } else {
                        wordCountMap.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TreeSet<String> sortedWords = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char c1 = Character.toLowerCase(o1.charAt(0));
                char c2 = Character.toLowerCase(o2.charAt(0));
                if (c1 != c2) {
                    return c1 - c2;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        sortedWords.addAll(wordCountMap.keySet());

        StringBuilder sb = new StringBuilder();
        for (String word : sortedWords) {
            sb.append(word).append(": ").append(wordCountMap.get(word)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounterGUI wordCounterGUI = new WordCounterGUI();
            wordCounterGUI.setVisible(true);
        });
    }
}
