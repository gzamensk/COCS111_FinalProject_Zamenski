
/**
 * Final Project Banking Application
 * 
 * Allows user to do the following:
 * 
 * Create Accounts
 * Make Deposits
 * Make Withdrawals
 *
 *
 *
 *
 *
 *
 */

import java.util.Scanner;

public class COCS111_FinalProject_Zamenski {

    /**
     * printMenu() prints menu options for user
     */
    static void printMenu() {
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Check Balance");
        System.out.println("4) Exit");
    }

    /**
     * formatCents(int cents)
     *
     * @param cents - integer representing the number of cents needing to be
     * formatted - i.e. 1205 -> $12.05
     * @return formatted - the String representation of the cents provided
     */
    public static String formatCents(int cents) {
        String formatted = "";
        // calculate dollar amount of cents provided (i.e. cents = 1205 -> 12.05)
        double dollars = cents / 100.0;

        if (dollars % 10 == 0) {
            return "$" + dollars + "0";
        }

        // build formatted String to include the dollar representation of the cents given (i.e. $12.05)
        return "$" + dollars;
    }

    /**
     * deposit(int balanceCents, int amountCents)
     *
     * @param balanceCents - the amount in the user's account prior to deposit
     * @param amountCents - the amount the user wants to deposit
     * @return updatedBalance - in cents
     */
    public static int deposit(int balanceCents, double amountCents) {

        // Ensure that it is a positive value
        if (amountCents >= 0) {

            // If so, add that amount to the balanceCents
            int updatedBalance = balanceCents + (int) amountCents;

            return updatedBalance;
        } else {
            // else, let the user know the amount must be greater than 0.

            return balanceCents;
        }
    }

    /**
     * withdraw(int balanceCents, int amountCents) - allows the user to withdraw
     * from their account
     *
     * @param balanceCents - the amount in the user's account prior to withdraw
     * @param amountCents - the amount the user wants to withdraw
     * @return updatedBalance - in cents
     */
    static int withdraw(int balanceCents, double amountCents) {
        int updatedBalance = balanceCents - (int) amountCents;

        if (updatedBalance < 0) {
            System.out.println("Insufficient funds. Balance remains: " + formatCents(balanceCents));
            return balanceCents;
        }
        return updatedBalance;
    }

    /**
     * readMenuChoice(Scanner kb) - reads menu choice and validates that it is
     * between 1 and 4
     *
     * @param kb - Scanner used to read choice
     * @return validChoice
     */
    static int readMenuChoice(Scanner kb) {
        int validChoice = 0;
        System.out.println("Choose 1-4: ");
        validChoice = kb.nextInt();
        return validChoice;
    }

    /**
     * readAmountCents(Scanner kb, String prompt) - validates amount entered by
     * user (must be positive) converts amount to cents, reprompts if invalid.
     *
     * @param kb - Scanner to read input
     * @param prompt - What the user will be asked
     * @return validCents
     */
    static double readAmountCents(Scanner kb, String prompt) {
        double validCents = 0;
        do {
            System.out.print(prompt);
            validCents = kb.nextDouble();
            if (validCents < 0) {
                System.out.println("Please enter the value in positive numbers");
            }
        } while (validCents < 0);

        return validCents * 100;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int choice = 0;
        int balanceCents = 0; // a single account starting at $0

        System.out.println("Welcome to COCS ATM");
        do {
            printMenu();
            choice = readMenuChoice(kb);
            if (choice == 1) {
                System.out.println("Depoist");
                double amount = readAmountCents(kb, "Enter deposit amount in dollars (e.g., 25.75): ");
                int prevBalance = balanceCents;
                balanceCents = deposit(balanceCents, amount);
                if (prevBalance != balanceCents) {
                    System.out.println("Deposited " + formatCents((int) amount) + ". New balance: " + formatCents(balanceCents));
                }
            } else if (choice == 2) {
                System.out.println("Withdraw");
                double amount = readAmountCents(kb, "Enter withdrawal amount in dollars (e.g., 25.75): ");
                int prevBalance = balanceCents;
                balanceCents = withdraw(balanceCents, amount);
                if (prevBalance != balanceCents) {
                    System.out.println("Withdrew " + formatCents((int) amount) + ". New balance: " + formatCents(balanceCents));
                }
            } else if (choice == 3) {
                System.out.println("Balance");
                System.out.println("Current balance: " + formatCents(balanceCents));
            }

        } while (choice != 4);
        System.out.println("Goodbye!");
        kb.close();
    }

}


