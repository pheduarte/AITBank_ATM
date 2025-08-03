package aitbank_atm;

import javax.swing.*;

public class AITBank_ATM {

    public static void main(String[] args) {

        // AtmTerminal newTerminal = new AtmTerminal();
        SwingUtilities.invokeLater(() -> new AtmTerminal().setVisible(true));

    }

}
