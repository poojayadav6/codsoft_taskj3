import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimedQuiz {
    static Timer timer;
    static int timeLimitInSeconds = 10; // Time limit for each question in seconds

    public static void main(String[] args) {
        // Create a map to store questions, options, and correct answers
        Map<String, String[]> quizQuestions = new HashMap<>();
        quizQuestions.put("Who was the First Prime Minister of India?",
                new String[]{"A. Jawaharlal Nehru", "B. Chandra Shekhar", "C. Rajiv Gandhi", "D. Indira Gandhi", "A"});
        quizQuestions.put("Which planet is known as the Red Planet?",
                new String[]{"A. Mars", "B. Jupiter", "C. Saturn", "D. Venus", "A"});
        quizQuestions.put("Who wrote 'Romeo and Juliet'?",
                new String[]{"A. William Shakespeare", "B. Charles Dickens", "C. Jane Austen", "D. Mark Twain", "A"});

        // Take user input for the quiz
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Map.Entry<String, String[]> entry : quizQuestions.entrySet()) {
            String question = entry.getKey();
            String[] options = entry.getValue();

            System.out.println(question);
            for (String option : options) {
                System.out.println(option);
            }

            // Start the timer
            startTimer();

            // Get user's answer
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().toUpperCase();

            // Stop the timer
            stopTimer();

            // Check if the user's answer is correct
            if (userAnswer.equals(options[4])) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + options[4] + ".\n");
            }
        }

        // Display final score
        System.out.println("Quiz ended! Your final score is: " + score + "/" + quizQuestions.size());

        // Close scanner
        scanner.close();
    }

    // Timer task to limit the time for each question
    static class QuizTimerTask extends TimerTask {
        public void run() {
            System.out.println("\nTime's up!");
            stopTimer();
        }
    }

    // Start the timer
    static void startTimer() {
        timer = new Timer();
        timer.schedule(new QuizTimerTask(), timeLimitInSeconds * 1000);
    }

    // Stop the timer
    static void stopTimer() {
        timer.cancel();
    }
}