
package aitbank_atm;

import java.util.Scanner;

public class Fixed extends Account {
   @Override
   public String showInfo() {
      return "Fixed account balance: $" + balance;
   }

   // Calculate compound interest for 6 months
   @Override
    public double compound() {
        double bonus = 0.0100;
       return Math.pow(1 + (baseRate + bonus), 180 / 365);
    }
   
    @Override
   public void limit(){       
   }
   
   @Override
    public void withdrawal(Scanner scanner) {
        double value;
        boolean beforeContract = true;

        System.out.println("Plese inform the amount you want to withdraw: ");

        value = scanner.nextDouble();
        scanner.nextLine(); // Clear scanner
        
        //Validates if withdrawal is before contract period
        if (beforeContract) {
        
        // Check if the balance is sufficient
        if (value > balance) {
            System.out.println("Insufficient balance");
            return;
        }
            
        // Check if the available notes 20, 50, or 100      
        if (value % 20 == 0 || value % 50 == 0 || value % 100 == 0) {
            System.out.println("You will not get interests for this balance");
        } else {
            System.out.println("Only $20, $50, and $100 notes available from this ATM.");
        }

        balance -= value;

        System.out.println("Your new balance: " + balance);
        }
    }
}
