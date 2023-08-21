import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private ATM atm;
    private JFrame frame;
    private JButton loginButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JButton changePINButton;
    private JLabel balanceLabel;

    public ATMInterface(ATM atm) {
        this.atm = atm;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 400)); // Set initial window size

        loginButton = new JButton("Login");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");
        changePINButton = new JButton("Change PIN");


        balanceLabel = new JLabel("");



        
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Color buttonColor = new Color(255, 255, 0);
        Color buttonTextColor = Color.RED;
        loginButton.setFont(buttonFont);
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(buttonTextColor);
        withdrawButton.setFont(buttonFont);
        withdrawButton.setBackground(buttonColor);
        withdrawButton.setForeground(buttonTextColor);
        depositButton.setFont(buttonFont);
        depositButton.setBackground(buttonColor);
        depositButton.setForeground(buttonTextColor);
        checkBalanceButton.setFont(buttonFont);
        checkBalanceButton.setBackground(buttonColor);
        checkBalanceButton.setForeground(buttonTextColor);
        changePINButton.setFont(buttonFont);
        changePINButton.setBackground(buttonColor);
        changePINButton.setForeground(buttonTextColor);
       
  


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredPin = JOptionPane.showInputDialog(frame, "Enter PIN:");
                if (enteredPin != null && !enteredPin.isEmpty()) {
                    atm.login(Integer.parseInt(enteredPin));
                    updateBalanceLabel();
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!atm.isLoggedIn()) {
                    showLoginFirstMessage();
                    return;
                }

                String amount = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
                if (amount != null && !amount.isEmpty()) {
                    atm.withdraw(Double.parseDouble(amount));
                    updateBalanceLabel();
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!atm.isLoggedIn()) {
                    showLoginFirstMessage();
                    return;
                }

                String amount = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
                if (amount != null && !amount.isEmpty()) {
                    atm.deposit(Double.parseDouble(amount));
                    updateBalanceLabel();
                }
            }
        });


        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!atm.isLoggedIn()) {
                    showLoginFirstMessage();
                    return;
                }

                double balance = atm.getBankAccount().getBalance();
                String balanceMessage = "Your account balance is: $" + String.format("%.2f", balance);
                JOptionPane.showMessageDialog(frame, balanceMessage, "Account Balance", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        changePINButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!atm.isLoggedIn()) {
                    showLoginFirstMessage();
                    return;
                }

                String newPin = JOptionPane.showInputDialog(frame, "Enter new PIN:");
                if (newPin != null && !newPin.isEmpty()) {
                    atm.changePIN(Integer.parseInt(newPin));
                    JOptionPane.showMessageDialog(frame, "PIN changed successfully.");
                }
            }
        });

        // Create button panels
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new BoxLayout(leftButtonPanel, BoxLayout.Y_AXIS));
        leftButtonPanel.add(withdrawButton);
        leftButtonPanel.add(Box.createRigidArea(new Dimension(10, 150))); // Vertical spacing
        leftButtonPanel.add(depositButton);

        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.add(loginButton);
        centerButtonPanel.add(Box.createVerticalStrut(200));

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new BoxLayout(rightButtonPanel, BoxLayout.Y_AXIS));
        rightButtonPanel.add(checkBalanceButton);
        rightButtonPanel.add(Box.createRigidArea(new Dimension(10, 150))); // Vertical spacing
        rightButtonPanel.add(changePINButton);

        // Create main button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        buttonPanel.add(leftButtonPanel);
        buttonPanel.add(centerButtonPanel);
        buttonPanel.add(rightButtonPanel);

        // Create top panel

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Welcome to ATM"));
        // topPanel.setFont(buttonFont);
        topPanel.setForeground(buttonTextColor);
         
    
        // Create content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPanel.add(balanceLabel, BorderLayout.SOUTH);
        frame.setContentPane(contentPanel);

        // Pack the frame to accommodate its contents
        frame.pack();
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateBalanceLabel() {
        double balance = atm.getBankAccount().getBalance();
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    private void showLoginFirstMessage() {
        JOptionPane.showMessageDialog(frame, "Please log in first.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        BankAccount bankAccount = new BankAccount(1000);
        ATM atm = new ATM(bankAccount, 1234);
        ATMInterface atmInterface = new ATMInterface(atm);
    });
}

}