package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {
    public static int attempt = 0;
    public static String[] login(String[] details, Connection conn, JFrame menuWindow) {
        String username = details[0];
        String password = details[1];
        String dataRole = null;
        try {
            String dataUsername = null;
            String dataPassword = null;
            String sql = "SELECT USERNAME, PASSWORD, ROLE FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean success = false;
            while (rs.next()) {
                dataUsername = rs.getString("USERNAME");
                dataPassword = rs.getString("PASSWORD");
                dataRole = rs.getString("ROLE");
                if (dataUsername.equals(username) && dataPassword.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Correct Login details!\nHello " + username);
                    success = true;
                }
                if (success) {
                    break;
                }
            }

            if (!success) {
                attempt+=1;
                if(attempt == 3){
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    System.exit(0);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect User Code\nYou have " + (3 - attempt) + " more attempt(s)");

                    Main.loginFailReturn(menuWindow);
                 //   login(details); //when user logs in again takes them to the user menu not the admin menu
                }
            }
            if (success) {
                UserMenu.createGUI(menuWindow, conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error! Username and/or password is incorrect.");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return new String[]{username, password, dataRole};
    }

}

