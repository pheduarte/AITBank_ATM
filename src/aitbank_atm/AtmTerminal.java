
package aitbank_atm;

import javax.swing.JFrame;


public class AtmTerminal extends JFrame{
    
    
    public AtmTerminal() {

        AtmPanel terminal = new AtmPanel();
        add(terminal);

        setTitle("AITBank");
        setSize(800, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }       
    
}
