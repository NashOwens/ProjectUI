package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;



public abstract class LoginMenu extends JFrame implements ActionListener {


    private static JButton loginButton;
    private static JTextField usernameBox, passwordBox;
    private static Connection conn = Main.connect();

    public static JFrame createGUI(JFrame menuWindow) {

        menuWindow.setLayout(new GridLayout(3, 2));

        JLabel user = new JLabel("Username: ");
        menuWindow.add(user);

        usernameBox = new JTextField("");
        menuWindow.add(usernameBox);

        JLabel pass = new JLabel("Password: ");
        menuWindow.add(pass);

        passwordBox = new JTextField("");
        menuWindow.add(passwordBox);

        loginButton = new JButton("Login");
        menuWindow.add(loginButton);
        loginButton.addActionListener(e -> {
            if (e.getSource() == loginButton) {
                try {
                    String user1 = usernameBox.getText();
                    String pass1 = passwordBox.getText();
                    System.out.println(user1);
                    System.out.println(pass1);
                    String[] details = userDetails(user1, pass1);
                    Login.login(details, conn);
                } catch (NullPointerException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            } else {
                System.out.println("meme");
            }
        });

        JButton forgotButton = new JButton("Forgot?");
        menuWindow.add(forgotButton);

        return menuWindow;
    }

    public static String[] userDetails(String user, String pass) {
        return new String[]{user, pass};
    }
}


