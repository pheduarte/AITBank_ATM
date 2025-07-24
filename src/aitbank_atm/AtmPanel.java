
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
    private final JLabel balanceLabel = new JLabel("Balance: $0.00");
    private final JLabel limitLabel = new JLabel("Limit: $0.00");
    private final StringBuilder input = new StringBuilder();
    double amount;

    // Helps to capture the selected operation
    private enum Op {
        NONE, DEPOSIT, WITHDRAW, BALANCE, LIMIT
    }

    private Op pendingOp = Op.NONE;

    // Initialises the objects for each account
    Check newCheck = new Check();
    Savings newSavings = new Savings();
    Fixed newFixed = new Fixed();
    NetSavings newNet = new NetSavings();

    // Helps with the grid
    public AtmPanel() {
        JPanel generalPanel = new JPanel(new GridLayout(2, 1, 8, 8));

        setLayout(new BorderLayout(8, 8));
        setBackground(Color.LIGHT_GRAY);

        // Infobar account name and balance
        JPanel infoBar = new JPanel(new GridLayout(1, 4, 8, 8));
        infoBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoBar.add(nameLabel);
        infoBar.add(account);
        infoBar.add(balanceLabel);
        infoBar.add(limitLabel);
        add(infoBar, BorderLayout.NORTH);

        // Creates a main panel for a manageble layout
        JPanel mainPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Creates a display area
        JPanel displayPanel = new JPanel(new GridLayout(1, 1, 0, 0));
        displayPanel.add(display);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setFont(display.getFont().deriveFont(14f));
        display.setBackground(Color.lightGray);
        display.setText("Welcome! Please select an account and operation");

        mainPanel.add(displayPanel);

        // Accounts buttons
        JPanel accountPanel = new JPanel(new GridLayout(4, 1, 5, 5));
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
        JPanel keypad = new JPanel(new GridLayout(5, 3, 1, 1));
        keypad.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 30));
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
        JButton btnD = new JButton(".");
        JButton btnE = new JButton("Enter");
        JButton btnL = new JButton("Limit");

        // Adds each pressed button to a string
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
        btnD.addActionListener(digitListener);

        // Resets display
        btnC.addActionListener(e -> {
            input.setLength(0);
            display.setText("");
        });

        btnE.addActionListener(e -> {
            if (input.length() == 0)
                return;
            try {
                amount = Double.parseDouble(input.toString());

                // Confirms the operation Deposit or Withdrawal
                switch (pendingOp) {
                    case DEPOSIT -> doDeposit();
                    case WITHDRAW -> doWithdraw();
                    case LIMIT -> doLimit();
                    default -> display.setText("Select an operation first.");
                }
                pendingOp = Op.NONE;

                input.setLength(0); // reset after use
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
                input.setLength(0);
                display.setText("");
            }
        });

        btnL.addActionListener(e -> {
            pendingOp = Op.LIMIT;
            display.setText("Enter daily withdrawal limit:");
            input.setLength(0);
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
        keypad.add(btn0);
        keypad.add(btnD);
        keypad.add(btnC);
        keypad.add(btnE);
        keypad.add(btnL);

        mainPanel2.add(keypad);

        // Creates button area
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnBalance = new JButton("Balance");
        JButton btnExit = new JButton("Exit");

        // Starts the op deposit and capture amount
        btnDeposit.addActionListener(e -> {
            pendingOp = Op.DEPOSIT;
            display.setText("Enter deposit amount:");
            input.setLength(0);
        });
        buttonsPanel.add(btnDeposit);

        // Starts the op withdrawal and capture amount
        btnWithdraw.addActionListener(e -> {
            pendingOp = Op.WITHDRAW;
            display.setText("Enter withdraw amount:");
            display.setFont(display.getFont().deriveFont(18f));
            input.setLength(0);
        });
        buttonsPanel.add(btnWithdraw);

        // Displays balance according to selected account
        btnBalance.addActionListener(e -> display.setText(String.format("%s balance: $%,.2f", accType,
                currentAccount().getBalance())));

        buttonsPanel.add(btnBalance);
        btnExit.addActionListener(e -> System.exit(0));
        buttonsPanel.add(btnExit);
        btnExit.setForeground(Color.RED);

        mainPanel2.add(buttonsPanel);

        // Adds main panels
        generalPanel.add(mainPanel2);
        add(generalPanel);
    }

    // Setter for account type
    public void setAccType(String t) {
        accType = t;
        account.setText("Account: " + t);
        display.setText(t);
        updateBalanceLabel();
        updateLimitLabel();
    }

    // Links deposit method with UI
    private void doDeposit() {
        Account acc = currentAccount();
        acc.deposit(amount);
        display.setText(String.format("$%,.2f deposited to %s.", amount, accType));
        updateBalanceLabel();
    }

    // Links withdrawal method with UI
    private void doWithdraw() {
        Account acc = currentAccount();
        Account.WithdrawStatus st = acc.withdraw(amount);

        // Managers erros related to funds, notes available or input
        switch (st) {
            case OK -> display.setText(String.format("$%,.2f withdrawn from %s.", amount, accType));
            case NEGATIVE_AMOUNT -> JOptionPane.showMessageDialog(this,
                    "Amount must be positive.", "Invalid amount", JOptionPane.WARNING_MESSAGE);
            case INSUFFICIENT_FUNDS -> JOptionPane.showMessageDialog(this,
                    "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
            case INVALID_NOTES -> JOptionPane.showMessageDialog(this,
                    "Only $20, $50 and $100 notes available.", "Invalid notes", JOptionPane.WARNING_MESSAGE);
            case REACHED_LIMIT ->
                JOptionPane.showMessageDialog(this, "Amount above the daily limit", "Error", JOptionPane.ERROR_MESSAGE);
        }

        updateBalanceLabel();
    }

    // Helps to set the current account type after user presses it
    private Account currentAccount() {
        return switch (accType) {
            case "Check" -> newCheck;
            case "Savings" -> newSavings;
            case "Fixed" -> newFixed;
            case "Net Savings" -> newNet;
            default -> newCheck; // fallback
        };
    }

    // Updates balance in status bar according to selected account
    private void updateBalanceLabel() {
        Account acc = currentAccount();
        balanceLabel.setText(String.format("Balance: $%,.2f", acc.getBalance()));
    }

    private void doLimit() {
        Account acc = currentAccount();
        switch (accType) {
            case "Savings" -> {
                // user‑settable
                acc.setLimit(amount);
                display.setText(String.format(
                        "Daily limit set to $%,.2f for %s account.", amount, accType));
            }
            case "Net Savings" -> {
                // fixed limit defined in model
                acc.limitValue = 10000.00; // calls NetSavings.limit() :contentReference[oaicite:1]{index=1}
                display.setText(String.format(
                        "Daily limit for %s account is $%,.2f", accType, acc.limitValue));
            }
            default -> {
                // no limit feature
                display.setText("No withdrawal limit for this account.");
            }
        }
        updateBalanceLabel();
        updateLimitLabel();
    }

    // Updates balance in status bar according to selected account
    private void updateLimitLabel() {
        Account acc = currentAccount();
        limitLabel.setText(String.format("Limit: $%,.2f", acc.getLimit()));
    }

}
