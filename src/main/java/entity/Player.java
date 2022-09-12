package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        lastDirection = "";
        frameIndex = 0;
        spriteCounter = 0;
    }

    public void getPlayerImage() {
        InputStream resourceAsStream = getClass().getResourceAsStream("/player/characterSheet.png");
        try {
            BufferedImage spriteSheet = ImageIO.read(resourceAsStream);
            ArrayList<BufferedImage> images = new ArrayList<>();
            for (int i = 0; i < (16 * 16); i += 16) {
                images.add(spriteSheet.getSubimage(i, 0, 16, 16));
            }
            up[0] = images.get(8);
            up[1] = images.get(9);
            up[2] = images.get(10);
            up[3] = images.get(11);
            down[0] = images.get(0);
            down[1] = images.get(1);
            down[2] = images.get(2);
            down[3] = images.get(3);
            left[0] = images.get(12);
            left[1] = images.get(13);
            left[2] = images.get(14);
            left[3] = images.get(15);
            right[0] = images.get(4);
            right[1] = images.get(5);
            right[2] = images.get(6);
            right[3] = images.get(7);
            //down right up left
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update() {
        if (keyH.downPressed || keyH.upPressed || keyH.leftPressed
                || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter == 8) {
                frameIndex++;
                if (frameIndex == 4) {
                    frameIndex = 0;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":

                if (!lastDirection.equals("up")) {
                    frameIndex = 0;
                }
                image = up[frameIndex];
                lastDirection = "up";
                break;
            case "down":
                if (!lastDirection.equals("down")) {
                    frameIndex = 0;
                }
                image = down[frameIndex];
                lastDirection = "down";
                break;
            case "right":
                if (!lastDirection.equals("right")) {
                    frameIndex = 0;
                }
                image = right[frameIndex];
                lastDirection = "right";
                break;
            case "left":
                if (!lastDirection.equals("left")) {
                    frameIndex = 0;
                }
                image = left[frameIndex];
                lastDirection = "left";
                break;
            default:
                image = down[0];
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
