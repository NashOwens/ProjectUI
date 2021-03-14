package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class UserMenu extends JFrame implements ActionListener {

    public static JFrame createGUI(JFrame menuWindow) {

        // Sets the UI for the appropriate class

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1002);

        JButton logOut = new JButton("Logout");
        menuWindow.add(logOut);

        JButton searchProduct = new JButton("Search Product");
        menuWindow.add(searchProduct);

        JButton viewProduct = new JButton("View Product");
        menuWindow.add(viewProduct);

        // all these buttons call their appropriate function when clicked
        // when passing through the same JFrame i pass through the remove all function to clear all objects to allow a
        // "blank slate" so to speak to create the new menu

        logOut.addActionListener(e -> {
            if (e.getSource() == logOut) {
                try {
                    Login.attempt = 0;
                    LoginMenu.createGUI(removeAll(menuWindow, searchProduct, viewProduct, logOut), Main.connect());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        viewProduct.addActionListener(e -> {
            if (e.getSource() == viewProduct) {
                try {
                    ViewProducts.createGUI(removeAll(menuWindow, searchProduct, viewProduct, logOut));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        searchProduct.addActionListener(e -> {
            if (e.getSource() == searchProduct) {
                try {
                    SearchProduct.createGUI(removeAll(menuWindow, searchProduct, viewProduct, logOut));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        return menuWindow;
    }
    // returns the JFrame to the userMenu
    // removes all the object's within the current JFrame to allow construction of new objects - effectively making
    // a new menu
    private static JFrame removeAll(JFrame menuWindow, JButton searchProduct, JButton viewProduct, JButton logOut){
        menuWindow.remove(searchProduct);
        menuWindow.remove(viewProduct);
        menuWindow.remove(logOut);
        return menuWindow;
    }
}