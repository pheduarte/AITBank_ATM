
package aitbank_atm;

import java.util.Scanner;

public class Savings extends Account {

    @Override
    public String showInfo() {
        return "Saving account balance: $" + balance;
    }

    // Calculate compound interest
    @Override
    public double compound() {
        return Math.pow(1 + baseRate, 1 / 365);
    }

    @Override
    public void limit() {
        Scanner input = new Scanner(System.in);
        int option;
        String displayStatus = isLimited ? "yes" : "no";

        System.out.println("Account limit status: " + displayStatus);

        if (isLimited) {
            System.out.println("Choose an option: ");
            System.out.println("0 - No limit");
            System.out.println("1 - Change Limit");
        } else {
            System.out.println("Choose an option: ");
            System.out.println("1 - Set Limit");
        }

        option = Integer.parseInt(input.nextLine());

        switch (option) {
            case 0:
                isLimited = false;
                break;

            case 1:
                System.out.println("Type new limit");

                limitValue = Double.parseDouble(input.nextLine());
                isLimited = true;
                break;

            default:
                System.out.println("Select a valid option");
                break;
        }
    }

    @Override
    public void deposit(double scanner) {
        // System.out.println("Depositing to: " + this.getClass().getSimpleName());
        // System.out.println("Plese informe the deposit amount: ");
        // balance += scanner.nextDouble();
        // scanner.nextLine();

        // System.out.println("Your new balance: " + balance);
    }
}
