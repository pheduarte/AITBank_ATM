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

        if (dep <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Amount must be a positive number.");
            return;
        }

        balance += dep;
    }

    public double getBalance() {
        return balance;
    }

    // Implement withdrawal capability
    public boolean withdrawal(double value) {

        if (value <= 0)
            return false;
        if (value > balance) {
            return false; // or throw InsufficientFundsException
        }
        if (!isDispensable(value)) {
            return false; // UI can show “Only $20/$50/$100...”
        }
        balance -= value;
        return true;
    }

    // Check if the available notes 20, 50, or 100
    private boolean isDispensable(double value) {
        // simplest: require multiples of 10 or 20, or your exact rule:
        return value % 20 == 0 || value % 50 == 0 || value % 100 == 0;
    }

    // Display account information
    public abstract String showInfo();

    // Calculate compound interest
    public abstract double compound();

    public abstract void limit();
}
