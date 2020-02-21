package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jf = new JFrame("Double Pendulum");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DoublePendulum d = new DoublePendulum();
        jf.add(d);
        jf.setSize(1000,1000);
        jf.setVisible(true);
    }
}
