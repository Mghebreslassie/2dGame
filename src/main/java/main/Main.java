package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setTitle("2D Adventure Game");

        GamePanel gp = new GamePanel();
        j.add(gp);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        gp.setupGame();
        gp.startGameThrad();
    }
}
