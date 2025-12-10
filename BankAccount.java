
class BankAccount {

    public String type;        // Savings or Checking
    public double balance;     // Account balance
    public int accountNum;     // Index assigned when created

    // Constructor
    public BankAccount(String type, double balance, int accountNum) {
        this.type = type;
        this.balance = balance;
        this.accountNum = accountNum;
    }

    // Getters & Setters
    public String getAccountType() {
        return type;
    }

    public void setAccountType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public int getAccountNumber() {
        return accountNum;
    }

    // Return formatted summary text
    public String toString() {
        return "Account #" + accountNum + " - " + type + " " + "====================" + " " + "Balance: $" + balance;
    }
}
