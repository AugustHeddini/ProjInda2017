package com.company;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Created by johan on 2017-05-03.The frame is where the map, player and monsters will be displayed.
 */
public class Frame extends JFrame {

    public Frame() {

        initUI();

    }

    private void initUI() {

        setTitle("Frame");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame ex = new Frame();
            ex.setVisible(true);
        });
    }
}
