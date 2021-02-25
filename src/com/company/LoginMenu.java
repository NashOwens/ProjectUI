package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.concurrent.Flow;

public class LoginMenu extends JFrame implements ActionListener {


    private static JButton loginButton;
    private static JTextField usernameBox;

    public static JFrame createGUI(JFrame menuWindow) {


        menuWindow.setLayout(new GridLayout(3,2));

        JLabel user = new JLabel("Username: ");
        menuWindow.add(user);

        usernameBox = new JTextField("");
        menuWindow.add(usernameBox);

        JLabel pass = new JLabel("Password: ");
        menuWindow.add(pass);

        JTextField passwordBox = new JTextField("");
        menuWindow.add(passwordBox);

        loginButton = new JButton("Login");
        menuWindow.add(loginButton);

        JButton forgotButton = new JButton("Forgot?");
        menuWindow.add(forgotButton);


        return menuWindow;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

        }

    }
}
