package com.company;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private String username, password;

    public static void main(String[] args) {
        JFrame temp = new JFrame();
        JFrame menuWindow = LoginMenu.createGUI(temp);
        menuWindow.setSize(400,150);
        menuWindow.setVisible(true);

    }

    public static Connection connect() {
        String fileName = "C:/sqlite/db/Test.db";
        String url = "jdbc:sqlite:" + fileName;
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
