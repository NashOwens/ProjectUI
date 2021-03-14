package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static java.lang.Integer.parseInt;

public abstract class addUser extends JFrame implements ActionListener {

    // Sets the UI for the appropriate class
    private static final String[] roles = {"Employee", "Admin"};

    public static void createGUI(JFrame menuWindow) {
        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1001);

        JLabel enterUserName = new JLabel("Enter Username:");
        menuWindow.add(enterUserName);

        JTextField uName = new JTextField("", 15);
        menuWindow.add(uName);

        JLabel enterPass = new JLabel("Enter Password:");
        menuWindow.add(enterPass);

        JTextField uPass = new JTextField("", 15);
        menuWindow.add(uPass);

        JComboBox uRole = new JComboBox(roles);
        uRole.setSelectedIndex(0);
        menuWindow.add(uRole);

        JButton submit = new JButton("Submit");
        menuWindow.add(submit);

        submit.addActionListener(e -> {
            try {
                String Name = uName.getText();
                String Pass = uPass.getText();
                int Role = uRole.getSelectedIndex();

                AddProd(Name, Pass, Role);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            } finally {
                AdminMenu.createGUI(removeAll(menuWindow, enterUserName, enterPass, uName, uPass, uRole, submit));
            }

        });

    }
    // removes all the object's within the current JFrame to allow construction of new objects - effectively
    // making a new menu
    private static JFrame removeAll(JFrame menuWindow, JLabel enterUserName, JLabel enterPass, JTextField uName,
                                    JTextField uPass, JComboBox uRole, JButton submit) {
        menuWindow.remove(enterUserName);
        menuWindow.remove(enterPass);
        menuWindow.remove(uName);
        menuWindow.remove(uPass);
        menuWindow.remove(uRole);
        menuWindow.remove(submit);

        return menuWindow;
    }
    public static void AddProd( String Name, String Pass, int Role) {
        String sql = "INSERT INTO users(USERNAME,PASSWORD,ROLE) VALUES(?,?,?)";
        Connection conn = Main.connect();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Name);
            pstmt.setString(2, Pass);
            pstmt.setInt(3, Role);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "User added!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid data types!");
                System.out.println(ex.getMessage());
            }
        }

    }
}