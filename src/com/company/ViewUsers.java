package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public abstract class ViewUsers extends JFrame implements ActionListener {

    private static final String[] columnNames = {"ID", "USERNAME", "PASSWORD", "ROLE"};
    private static JTable table;
    private static JScrollPane scroll;

    public static void createGUI(JFrame menuWindow) {

        // Sets the UI for the appropriate class

        menuWindow.setLayout(new GridBagLayout());

        JButton returnMenu = new JButton("Return");
        menuWindow.add(returnMenu);

        returnMenu.addActionListener(e -> {
            if (e.getSource() == returnMenu){
                try {
                    if (Login.dataRole = true) {
                        AdminMenu.createGUI(removeAll(menuWindow, returnMenu, table, scroll));
                    }
                    if (Login.dataRole = false){
                        UserMenu.createGUI(removeAll(menuWindow, returnMenu, table, scroll));
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
                System.out.println(Login.dataRole);
            }
        });
        menuWindow.setSize(2000, 1001);
        menuWindow.setSize(2000, 1000);
        menuWindow.setVisible(true);

        showTableData(menuWindow);

    }
    // removes all the object's within the current JFrame to allow construction of new objects - effectively making a new menu
    public static JFrame removeAll(JFrame menuWindow, JButton returnMenu, JTable table, JScrollPane scroll) {
        menuWindow.remove(returnMenu);
        menuWindow.remove(table);
        menuWindow.remove(scroll);
        return menuWindow;
    }

    public static void showTableData(JFrame menuWindow) {

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
        try {

            Connection con = Main.connect();
            String sql = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                String Id = rs.getString("ID");
                String name = rs.getString("USERNAME");
                String pass = rs.getString("PASSWORD");
                String role = rs.getString("ROLE");
                model.addRow(new Object[]{Id, name, pass, role});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Users Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, i + " User(s) Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        menuWindow.add(scroll);
        menuWindow.setVisible(true);
        menuWindow.setSize(2000, 1001);
        menuWindow.setSize(2000, 1000);
    }
}