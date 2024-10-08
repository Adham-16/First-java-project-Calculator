package Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // إضافة تباعد بين العناصر

        // إنشاء حقل الإدخال
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 40)); // تغيير حجم الخط داخل حقل الإدخال إلى 30
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.LIGHT_GRAY);
        display.setForeground(Color.BLACK);
        display.setPreferredSize(new Dimension(400, 100)); // تغيير ارتفاع حقل الإدخال إلى 100 بكسل
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10)); // تباعد بين الأزرار

        // إضافة أزرار الأرقام
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 40)); // تغيير حجم الخط إلى 20
            button.setBackground(Color.CYAN); // تغيير لون الخلفية
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        JButton button0 = new JButton("0");
        button0.setFont(new Font("Arial", Font.BOLD, 40)); // تغيير حجم الخط إلى 20
        button0.setBackground(Color.CYAN);
        button0.addActionListener(this);
        buttonPanel.add(button0);

        // إضافة أزرار العمليات
        String[] operations = {"+", "-", "x", "/", "C", "="};
        for (String op : operations) {
            JButton button = new JButton(op);
            button.setFont(new Font("Arial", Font.BOLD, 40)); // تغيير حجم الخط إلى 20
            button.setBackground(Color.ORANGE); // تغيير لون العمليات
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                display.setText(display.getText() + command);
            } 
            else if (command.equals("C")) {
                display.setText("");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
            } 
            else if (command.equals("=")) {
                secondNumber = Double.parseDouble(display.getText());
                double result = calculate(firstNumber, secondNumber, operator);
                display.setText(String.valueOf(result));
                operator = "";
            } 
            else {
                firstNumber = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
            display.setText("");
        }
    }

    private double calculate(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "x":
                return first * second;
            case "/":
                if (second != 0) {
                    return first / second;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator(); // تشغيل التطبيق
    }
}
