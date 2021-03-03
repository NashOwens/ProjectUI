package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.numberOfTrailingZeros;
import static java.lang.Integer.parseInt;

public abstract class SearchProduct extends JFrame implements ActionListener {

    public static JFrame createGUI(JFrame menuWindow) {

        menuWindow.setLayout(new GridBagLayout());

        JButton returnMenu = new JButton("Return");
        menuWindow.add(returnMenu);

        JTextField searchBar = new JTextField(30);
        menuWindow.add(searchBar);

        JButton search = new JButton("Search");
        menuWindow.add(search);


        search.addActionListener(e -> {
            if (e.getSource() == search) {
                try {
                    JInternalFrame dataArea = new JInternalFrame("Item",false, true, false, false);
                    dataArea.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    dataArea.setLayout(new GridBagLayout());
                    dataArea.setSize(20,20);
                    JScrollPane database = new JScrollPane(pSearch(searchBar));
                    database.setSize(10,5);
                    dataArea.add(database);
                    menuWindow.add(dataArea);
                    menuWindow.setSize(2000,1001);
                    menuWindow.setSize(2000,1000);
                    dataArea.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        returnMenu.addActionListener(e -> {
            if (e.getSource() == returnMenu) {
                try {
                  //  removeAll(menuWindow, searchBar, search, database, dataArea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error!");
                }
            }
        });

        menuWindow.setSize(2000,1000);
        return menuWindow;
    }
    public static String itemName(String name) {
        return name;
    }

    public static JTextArea pSearch(JTextField searchBar) {
        Connection conn = Main.connect();
        JTextArea search2 = new JTextArea();
        try {
            int searching = parseInt(searchBar.getText());

            String sql = "SELECT * FROM products WHERE ID = ?";

            PreparedStatement found = conn.prepareStatement(sql);
            found.setInt(1, searching);
            ResultSet rs = found.executeQuery();
            while(rs.next()) {
                    String id = rs.getString("ID");
                    String prodId = rs.getString("PRODUCT_ID");
                    String prodName = rs.getString("PRODUCT_NAME");
                    String prodPrice = rs.getString("PRODUCT_PRICE");
                    String prodLocation = rs.getString("PRODUCT_LOCATION");
                    String prodQuan = rs.getString("PRODUCT_STOCK");

                    search2.append(id);
                    search2.append("          \n" +prodId);
                    search2.append("          \n" +prodName);
                    search2.append("         \n£" + prodPrice);
                    search2.append("          \n" +prodLocation);
                    search2.append("          \n" +prodQuan);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return search2;
    }

    public static JFrame removeAll(JFrame menuWindow, JTextField searchBar, JButton search, JScrollPane database, JTextArea dataArea) {
        menuWindow.remove(search);
        menuWindow.remove(searchBar);
        menuWindow.remove(database);
        menuWindow.remove(dataArea);
        return menuWindow;
    }

}
