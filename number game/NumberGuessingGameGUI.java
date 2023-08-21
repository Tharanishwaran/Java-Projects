import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private final JLabel rangeLabel;
    private final JLabel resultLabel;
    private final JLabel scoreLabel;
    private final JTextField guessField;
    private final JButton guessButton;
    private final JButton playAgainButton;
    private final JRadioButton attemptsBasedRadioButton;
    private final JRadioButton roundsWonRadioButton;
    private final ButtonGroup scoreTypeButtonGroup;

    private final Random random;
    private int secretNumber;
    private int attempts;
    private int maxAttempts = 3;
   private int totalRounds = 0; 
    private int totalScore = 0;


    public NumberGuessingGameGUI() {
        random = new Random();

        rangeLabel = new JLabel("Guess a number between 1 and 100:");
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("Score: 0");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");
        attemptsBasedRadioButton = new JRadioButton("Attempts-based Score");
        roundsWonRadioButton = new JRadioButton("Rounds-won Score");
        scoreTypeButtonGroup = new ButtonGroup();
        scoreTypeButtonGroup.add(attemptsBasedRadioButton);
        scoreTypeButtonGroup.add(roundsWonRadioButton);

        attemptsBasedRadioButton.setSelected(true);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewRound();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,3 )); 
        mainPanel.add(rangeLabel);
        mainPanel.add(guessField);
        mainPanel.add(guessButton);
        mainPanel.add(resultLabel);
        mainPanel.add(scoreLabel);
        mainPanel.add(playAgainButton);

        JPanel radioPanel = new JPanel(); 
        radioPanel.add(attemptsBasedRadioButton);
        radioPanel.add(roundsWonRadioButton);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(radioPanel, BorderLayout.NORTH); 
        setTitle("Number Guessing Game");
        setSize(700, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startNewRound();
    }



    private void startNewRound() {
        secretNumber = random.nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        totalRounds++;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < secretNumber) {
                resultLabel.setText("Too low! Try a higher number.");
                resultLabel.setForeground(Color.RED);
            } else if (userGuess > secretNumber) {
                resultLabel.setText("Too high! Try a lower number.");
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setText("Congratulations! You've guessed the number " + secretNumber +
                        " in " + attempts + " attempts.");
                resultLabel.setForeground(Color.BLACK);

                
                if (attemptsBasedRadioButton.isSelected()) {
                    totalScore += maxAttempts - attempts + 1;
                } else {
                    totalScore += 1;
                }

                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }

            if (attempts >= maxAttempts) {
                resultLabel.setText("Sorry, you've reached the maximum number of attempts. The correct number was " + secretNumber + ".");
                resultLabel.setForeground(Color.BLACK);
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }

          
            scoreLabel.setText("Score: " + (attemptsBasedRadioButton.isSelected() ? "Attempts-based " : "Rounds-won ") + totalScore);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
            resultLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
