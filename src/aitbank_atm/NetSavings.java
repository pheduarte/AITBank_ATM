/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aitbank_atm;

import java.util.Scanner;

public class NetSavings extends Account {

    @Override
    public String showInfo() {
        return "Net Savings account balance: $" + balance;
    }

    // Calculate compound interest
    @Override
    public double compound() {
        double bonus = 0.0085;
        return Math.pow(1 + (baseRate + bonus), 30.0 / 365);
    }

    @Override
    public void limit() {

        System.out.println("Account limit status: $10,000.00");
        limitValue = 10000.00;
    }

    @Override
    public void withdrawal(Scanner scanner) {
        double value;
        limitValue = 10000.00;

        System.out.println("Plese inform the amount you want to withdraw: ");

        value = scanner.nextDouble();
        scanner.nextLine(); // Clear scanner

        // Check if the balance is sufficient
        if (value > balance) {
            System.out.println("Insufficient balance");
            return;
        }

        // Check if the available notes 20, 50, or 100
        if (value > limitValue) {
            System.out.println("Daily limit of $10,000");
        } else {

            if (value % 20 == 0 || value % 50 == 0 || value % 100 == 0) {
                System.out.println("Take your money");
            } else {
                System.out.println("Only $20, $50, and $100 notes available from this ATM.");
            }

            balance -= value;

            System.out.println("Your new balance: " + balance);
        }
    }

    @Override
    public void deposit(double s) {
        // System.out.println("Depositing to: " + this.getClass().getSimpleName());
        // System.out.println("Plese informe the deposit amount: ");
        // balance += scanner.nextDouble();
        // scanner.nextLine();

        // System.out.println("Your new balance: " + balance);
    }

}
