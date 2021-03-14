package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public abstract class DeleteProduct extends JFrame implements ActionListener {

    // Sets the UI for the appropriate class

    private static JTextField ID;
    private static JButton Submit;

    public static void productDelSelc(JFrame menuWindow){

        menuWindow.setLayout(new GridBagLayout());
        menuWindow.setSize(2000, 1001);


        JLabel enterID = new JLabel("Enter item ID:");
        menuWindow.add(enterID);

        ID = new JTextField("", 10);
        menuWindow.add(ID);

        Submit = new JButton("Submit");
        menuWindow.add(Submit);

        Submit.addActionListener(e -> {
            if(e.getSource() == Submit) {
                try {
                    int productDel = parseInt(ID.getText());
                    deleteProduct(productDel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter an Integer ID");
                } finally {
                    AdminMenu.createGUI(removeAll(menuWindow, enterID, Submit, ID));
                }
            }
        });

    }
    // removes all the object's within the current JFrame to allow construction of new objects - effectively
    // making a new menu
    private static JFrame removeAll(JFrame menuWindow, JLabel enterID, JButton submit, JTextField ID) {
        menuWindow.remove(enterID);
        menuWindow.remove(submit);
        menuWindow.remove(ID);
        return menuWindow;
    }

    public static void deleteProduct(int productDel){
        String sql = "DELETE FROM products WHERE ID = ?";
        Connection conn = Main.connect();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, productDel);
            // execute the delete statement
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "A record has been deleted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
    }
}