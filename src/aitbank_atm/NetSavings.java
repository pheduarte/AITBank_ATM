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

}
