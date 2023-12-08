import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        // Set the range of the guessing game
        int minNumber = 1,maxNumber = 100;

        // Generate a random number
        Random random = new Random();

      int secretNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;

        // Start the game
        Scanner scanner = new Scanner(System.in);
        int guessCount = 0;
        boolean guessedCorrectly = false;

        
      System.out.println("Welcome to the number guessing game!");
        
      System.out.println("I am thinking of a number between " + minNumber + " and " + maxNumber + ".");
        
      System.out.println("Can you guess what it is?");

        while (!guessedCorrectly) {
            // Get the user's guess
          System.out.print("Enter your guess: ");
            
          int guess = scanner.nextInt();
            
          guessCount++;

            // Check the guess
            if (guess == secretNumber) {
        
              guessedCorrectly = true;
            } 
            else if (guess < secretNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }

        // Game over
        scanner.close();
      
       System.out.println("Congratulations! You guessed the number in " + guessCount + " tries.");
    }
}

