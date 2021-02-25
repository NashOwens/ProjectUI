package com.company;
import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        JFrame temp = new JFrame();
        JFrame menuWindow = LoginMenu.createGUI(temp);
        menuWindow.setSize(500,300);
        menuWindow.setVisible(true);

    }
}
