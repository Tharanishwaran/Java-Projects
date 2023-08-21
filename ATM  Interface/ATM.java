import java.util.Scanner;

public class ATM {
    private BankAccount bankAccount;
    private int pin;
    private boolean loggedIn;

    public ATM(BankAccount account, int initialPin) {
        bankAccount = account;
        pin = initialPin;
        loggedIn = false;
    }

    public void login(int enteredPin) {
        if (enteredPin == pin) {
            loggedIn = true;
            System.out.println("Login successful.");
        } else {
            loggedIn = false;
            System.out.println("Login failed. Incorrect PIN.");
        }
    }

    public void changePIN(int newPin) {
        if (loggedIn) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("You must be logged in to change PIN.");
        }
    }

    public void withdraw(double amount) {
        if (loggedIn) {
            if (bankAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("You must be logged in to perform withdrawals.");
        }
    }

    public void deposit(double amount) {
        if (loggedIn) {
            bankAccount.deposit(amount);
        } else {
            System.out.println("You must be logged in to make deposits.");
        }
    }

    public void checkBalance() {
        if (loggedIn) {
            System.out.println("Your account balance is: " + bankAccount.getBalance());
        } else {
            System.out.println("You must be logged in to check your balance.");
        }
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
