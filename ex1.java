import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ex1 {
    private JFrame frame;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private String savedPassword = null;
    private String name = ""; // Variable to store the entered password

    public ex1() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Lock Class");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new NumberButtonListener());
            buttonPanel.add(button);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            passwordField.setText("");
            name = ""; // Reset the name variable
        });
        passwordField = new JPasswordField(10);
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        statusLabel = new JLabel("Enter Password");

        bottomPanel.add(clearButton);
        bottomPanel.add(passwordField);
        bottomPanel.add(enterButton);
        bottomPanel.add(statusLabel);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            name += source.getText(); // Append the clicked number to the name
            passwordField.setText(name); // Update the passwordField with the current name
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passwordField.getPassword());

            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else {
                if (enteredPassword.equals(savedPassword)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }

            passwordField.setText("");
            name = ""; // Reset the name variable after checking the password
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ex1::new);
    }
}
