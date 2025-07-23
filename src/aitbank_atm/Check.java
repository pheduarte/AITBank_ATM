
package aitbank_atm;

public class Check extends Account {
   @Override
   public String showInfo() {
      return String.format("Type: %s, Balance: $%,.2f", getClass().getSimpleName(), balance);
   }

   // Calculate compound interest
   @Override
   public double compound() {
      return 0;
   }

   @Override
   public void limit() {
   }
}