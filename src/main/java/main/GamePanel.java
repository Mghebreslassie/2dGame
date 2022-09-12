package main;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set player default position;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThrad() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000_000_000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        long start = System.currentTimeMillis();
        int counter = 0;
        while (gameThread != null) {
            long finish = System.currentTimeMillis();
            long delta = finish - start;
            if (delta < 1000) {
                counter++;
            } else {
                System.out.println("FPS: " + counter);
                counter = 0;
                start = System.currentTimeMillis();
            }
            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime /= 1000_000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextDrawTime += drawInterval;
        }

    }

    public void update() {

        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            System.out.println("hello");
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
