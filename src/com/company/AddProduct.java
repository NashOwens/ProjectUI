package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static java.lang.Integer.parseInt;

public abstract class AddProduct extends JFrame implements ActionListener {

    // Sets the UI for the appropriate class

    public static void createGUI(JFrame menuWindow) {
        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1001);

        JLabel enterID = new JLabel("Enter ID:");
        menuWindow.add(enterID);

        JTextField ID = new JTextField("", 10);
        menuWindow.add(ID);

        JLabel enterProductName = new JLabel("Enter Product Name:");
        menuWindow.add(enterProductName);

        JTextField productName = new JTextField("", 15);
        menuWindow.add(productName);

        JLabel enterProductPrice = new JLabel("Enter Product Price:");
        menuWindow.add(enterProductPrice);

        JTextField productPrice = new JTextField("", 15);
        menuWindow.add(productPrice);

        JLabel enterProductLocation = new JLabel("Enter Product Location:");
        menuWindow.add(enterProductLocation);

        JTextField productLocation = new JTextField("", 15);
        menuWindow.add(productLocation);

        JLabel enterStockQuantity = new JLabel("Enter Stock Quantity:");
        menuWindow.add(enterStockQuantity);

        JTextField stockQuantity = new JTextField("", 15);
        menuWindow.add(stockQuantity);

        JButton submit = new JButton("Submit");
        menuWindow.add(submit);

        submit.addActionListener(e -> {
            try {
                int pId = parseInt(ID.getText());
                String pName = productName.getText();
                int pPrice = parseInt(productPrice.getText());
                String pLoc = productLocation.getText();
                int pStock = parseInt(stockQuantity.getText());

                AddProd(pId, pPrice, pStock, pName, pLoc);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            } finally {
                AdminMenu.createGUI(removeAll(menuWindow, enterID, enterProductName, enterProductPrice, enterProductLocation,
                        enterStockQuantity, ID, productName, productPrice, productLocation, stockQuantity, submit));
            }

        });

    }
    // removes all the object's within the current JFrame to allow construction of new objects - effectively making a new menu
    private static JFrame removeAll(JFrame menuWindow, JLabel enterID, JLabel enterProductName, JLabel enterProductPrice,
                                   JLabel enterProductLocation, JLabel enterStockQuantity, JTextField ID, JTextField productName,
                                   JTextField productPrice, JTextField productLocation, JTextField stockQuantity,
                                   JButton submit) {
        menuWindow.remove(enterID);
        menuWindow.remove(enterProductName);
        menuWindow.remove(enterProductPrice);
        menuWindow.remove(enterProductLocation);
        menuWindow.remove(enterStockQuantity);
        menuWindow.remove(ID);
        menuWindow.remove(productName);
        menuWindow.remove(productPrice);
        menuWindow.remove(productLocation);
        menuWindow.remove(stockQuantity);
        menuWindow.remove(submit);

        return menuWindow;
    }
    public static void AddProd(int pId, int pPrice, int pStock, String pName, String pLoc) {
        String sql = "INSERT INTO products(PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_LOCATION,PRODUCT_STOCK) VALUES(?,?,?,?,?)";
        Connection conn = Main.connect();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, pId);
            pstmt.setString(2, pName);
            pstmt.setInt(3, pPrice);
            pstmt.setString(4, pLoc);
            pstmt.setInt(5, pStock);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Item added!");

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

