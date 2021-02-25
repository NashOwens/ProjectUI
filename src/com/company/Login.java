package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {
    public static int attempt = 0;
    public static String[] login(String[] details, Connection conn) {
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
                    System.out.println("Correct Login details!\nHello " + username);
                    success = true;
                }
                if (success) {
                    break;
                }
            }
            if (!success) {
                attempt+=1;
                if(attempt == 3){
                    System.out.println("Login Failed");
                    System.exit(0);
                }
                else {
                    System.out.println("Incorrect User Code\nYou have " + (3 - attempt) + " more attempt(s)");
                 //   login(details); //when user logs in again takes them to the user menu not the admin menu
                }
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

