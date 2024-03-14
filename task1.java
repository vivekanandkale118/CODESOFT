import java.util.Random;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int maxAttempts = 5;
        int score = 0;

        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("Welcome to the Random Number Game!");
            System.out.println("Guess the random number between " + min + " and " + max + ":");

            while (!guessedCorrectly && attempts < maxAttempts) {
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < randomNumber) {
                    System.out.println("Too low. Try again:");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high. Try again:");
                } else {
                    System.out.println("Congratulations! You guessed the correct number: " + randomNumber);
                    score += maxAttempts - attempts + 1;
                    guessedCorrectly = true;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Your current score: " + score);
            System.out.println("Do you want to play again? (yes/no)");
            String playAgainChoice = scanner.next();
            playAgain = playAgainChoice.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
    }
}

