package com.company;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private String username, password;

    public static void main(String[] args) {
        JFrame temp = new JFrame();
        JFrame menuWindow = LoginMenu.createGUI(temp, connect());

    }
    public static void loginFailReturn(JFrame menuWindow) {
        LoginMenu.createGUI(menuWindow, connect());
    }

    public static Connection connect() {
        String fileName = "C:/sqlite/db/inventoryMangSys.db";
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
