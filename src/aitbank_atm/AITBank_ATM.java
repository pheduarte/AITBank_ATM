package aitbank_atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AITBank_ATM {

   
    
    public static void main(String[] args) {

//     AtmTerminal newTerminal = new AtmTerminal();
        SwingUtilities.invokeLater(() -> new AtmTerminal().setVisible(true));

    }
    
}

