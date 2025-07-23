
package aitbank_atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class AtmPanel extends JPanel {
    JTextField display = new JTextField();
    String accType = "";
    private final JLabel nameLabel = new JLabel("Welcome, Phelippe D.");
    private final JLabel account = new JLabel("Account: Check");
    private final StringBuilder input = new StringBuilder();
    double amount;

    Check newCheck = new Check();
    Savings newSavings = new Savings();
    Fixed newFixed = new Fixed();
    NetSavings newNet = new NetSavings();

    public AtmPanel() {
        JPanel generalPanel = new JPanel(new GridLayout(2, 1, 8, 8));

        setLayout(new BorderLayout(8, 8));
        setBackground(Color.LIGHT_GRAY);

        // --- Info bar (account name & balance) ---
        JPanel infoBar = new JPanel(new GridLayout(1, 3, 8, 8));
        infoBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoBar.add(nameLabel);
        infoBar.add(account);
        add(infoBar, BorderLayout.NORTH);

        // Creates a main panel for a manageble layout
        JPanel mainPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Creates a display area
        JPanel displayPanel = new JPanel(new GridLayout(1, 1, 0, 0));
        displayPanel.add(display);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setFont(display.getFont().deriveFont(16f));
        display.setBackground(Color.lightGray);
        display.setText("Welcome! Please select an operation");

        mainPanel.add(displayPanel);

        JPanel accountPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JButton btnCheck = new JButton("Check");
        JButton btnFixed = new JButton("Fixed");
        JButton btnNet = new JButton("NetSavings");
        JButton btnSav = new JButton("Savings");

        btnCheck.addActionListener(e -> setAccType("Check"));
        accountPanel.add(btnCheck);
        btnSav.addActionListener(e -> setAccType("Savings"));
        accountPanel.add(btnSav);
        btnFixed.addActionListener(e -> setAccType("Fixed"));
        accountPanel.add(btnFixed);
        btnNet.addActionListener(e -> setAccType("Net Savings"));
        accountPanel.add(btnNet);

        mainPanel.add(accountPanel);

        generalPanel.add(mainPanel);

        JPanel mainPanel2 = new JPanel(new GridLayout(1, 1, 5, 5));
        mainPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Creates a keypad
        JPanel keypad = new JPanel(new GridLayout(4, 3, 1, 1));
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btnC = new JButton("C");
        JButton btn0 = new JButton("0");
        JButton btnE = new JButton("Enter");

        ActionListener digitListener = e -> {
            String t = ((JButton) e.getSource()).getText();
            input.append(t);
            display.setText(input.toString());
        };

        btn0.addActionListener(digitListener);
        btn1.addActionListener(digitListener);
        btn2.addActionListener(digitListener);
        btn3.addActionListener(digitListener);
        btn4.addActionListener(digitListener);
        btn5.addActionListener(digitListener);
        btn6.addActionListener(digitListener);
        btn7.addActionListener(digitListener);
        btn8.addActionListener(digitListener);
        btn9.addActionListener(digitListener);

        btnC.addActionListener(e -> {
            input.setLength(0);
            display.setText("");
        });

        btnE.addActionListener(e -> {
            if (input.length() == 0)
                return;
            try {
                amount = Double.parseDouble(input.toString());
                // use amount (deposit/withdraw/etc.)
                // e.g. doDeposit(amount);
                input.setLength(0); // reset after use
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
                input.setLength(0);
                display.setText("");
            }
        });

        keypad.add(btn1);
        keypad.add(btn2);
        keypad.add(btn3);
        keypad.add(btn4);
        keypad.add(btn5);
        keypad.add(btn6);
        keypad.add(btn7);
        keypad.add(btn8);
        keypad.add(btn9);
        keypad.add(btnC);
        keypad.add(btn0);
        keypad.add(btnE);

        mainPanel2.add(keypad);

        // Creates button area
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnBalance = new JButton("Balance");
        JButton btnExit = new JButton("Exit");

        btnDeposit.addActionListener(e -> depositOp(accType, amount));
        buttonsPanel.add(btnDeposit);

        buttonsPanel.add(btnWithdraw);
        btnBalance.addActionListener(e -> showBalance(newCheck.showInfo()));

        btnBalance.addActionListener(digitListener);
        buttonsPanel.add(btnBalance);
        btnExit.addActionListener(e -> System.exit(0));
        buttonsPanel.add(btnExit);
        btnExit.setForeground(Color.RED);

        mainPanel2.add(buttonsPanel);

        // Adds main panels
        generalPanel.add(mainPanel2);
        add(generalPanel);
    }

    public void setAccType(String t) {
        accType = t;
        account.setText("Account: " + accType);
    }

    public void showBalance(String b) {
        String accountB = b;

        if ("Savings".equals(accountB)) {
            display.setText(accountB + " balance: $");
        }
    }

    public void depositOp(String account, double value) {
        double amount = value;
        display.setText("Please enter the amount: ");

        switch (account) {
            case "Check" -> newCheck.deposit(amount);
            case "Savings" -> newSavings.deposit(amount);
            case "Fixed" -> newFixed.deposit(amount);
            case "Net Savings" -> newNet.deposit(amount);
        }

    }

    public void balanceOp(String account) {
        switch (account) {
            case "Check" -> display.setText(Check.showInfo());
            case "Savings" -> newSavings.deposit(amount);
            case "Fixed" -> newFixed.deposit(amount);
            case "Net Savings" -> newNet.deposit(amount);
        }
    }

}
