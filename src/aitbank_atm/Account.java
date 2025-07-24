package aitbank_atm;

//Create an abstract class for the account

import javax.swing.JOptionPane;

public abstract class Account {

    // Captures withdrawal status to return possible errors to users
    public enum WithdrawStatus {
        OK,
        NEGATIVE_AMOUNT,
        INSUFFICIENT_FUNDS,
        INVALID_NOTES,
        REACHED_LIMIT
    }

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

    public double getLimit() {
        return limitValue;
    }

    public WithdrawStatus withdraw(double value) {
        if (value <= 0)
            return WithdrawStatus.NEGATIVE_AMOUNT;
        if (value > balance)
            return WithdrawStatus.INSUFFICIENT_FUNDS;
        if (isLimited && value > limitValue)
            return WithdrawStatus.REACHED_LIMIT;
        if (!isDispensable(value))
            return WithdrawStatus.INVALID_NOTES;
        balance -= value;
        return WithdrawStatus.OK;
    }

    // Check if the available notes 20, 50, or 100
    private boolean isDispensable(double value) {
        // simplest: require multiples of 10 or 20, or your exact rule:
        return value % 20 == 0 || value % 50 == 0 || value % 100 == 0;
    }

    private boolean reachedLimit(boolean isLimited) {
        this.isLimited = isLimited;

        return false;
    }

    // Display account information
    public String showInfo() {
        String saldo = String.valueOf(balance);

        return saldo;
    };

    // Calculate compound interest
    public abstract double compound();

    public double setLimit(double limitValue) {
        this.limitValue = limitValue;
        this.isLimited = true;

        return 0;
    };
}
