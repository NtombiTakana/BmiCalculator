package bmicalculator;

import java.util.Scanner;
import java.util.Locale;

public class BmiCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        char repeat;

        do {
            int unitChoice = getUnitChoice(scanner);

            double weight = (unitChoice == 1) 
                    ? getValidInput(scanner, "Enter your weight in kilograms: ", 10, 600)
                    : getValidInput(scanner, "Enter your weight in pounds: ", 22, 1300);

            double height = (unitChoice == 1) 
                    ? getValidInput(scanner, "Enter height in centimeters: ", 50, 250) // ask in cm
                    : getValidInput(scanner, "Enter your height in inches: ", 20, 100);

            // Convert centimeters to meters
            if (unitChoice == 1) {
                height = height / 100.0;
            }

            double bmi = calculateBMI(unitChoice, weight, height);
            System.out.println("\nYour BMI is " + String.format("%.2f", bmi));

            // Categorization
            if (bmi < 18.5) {
                System.out.println(" Underweight");
            } else if (bmi >= 18.5 && bmi < 24.9) {
                System.out.println(" Normal weight");
            } else if (bmi >= 25 && bmi < 29.9) {
                System.out.println(" Overweight");
            } else {
                System.out.println(" Obese");
            }

            System.out.println("\nDo you want to calculate another BMI? (Y/N): ");
            repeat = scanner.next().charAt(0);

        } while (repeat == 'Y' || repeat == 'y');

        System.out.println("Thank you for using the BMI Calculator. Goodbye!");
    }

    public static int getUnitChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("Select a preferred unit:\n"
                    + "1. Metric (kg, cm)\n"
                    + "2. Imperial (lbs, in)\n"
                    + "Please select 1 or 2:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Enter a number (1 or 2).");
                scanner.next();
            }
        }

        return choice;
    }

    public static double getValidInput(Scanner scanner, String prompt, double min, double max) {
        double value;

        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        return value;
    }

    public static double calculateBMI(int unitChoice, double weight, double height) {
        if (unitChoice == 1) {
            // Metric formula
            return weight / (height * height);
        } else {
            // Imperial formula
            return (703 * weight) / (height * height);
        }
    }
}

