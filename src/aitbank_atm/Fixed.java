
package aitbank_atm;

public class Fixed extends Account {

    // Calculate compound interest for 6 months
    @Override
    public double compound() {
        double bonus = 0.0100;
        return Math.pow(1 + (baseRate + bonus), 180 / 365);
    }

}
