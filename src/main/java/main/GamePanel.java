package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    public final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    final int FPS = 60;
    Sound sound = new Sound();
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public AssetManager aManager = new AssetManager(this);
    public SuperObject[] objects = new SuperObject[10];
    public CollisionChecker cChecker = new CollisionChecker(this);
    TileManager tileM = new TileManager(this);
    public Player player = new Player(this, keyH);

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

    public void setupGame() {
        aManager.setObjects();
        playMusic(0);
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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                objects[i].draw(g2, this);
            }
        }
        player.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}
