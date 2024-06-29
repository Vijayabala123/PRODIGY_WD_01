import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int targetNumber = random.nextInt(max - min + 1) + min;
        int attempts = 0;
        int guess;

        System.out.println("Welcome to Guess the Number game!");
        System.out.println("I have picked a number between " + min + " and " + max + ".");
        System.out.println("Try to guess it!");

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            }
        } while (guess != targetNumber);

        System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly.");
        System.out.println("It took you " + attempts + " attempts.");

        scanner.close();
    }
}
