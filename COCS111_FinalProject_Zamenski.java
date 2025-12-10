
/**
 * Final Project Banking Application
 *
 * Allows user to do the following via Numerical Pad:
 *
 * Create New Accounts
 * Select Account Type (Checking/Saving)
 * Make Deposits into Accounts
 * Make Withdrawals from Accounts
 * Shown Specific Account Summary
 * Search Account by Type
 *
 */
import java.util.Scanner;

public class COCS111_FinalProject_Zamenski {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Fixed Size Array for Accounts
        BankAccount[] accounts = new BankAccount[10]; // Array to Store 1-10 Accounts
        int numAccounts = 0; // Track number of accounts created

        boolean running = true; // Controls Main Loop

        // Main loop (Main Menu Display)
        while (running) {
            System.out.println("BANKING MENU");
            System.out.println("========================");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Account Summary");
            System.out.println("5. Search Account");
            System.out.println("6. Exit");
            System.out.print("Select Option: ");

            // Validate Numeric Input
            if (!sc.hasNextInt()) { // Verifies Input is not an Integer
                System.out.println("Input Invalid. Enter Number.");
                sc.nextLine(); // Clears Invalid Input
                continue; // Restart Loop
            }
            int choice = sc.nextInt(); // Reads User Input 
            sc.nextLine(); // Clear Buffer

            switch (choice) {
                case 1:
                    // Create Account
                    if (numAccounts >= accounts.length) { // Verify Array Capacity
                        System.out.println("Account Not Created. System Capacity Reached.");
                        break;
                    }

                    // NUMERIC ACCOUNT TYPE SELECTION    
                    System.out.println("Select account type:");
                    System.out.println("1. Checking");
                    System.out.println("2. Savings");
                    System.out.print("Enter choice: ");

                    if (!sc.hasNextInt()) { // Validates Account Type Input
                        System.out.println("Invalid input. Select 1 or 2.");
                        sc.nextLine();
                        break;
                    }

                    int typeChoice = sc.nextInt(); // Read account type choice
                    sc.nextLine(); // Clear buffer

                    String type;

                    if (typeChoice == 1) {
                        type = "Checking"; // Sets Type Upon User Input 1
                    } else if (typeChoice == 2) {
                        type = "Savings"; // Sets Type Upon User Input 2
                    } else {
                        System.out.println("Selection Invalid. Select 1 for Checking or 2 for Savings.");
                        break;
                    }

                    System.out.print("Input Initial Deposit: $");
                    if (!sc.hasNextDouble()) { // Validates User Input Deposit
                        System.out.println("Invalid amount.");
                        sc.nextLine();
                        break;
                    }

                    double initial = sc.nextDouble(); // Reads Deposit Amount

                    if (initial < 0) { // Verifies User Input is Non Negative
                        System.out.println("Initial Deposit must be Positive.");
                        break;
                    }

                    int newAccNum = numAccounts + 1; // Assigns Account Number Greater than Zero
                    accounts[numAccounts] = new BankAccount(type, initial, newAccNum);
                    numAccounts++; // Increase Account Number in Numerical Order 
                    System.out.println(type + " " + "Account #" + newAccNum + " - " + " Created Successfully.");
                    break;

                case 2: // Deposit
                    System.out.print("Select Account for Deposit: ");

                    if (!sc.hasNextInt()) { // Validate Account Number
                        System.out.println("Invalid number.");
                        sc.nextLine();
                        break;
                    }
                    int depIndex = sc.nextInt() - 1; // User Friendly Account Number (Avoids Using "Account 0")

                    if (depIndex < 0 || depIndex >= numAccounts) { // Verify Existence of Account
                        System.out.println("Account does not exist.");
                        break;
                    }

                    BankAccount depAcc = accounts[depIndex]; // Retrieve Account Object

                    System.out.println("Account #" + depIndex + " Balance: $" + depAcc.getBalance());
                    System.out.print("Input Deposit Amount: $");

                    if (!sc.hasNextDouble()) { // Validate Deposit Amount
                        System.out.println("Amount Invalid.");
                        sc.nextLine();
                        break;
                    }
                    double depAmount = sc.nextDouble(); // Rread Deposit Amount 

                    if (depAmount <= 0) { // Checks for Positive Amount 
                        System.out.println("Deposit could not be completed. Amount must be > $0.");
                    } else {
                        depAcc.setBalance(depAcc.getBalance() + depAmount); // Add Deposit
                        System.out.println("$" + depAmount + " deposited. New balance: $" + depAcc.getBalance());
                    }
                    break;

                case 3: // Withdraw
                    System.out.print("Enter Desired Account number for Withdraw: ");

                    if (!sc.hasNextInt()) { // Validate Input
                        System.out.println("Invalid Number.");
                        sc.nextLine();
                        break;
                    }
                    int wIndex = sc.nextInt() - 1; // User Friendly (Avoid 0)

                    if (wIndex < 0 || wIndex >= numAccounts) { // Verifys Account Existence
                        System.out.println("Non-Existent Account.");
                        break;
                    }

                    BankAccount wAcc = accounts[wIndex]; // Retrieve Account Object 

                    System.out.println("Account #" + wIndex + " Balance: $" + wAcc.getBalance());
                    System.out.print("Select Withdraw Amount: $");

                    if (!sc.hasNextDouble()) { // Validates Amount
                        System.out.println("Amount Invalid.");
                        sc.nextLine();
                        break;
                    }
                    double wAmount = sc.nextDouble();

                    if (wAmount <= 0) { // Verifys Positive Amount
                        System.out.println("Withdrawal must be > $0.");
                    } else if (wAmount > wAcc.getBalance()) { // Verifys Sufficient Balance 
                        System.out.println("Cannot withdraw. Account Balance Exceeded.");
                    } else {
                        wAcc.setBalance(wAcc.getBalance() - wAmount); // Subtract Withdrawal
                        System.out.println("$" + wAmount + " withdrawn. New balance: $" + wAcc.getBalance());
                    }
                    break;

                case 4: // Summary
                    System.out.print("Select Account for Summary: ");

                    if (!sc.hasNextInt()) { // Validate Account Number
                        System.out.println("Number Invalid.");
                        sc.nextLine();
                        break;
                    }
                    int sIndex = sc.nextInt() - 1; // User Friendly

                    if (sIndex < 0 || sIndex >= numAccounts) { // Check Existence
                        System.out.println("Non Existent Account.");
                        break;
                    }

                    System.out.println(accounts[sIndex].toString()); // Account Summary Display
                    break;

                case 5: // Search Account by Type (Checking/Savings)
                    System.out.println("Select account type:");
                    System.out.println("1. Checking");
                    System.out.println("2. Savings");
                    System.out.print("Enter choice: ");

                    if (!sc.hasNextInt()) { // Validate User Input
                        System.out.println("Invalid input. Select 1 or 2.");
                        sc.nextLine();
                        break;
                    }

                    int searchChoice = sc.nextInt(); // Read Search Type Choice
                    sc.nextLine(); // Clear buffer

                    String searchTypeStr;

                    if (searchChoice == 1) {
                        searchTypeStr = "Checking"; // Set Search String
                    } else if (searchChoice == 2) {
                        searchTypeStr = "Savings"; // Set Search String
                    } else {
                        System.out.println("Selection Invalid. Please Select 1 for Checking or 2 for Savings.");
                        break;
                    }

                    boolean found = false; // Track if any Account Found
                    for (int i = 0; i < numAccounts; i++) {
                        if (accounts[i].getAccountType().equalsIgnoreCase(searchTypeStr)) {
                            System.out.println(accounts[i]);
                            found = true;
                        }
                    }

                    if (!found) { // When Account not found
                        System.out.println("No accounts of that type.");
                    }
                    break;

                case 6: // Exit
                    running = false; // Stop Main Loop
                    System.out.println("Exiting system. Have A Nice Day... Or Don't, It's Your Life.");
                    break;

                default:
                    System.out.println("Invalid option."); // Invalid Menu Choice
            }
        }

        sc.close(); // Close Scanner 
    }
}