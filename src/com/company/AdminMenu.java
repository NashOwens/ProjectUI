package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AdminMenu extends JFrame implements ActionListener {

    public static JFrame createGUI(JFrame menuWindow) {

        // Sets the UI for the appropriate class

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1002);

        JButton logOut = new JButton("Logout");
        menuWindow.add(logOut);

        JButton deleteProduct = new JButton("Delete Product");
        menuWindow.add(deleteProduct);

        JButton addProduct = new JButton("Add Product");
        menuWindow.add(addProduct);

        JButton editProduct = new JButton("Edit Product");
        menuWindow.add(editProduct);

        JButton searchProduct = new JButton("Search Product");
        menuWindow.add(searchProduct);

        JButton viewProduct = new JButton("View Product");
        menuWindow.add(viewProduct);

        JButton viewUser = new JButton("View Users");
        menuWindow.add(viewUser);

        // all these buttons call their appropriate function when clicked
        // when passing through the same JFrame i pass through the remove all function to clear all objects to allow a
        // "blank slate" so to speak to create the new menu

        logOut.addActionListener(e -> {
            if (e.getSource() == logOut) {
                try {
                    Login.attempt = 0;
                    LoginMenu.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser), Main.connect());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());

                }
            }
        });

        deleteProduct.addActionListener(e -> {
            if (e.getSource() == deleteProduct) {
                try {
                    DeleteProduct.productDelSelc(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        addProduct.addActionListener(e -> {
            if (e.getSource() == addProduct) {
                try {
                    AddProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        editProduct.addActionListener(e -> {
            if (e.getSource() == editProduct) {
                try {
                    EditProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        viewProduct.addActionListener(e -> {
            if (e.getSource() == viewProduct) {
                try {
                    ViewProducts.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        searchProduct.addActionListener(e -> {
            if (e.getSource() == searchProduct) {
                try {
                    SearchProduct.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        viewUser.addActionListener(e -> {
            if (e.getSource()== viewUser){
                try {
                    ViewUsers.createGUI(removeAll(menuWindow, deleteProduct, addProduct, editProduct, searchProduct, viewProduct, logOut, viewUser));
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });

        return menuWindow;
    }
    // removes all the object's within the current JFrame to allow construction of new objects - effectively making a new menu
    private static JFrame removeAll(JFrame menuWindow, JButton deleteProduct, JButton addProduct, JButton editProduct, JButton searchProduct, JButton viewProduct, JButton logOut, JButton viewUser){
        menuWindow.remove(deleteProduct);
        menuWindow.remove(addProduct);
        menuWindow.remove(editProduct);
        menuWindow.remove(searchProduct);
        menuWindow.remove(viewProduct);
        menuWindow.remove(logOut);
        menuWindow.remove(viewUser);
        return menuWindow;

    }
}
