package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public abstract class EditProduct extends JFrame implements ActionListener {

    public static JFrame createGUI(JFrame menuWindow) {
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

                EditProd(pId, pName, pPrice, pLoc, pStock);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            } finally {
                UserMenu.userMenuReturn(removeAll(menuWindow, enterID, enterProductName, enterProductPrice, enterProductLocation,
                        enterStockQuantity, ID, productName, productPrice, productLocation, stockQuantity, submit));
            }
        });

        return menuWindow;
    }
    public static JFrame removeAll(JFrame menuWindow, JLabel enterID, JLabel enterProductName, JLabel enterProductPrice,
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
    public static void EditProd(int ID, String pName, int pPrice, String pLocation, int pStock) {
        Connection conn = Main.connect();
        String sql = "";
        try {
            sql = "UPDATE products SET PRODUCT_NAME = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, pName);
            preparedStmt.setInt(2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid ID");
            System.out.println(e.toString());
        }
        try {
            sql = "UPDATE products SET PRODUCT_PRICE = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, pPrice);
            preparedStmt.setInt(2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid price");
            System.out.println(e.toString());
        }
        try {
            sql = "UPDATE products SET PRODUCT_LOCATION = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, pLocation);
            preparedStmt.setInt(2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid Location");
            System.out.println(e.toString());
        }
        try {
            sql = "UPDATE products SET PRODUCT_STOCK = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, pStock);
            preparedStmt.setInt(2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid Stock Amount");
            System.out.println(e.toString());
        }
         finally {
            JOptionPane.showMessageDialog(null, "Product Details Changed!");
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
