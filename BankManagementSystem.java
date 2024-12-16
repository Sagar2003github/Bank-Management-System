import java.util.Scanner;
import java.util.HashMap;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static final double MINIMUM_BALANCE = 500.0; // Minimum balance required

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited ₹%.2f. New balance: ₹%.2f%n", amount, balance);
        } else {
            System.out.println("Error: Deposit amount must be greater than zero.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount > balance - MINIMUM_BALANCE) {
                System.out.printf("Error: Withdrawal would drop balance below the minimum of ₹%.2f.%n", MINIMUM_BALANCE);
            } else {
                balance -= amount;
                System.out.printf("Successfully withdrew ₹%.2f. New balance: ₹%.2f%n", amount, balance);
            }
        } else {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("\n===== Account Details =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Current Balance: ₹%.2f%n", balance);
    }
}

public class BankManagementSystem {
    private static HashMap<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Bank Management System ======");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            // Validate choice input
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a valid option (1-5): ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount(scanner);
                case 2 -> depositMoney(scanner);
                case 3 -> withdrawMoney(scanner);
                case 4 -> viewAccountDetails(scanner);
                case 5 -> System.out.println("Exiting... Thank you for using the Bank Management System.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();

        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();

        System.out.print("Enter Initial Balance (minimum ₹500): ");
        double balance = scanner.nextDouble();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Error: An account with this number already exists.");
        } else if (balance < 500) {
            System.out.println("Error: Initial balance must be at least ₹500.");
        } else {
            Account newAccount = new Account(accountNumber, accountHolderName, balance);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully!");
        }
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = scanner.nextDouble();
            accounts.get(accountNumber).deposit(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = scanner.nextDouble();
            accounts.get(accountNumber).withdraw(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static void viewAccountDetails(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();

        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).displayAccountDetails();
        } else {
            System.out.println("Error: Account not found.");
        }
    }
}
