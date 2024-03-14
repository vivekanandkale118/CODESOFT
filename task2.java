import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter marks obtained (out of 100) in each subject:");
        int totalMarks = 0;
        int numberOfSubjects;
        do {
            System.out.print("Enter the number of subjects: ");
            numberOfSubjects = scanner.nextInt();
            if (numberOfSubjects <= 0) {
                System.out.println("Please enter a valid number of subjects.");
            }
        } while (numberOfSubjects <= 0);

        for (int i = 1; i <= numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + ": ");
            int marks = scanner.nextInt();
            if (marks < 0 || marks > 100) {
                System.out.println("Marks should be between 0 and 100. Please enter a valid mark.");
                i--;
            } else {
                totalMarks += marks;
            }
        }

        double averagePercentage = (double) totalMarks / numberOfSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}

