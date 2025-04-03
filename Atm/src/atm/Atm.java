package atm;

import atm.model.User;
import atm.service.ATMService;
import atm.service.ATMServiceImpl;

import java.util.Scanner;

public class Atm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Add sample user
        User user = new User("John Doe", "123456789", 1234, 500.0);
        ATMService atmService = new ATMServiceImpl();

        System.out.println("Welcome to the ATM!");
        System.out.print("Please enter your account number: ");
        String accountNumber = scanner.next();

        if (!accountNumber.equals(user.getAccountNumber())) {
            System.out.println("Invalid account number. Access denied.");
            scanner.close();
            return;
        }

        System.out.print("Please enter your PIN: ");
        int pin = scanner.nextInt();

        if (atmService.authenticate(user, pin)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Please choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: " + atmService.checkBalance(user));
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atmService.deposit(user, depositAmount);
                        System.out.println("Deposit successful. New balance is: " + atmService.checkBalance(user));
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        if (atmService.withdraw(user, withdrawalAmount)) {
                            System.out.println("Withdrawal successful. New balance is: " + atmService.checkBalance(user));
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid PIN. Access denied.");
        }

        scanner.close();
    }
}
