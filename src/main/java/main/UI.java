package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage image;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished;

    DecimalFormat df = new DecimalFormat("#0.0");
    double playTime;
    int messageCounter;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        image = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;
            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, gp.screenWidth / 2 - textLength / 2, gp.screenHeight / 2 - (gp.tileSize * 3));
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, gp.screenWidth / 2 - textLength, gp.screenHeight / 2 + (gp.tileSize * 2));

            gp.gameThread = null;
        } else {
            playTime += (double) 1 / 60;
            g2.drawString("Time:" + df.format(playTime), gp.tileSize * 11, 65);
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, (int) (1.6 * gp.tileSize), (int) (1.4 * gp.tileSize));

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, (int) (5 * gp.tileSize));
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
