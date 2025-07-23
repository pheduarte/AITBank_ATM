package aitbank_atm;

//Create an abstract class for the account

import java.util.Scanner;

import javax.swing.JOptionPane;

public abstract class Account {

    // Properties for all accounts
    protected double balance = 0;
    public boolean isLimited;
    public double limitValue;
    public double baseRate = 0.04;

    // Implement deposit capability
    public void deposit(double dep) {
        try {
            if (dep <= 0) {
                JOptionPane.showMessageDialog(null, this,
                        "Amount must be a positive number.", 0);
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, this,
                    "Please enter a valid number.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        balance += dep;
    }

    public double getBalance() {
        return balance;
    }

    // Implement withdrawal capability
    public void withdrawal(Scanner scanner) {
        double value;

        value = scanner.nextDouble();
        scanner.nextLine(); // Clear scanner

        // Check if the balance is sufficient
        if (value > balance) {
            System.out.println("Insufficient balance");
            return;
        }

        // Check if the available notes 20, 50, or 100
        if (value % 20 == 0 || value % 50 == 0 || value % 100 == 0) {
            System.out.println("Take your money");
        } else {
            System.out.println("Only $20, $50, and $100 notes available from this ATM.");
        }

        balance -= value;
    }

    // Display account information
    public abstract String showInfo();

    // Calculate compound interest
    public abstract double compound();

    public abstract void limit();
}
