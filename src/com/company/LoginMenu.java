package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;



public abstract class LoginMenu extends JFrame implements ActionListener {


    private static JButton loginButton;
    private static JTextField usernameBox, passwordBox;
    public static Connection conn = Main.connect();

    public static JFrame createGUI(JFrame menuWindow, Connection conn) {

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000,1000);
        menuWindow.setVisible(true);

        JLabel user = new JLabel("Username: ");
        menuWindow.add(user);

        usernameBox = new JTextField("", 20);
        menuWindow.add(usernameBox);

        JLabel pass = new JLabel("Password: ");
        menuWindow.add(pass);

        passwordBox = new JTextField("", 20);
        menuWindow.add(passwordBox);

        loginButton = new JButton("Login");
        menuWindow.add(loginButton);

        JButton forgotButton = new JButton("Forgot?");
        menuWindow.add(forgotButton);

        loginButton.addActionListener(e -> {
            if (e.getSource() == loginButton) {
                try {
                    String user1 = usernameBox.getText();
                    String pass1 = passwordBox.getText();
                    String[] details = userDetails(user1, pass1);
                    Login.login(details, conn, removeAll(menuWindow, user, pass, usernameBox, passwordBox, loginButton, forgotButton));

                } catch (NullPointerException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            }
        });

        return menuWindow;
    }
    public static JFrame removeAll(JFrame menuWindow, JLabel user, JLabel pass, JTextField usernameBox, JTextField passwordBox, JButton loginButton, JButton forgotButton) {
        menuWindow.remove(user);
        menuWindow.remove(pass);
        menuWindow.remove(usernameBox);
        menuWindow.remove(passwordBox);
        menuWindow.remove(loginButton);
        menuWindow.remove(forgotButton);
        return menuWindow;
    }

    public static String[] userDetails(String user, String pass) {
        return new String[]{user, pass};
    }
}


