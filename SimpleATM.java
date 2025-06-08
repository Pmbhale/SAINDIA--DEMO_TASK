import java.awt.*;
import java.awt.event.*;

public class SimpleATM extends Frame implements ActionListener {

    Label l1, l2, message;
    TextField tf1, tf2;
    Button login;

    // Dummy ATM data
    String card = "1234";
    String pin = "0000";
    double balance = 1000;

    SimpleATM() {
        setTitle("ATM Login");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window

        l1 = new Label("Card Number:");
        l1.setBounds(30, 50, 100, 20);
        add(l1);

        tf1 = new TextField();
        tf1.setBounds(140, 50, 120, 20);
        add(tf1);

        l2 = new Label("PIN:");
        l2.setBounds(30, 90, 100, 20);
        add(l2);

        tf2 = new TextField();
        tf2.setBounds(140, 90, 120, 20);
        tf2.setEchoChar('*'); // Hide PIN
        add(tf2);

        login = new Button("Login");
        login.setBounds(100, 130, 100, 30);
        login.addActionListener(this);
        add(login);

        message = new Label();
        message.setBounds(50, 170, 200, 20);
        add(message);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String inputCard = tf1.getText();
        String inputPin = tf2.getText();

        if (inputCard.equals(card) && inputPin.equals(pin)) {
            showMenu(); // Go to ATM menu
            dispose(); // Close login screen
        } else {
            message.setText("Invalid card or PIN");
        }
    }

    void showMenu() {
        Frame menu = new Frame("ATM Menu");
        menu.setSize(300, 250);
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);

        Button check = new Button("Check Balance");
        check.setBounds(80, 50, 140, 30);
        menu.add(check);

        Button deposit = new Button("Deposit");
        deposit.setBounds(80, 90, 140, 30);
        menu.add(deposit);

        Button withdraw = new Button("Withdraw");
        withdraw.setBounds(80, 130, 140, 30);
        menu.add(withdraw);

        Button exit = new Button("Exit");
        exit.setBounds(80, 170, 140, 30);
        menu.add(exit);

        menu.setVisible(true);

        // Actions
        check.addActionListener(ae -> showMessage(menu, "Balance: ₹" + balance));
        deposit.addActionListener(ae -> {
            String amountStr = askInput(menu, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    balance += amount;
                    showMessage(menu, "Deposited ₹" + amount);
                } else {
                    showMessage(menu, "Enter valid amount!");
                }
            } catch (Exception ex) {
                showMessage(menu, "Invalid number!");
            }
        });
         
        withdraw.addActionListener(ae -> {
            String amountStr = askInput(menu, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    showMessage(menu, "Withdrew ₹" + amount);
                } else {
                    showMessage(menu, "Insufficient balance!");
                }
            } catch (Exception ex) {
                showMessage(menu, "Invalid number!");
            }
        });
    
        exit.addActionListener(ae -> {
            menu.dispose();
        });

        menu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                menu.dispose();
            }
        });
    }

    void showMessage(Frame parent, String msg) {
        Dialog d = new Dialog(parent, "Message", true);
        d.setSize(250, 100);
        d.setLayout(new FlowLayout());
        d.add(new Label(msg));
        Button ok = new Button("OK");
        ok.addActionListener(e -> d.setVisible(false));
        d.add(ok);
        d.setLocationRelativeTo(parent);
        d.setVisible(true);
    }

    String askInput(Frame parent, String prompt) {
        Dialog d = new Dialog(parent, "Input", true);
        d.setSize(300, 120);
        d.setLayout(new FlowLayout());

        Label label = new Label(prompt);
        TextField inputField = new TextField(10);
        Button ok = new Button("OK");

        final String[] input = new String[1];

        ok.addActionListener(e -> {
            input[0] = inputField.getText();
            d.setVisible(false);
        });

        d.add(label);
        d.add(inputField);
        d.add(ok);
        d.setLocationRelativeTo(parent);
        d.setVisible(true);

        return input[0];
    }

    public static void main(String[] args) {
        new SimpleATM();
    }
}
/* Features Of this Project 
 IT BASICALLY PROVIDES A LOGIN PAGE FOR USER 
 ALLOWS AUTHORIZE PERSON TO LOG IN
 AND PERFORM OPERATIONS SUCH AS 
 1 CHECK BALANCE 
 2 DEPOSIT MONEY 
 3 WITHDROW 
 4 EXIT
THANKS */