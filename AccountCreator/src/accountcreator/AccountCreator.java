/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package accountcreator;

/**
 *
 * @author Reccardo Horner
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class AccountCreator {
    private static String registeredUsername;
    private static String registeredCellphone;
    private static String registeredPassword;
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showRegistrationWindow());
    }

    private static void showRegistrationWindow() {
        
        JFrame registrationFrame = new JFrame("Registration");
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(500, 400);
        registrationFrame.setLayout(new GridLayout(8, 4));
        

        JTextField usernameField = new JTextField();
        JTextField cellphoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        registrationFrame.add(new JLabel("Create a Username:"));
        registrationFrame.add(usernameField);
        registrationFrame.add(new JLabel("Enter a South African Cellphone Number:"));
        registrationFrame.add(cellphoneField);
        registrationFrame.add(new JLabel("Create a Password:"));
        registrationFrame.add(passwordField);
        registrationFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String cellphone = cellphoneField.getText();
                String password = new String(passwordField.getPassword());

                if (validateRegistration(username, cellphone, password)) {
                    registeredUsername = username;
                    registeredCellphone = cellphone;
                    registeredPassword = password;
                    JOptionPane.showMessageDialog(registrationFrame, "Registration successful!");
                    registrationFrame.dispose();
                    showLoginWindow();
                }
            }
        });

        registrationFrame.setVisible(true);
    }

    private static boolean validateRegistration(String username, String cellphone, String password) {
        boolean isUsernameValid = username.length() <= 5 && username.contains("_");
        boolean isCellphoneValid = Pattern.matches("^\\+27\\d{9}$", cellphone);
        boolean isPasswordValid = Pattern.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", password);

        if (!isUsernameValid) {
            JOptionPane.showMessageDialog(null, "Invalid username. Must be <= 5 characters and contain an underscore.");
            return false;
        }
        if (!isCellphoneValid) {
            JOptionPane.showMessageDialog(null, "Invalid cellphone number. Must start with +27 and have 9 digits.");
            return false;
        }
        if (!isPasswordValid) {
            JOptionPane.showMessageDialog(null, "Invalid password. Must be at least 8 characters, contain a capital letter, a number, and a special character.");
            return false;
        }
        return true;
    }

    private static void showLoginWindow() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(600, 400);
        loginFrame.setLayout(new GridLayout(7, 4));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginFrame.add(new JLabel("Enter Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Enter a Password:"));
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                    // Proceed to the next step after successful login
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials. Please try again carefully attempt to enter again.");
                }
            }
        });

        loginFrame.setVisible(true);
        //
        //A.I Model used
        //OpenAI. (2023). ChatGPT [Large language model]. Version GPT-4. Available at: https://chat.openai.com/chat (Accessed on 12 April 2025).
}
}
