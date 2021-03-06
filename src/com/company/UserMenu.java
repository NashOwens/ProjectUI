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

    public static JFrame createGUI(JFrame menuWindow) {

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1002);

        deleteProduct = new JButton("Delete Product");
        menuWindow.add(deleteProduct);

        addProduct = new JButton("Add Product");
        menuWindow.add(addProduct);

        JButton editProduct = new JButton("Edit Product");
        menuWindow.add(editProduct);

        JButton searchProduct = new JButton("Search Product");
        menuWindow.add(searchProduct);

        JButton viewProduct = new JButton("View Product");
        menuWindow.add(viewProduct);

        deleteProduct.addActionListener(e -> {
            if (e.getSource() == deleteProduct) {
                try {
                    DeleteProduct.productDelSelc(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        addProduct.addActionListener(e -> {
            if (e.getSource() == addProduct) {
                try {
                    AddProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        editProduct.addActionListener(e -> {
            if (e.getSource() == editProduct) {
                try {
                    EditProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        viewProduct.addActionListener(e -> {
            if (e.getSource() == viewProduct) {
                try {
                    ViewProducts.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        searchProduct.addActionListener(e -> {
            if (e.getSource() == searchProduct) {
                try {
                    SearchProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        return menuWindow;
    }
    public static JFrame userMenuReturn(JFrame menuWindow) {
        return UserMenu.createGUI(menuWindow);
    }

    public static JFrame removeAll(JFrame menuWindow, JButton deleteProduct, JButton addProduct, JButton editProduct, JButton searchProduct, JButton viewProduct){
        menuWindow.remove(deleteProduct);
        menuWindow.remove(addProduct);
        menuWindow.remove(editProduct);
        menuWindow.remove(searchProduct);
        menuWindow.remove(viewProduct);
        return menuWindow;

    }
}
