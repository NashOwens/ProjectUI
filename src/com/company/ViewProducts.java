package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public abstract class ViewProducts extends JFrame implements ActionListener {

    private static final String[] columnNames = {"ID", "PName", "test", "test"};
    private static JTable table;
    private static JScrollPane scroll;

    public static JFrame createGUI(JFrame menuWindow) {

        menuWindow.setLayout(new GridBagLayout());

        JButton returnMenu = new JButton("Return");
        menuWindow.add(returnMenu);

        returnMenu.addActionListener(e -> {
            if (e.getSource() == returnMenu){
                try {
                    UserMenu.createGUI(removeAll(menuWindow, returnMenu, table, scroll));
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        menuWindow.setSize(2000, 1001);
        menuWindow.setSize(2000, 1000);
        menuWindow.setVisible(true);

        showTableData(menuWindow);

        return menuWindow;
    }
    public static JFrame removeAll(JFrame menuWindow, JButton returnMenu, JTable table, JScrollPane scroll) {
        menuWindow.remove(returnMenu);
        menuWindow.remove(table);
        menuWindow.remove(scroll);
        return menuWindow;
    }

    public static JFrame showTableData(JFrame menuWindow) {

        menuWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuWindow.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String Id = "";
        String name = "";
        String Price = "";
        String Loc = "";
        String Quant = "";
        try {

            Connection con = Main.connect();
            String sql = "SELECT * FROM products";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                Id = rs.getString("PRODUCT_ID");
                name = rs.getString("PRODUCT_NAME");
                Price = rs.getString("PRODUCT_PRICE");
                Loc = rs.getString("PRODUCT_LOCATION");
                Quant = rs.getString("PRODUCT_STOCK");
                model.addRow(new Object[]{Id, name, Price, Loc, Quant});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, i + " Record(s) Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        menuWindow.add(scroll);
        menuWindow.setVisible(true);
        menuWindow.setSize(2000, 1001);
        menuWindow.setSize(2000, 1000);
        return menuWindow;
    }
}

