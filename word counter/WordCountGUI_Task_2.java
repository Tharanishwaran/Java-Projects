import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCountGUI_Task_2 extends JFrame {
    private JTextArea textArea;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCountGUI_Task_2() {
        setTitle("Word Count Tool");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        countButton = new JButton("Count Words");
        countButton.setBackground(new Color(65, 131, 215));
        countButton.setForeground(Color.WHITE);
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textArea.getText();
                int wordCount = countWords(inputText);
                resultLabel.setText("Total words: " + wordCount);
            }
        });

        resultLabel = new JLabel("Total words: 0");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(countButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.NORTH);
    }

    private int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WordCountGUI_Task_2 wordCountGUI = new WordCountGUI_Task_2();
                wordCountGUI.setVisible(true);
            }
        });
    }
}
