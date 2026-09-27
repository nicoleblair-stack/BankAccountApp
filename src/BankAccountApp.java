import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ==========================================
// 1. THE BANK ACCOUNT CLASS (Business Logic)
// ==========================================
class BankAccount {
    private double balance;

    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;
    }

    // Standard annual compound interest formula: A = P(1 + r)^t
    public void accrueInterest(double annualRate, int years) {
        double rateDecimal = annualRate / 100.0;
        this.balance = this.balance * Math.pow(1 + rateDecimal, years);
    }

    public double getBalance() {
        return this.balance;
    }
}

// ==========================================
// 2. THE GUI APPLICATION ENVIRONMENT
// ==========================================
public class BankAccountApp extends JFrame {

    // UI Component Definitions
    private JTextField depositField;
    private JTextField rateField;
    private JTextField yearsField;
    private JLabel balanceLabel;
    private JButton calculateButton;

    public BankAccountApp() {
        // Configure Frame Properties matching the assignment layout requirements
        setTitle("Balance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 240); // Adjusted size to ensure each input takes up exactly one row
        setLayout(new GridLayout(5, 1, 5, 5)); // 5 rows: 3 inputs, 1 button, 1 display

        // Row 1: Initial Deposit Input
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel1.add(new JLabel("Initial Deposit:"));
        depositField = new JTextField(10);
        panel1.add(depositField);
        add(panel1);

        // Row 2: Annual Interest Rate Input
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(new JLabel("Annual Interest Rate:"));
        rateField = new JTextField(10);
        panel2.add(rateField);
        add(panel2);

        // Row 3: Number of Years Input
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel3.add(new JLabel("Number of Years:"));
        yearsField = new JTextField(10);
        panel3.add(yearsField);
        add(panel3);

        // Row 4: Action Execution Trigger Button
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculateButton = new JButton("Calculate New Balance");
        panel4.add(calculateButton);
        add(panel4);

        // Row 5: Dynamic Balance Outcome Output Text
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balanceLabel = new JLabel("balance: 0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel5.add(balanceLabel);
        add(panel5);

        // Connect the Button Action Listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract input numeric values from text fields
                    double initialDeposit = Double.parseDouble(depositField.getText());
                    double interestRate = Double.parseDouble(rateField.getText());
                    int years = Integer.parseInt(yearsField.getText());

                    // Instantiate the requested BankAccount object
                    BankAccount account = new BankAccount(initialDeposit);

                    // Run the calculation logic
                    account.accrueInterest(interestRate, years);

                    // Update the GUI display text seamlessly
                    balanceLabel.setText(String.format("balance: %.2f", account.getBalance()));

                } catch (NumberFormatException ex) {
                    // Input Validation Safeguard
                    JOptionPane.showMessageDialog(BankAccountApp.this,
                            "Please fill in all input boxes with valid numbers.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Main running execution thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankAccountApp().setVisible(true);
            }
        });
    }
}