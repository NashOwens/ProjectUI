package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public abstract class UserMenu extends JFrame implements ActionListener {

    private static JButton deleteProduct, addProduct, viewProduct, searchProduct, sortProduct, editProduct;
    private static JButton deleteUser, addUser, editUser;
    private static JButton exit;

    public static JFrame createGUI(JFrame menuWindow, Connection conn) {

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(100, 100);

        deleteProduct = new JButton("Delete Product");
        menuWindow.add(deleteProduct);

        addProduct = new JButton("Add Product");
        menuWindow.add(addProduct);

        deleteProduct.addActionListener(e -> {
            if (e.getSource() == deleteProduct) {
                try {
                    DeleteProduct.productDelSelc(removeAll(menuWindow, deleteProduct, addProduct), conn);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        });

        addProduct.addActionListener(e -> {
            if (e.getSource() == addProduct) {
                try {
                    AddProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        });

        return menuWindow;
    }
    public static JFrame userMenuReturn(JFrame menuWindow) {
        return UserMenu.createGUI(menuWindow, Main.connect());
    }

    public static JFrame removeAll(JFrame menuWindow, JButton deleteProduct, JButton addProduct) {
        menuWindow.remove(deleteProduct);
        menuWindow.remove(addProduct);
        return menuWindow;

    }
}
