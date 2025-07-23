
package aitbank_atm;

public class Check extends Account {
   @Override
   public static String showInfo() {

      String info = String.valueOf(balance);
      return info;
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