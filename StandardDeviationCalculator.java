import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandardDeviationCalculator extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;

    public StandardDeviationCalculator() {
        createView();

        setTitle("Standard Deviation Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel instructionLabel = new JLabel("Enter numbers separated by commas:");
        panel.add(instructionLabel);

        inputField = new JTextField(20);
        panel.add(inputField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonActionListener());
        panel.add(calculateButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));
    }

    private class CalculateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText();
            if (inputText.trim().isEmpty()) {
                resultArea.setText("Empty dataset");
                return;
            }

            String[] numberStrings = inputText.split(",");
            if (numberStrings.length == 0) {
                resultArea.setText("No observations entered");
                return;
            }

            double[] numbers = new double[numberStrings.length];

            try {
                for (int i = 0; i < numberStrings.length; i++) {
                    numbers[i] = toDouble(numberStrings[i].trim());
                }

                if (numbers.length == 1) {
                    resultArea.setText("0\nStandard deviation is 0 for a single observation");
                } else if (areAllZeros(numbers)) {
                    resultArea.setText("0\nStandard deviation is 0 for all zeros");
                } else if (areAllIdentical(numbers)) {
                    resultArea.setText("0\nStandard deviation is 0 for identical values");
                } else {
                    double stdDev = calculateStandardDeviation(numbers);
                    resultArea.setText("Standard Deviation: " + stdDev);
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Please enter only numeric values separated by commas.");
            }
        }
    }

    private boolean areAllZeros(double[] numbers) {
        for (double num : numbers) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllIdentical(double[] numbers) {
        double firstValue = numbers[0];
        for (double num : numbers) {
            if (num != firstValue) {
                return false;
            }
        }
        return true;
    }

    private double calculateStandardDeviation(double[] numbers) {
        int n = numbers.length;
        double mean = 0.0;
        for (double num : numbers) {
            mean += num;
        }
        mean /= n;

        double sumSquareDiffs = 0.0;
        for (double num : numbers) {
            sumSquareDiffs += square(num - mean);
        }
        double variance = sumSquareDiffs / n;
        return sqrt(variance);
    }

    private double square(double x) {
        return x * x;
    }

    private double sqrt(double x) {
        double guess = x / 2;
        double epsilon = 0.0001;
        while (abs(square(guess) - x) > epsilon) {
            guess = (guess + x / guess) / 2;
        }
        return guess;
    }

    private double abs(double x) {
        return x < 0 ? -x : x;
    }

    private double toDouble(String s) throws NumberFormatException {
        double result = 0.0;
        boolean isNegative = false;
        int startIndex = 0;

        if (s.length() == 0) {
            throw new NumberFormatException();
        }

        if (s.charAt(0) == '-') {
            isNegative = true;
            startIndex = 1;
        }

        boolean decimalEncountered = false;
        double decimalDivisor = 1.0;

        for (int i = startIndex; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '.') {
                if (decimalEncountered) {
                    throw new NumberFormatException();
                }
                decimalEncountered = true;
            } else if (c >= '0' && c <= '9') {
                int digit = c - '0';
                if (decimalEncountered) {
                    decimalDivisor *= 10;
                    result = result + digit / decimalDivisor;
                } else {
                    result = result * 10 + digit;
                }
            } else {
                throw new NumberFormatException();
            }
        }

        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StandardDeviationCalculator().setVisible(true);
        });
    }
}
