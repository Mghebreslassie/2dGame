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
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 20;
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

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);

            pickUpObject(objIndex);
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
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

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.objects[i].name;
            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.objects[i] = null;
                    System.out.println("key: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.objects[i] = null;
//                        gp.objects[i].collision = false;
                        hasKey--;
                    }
                    System.out.println("key: " + hasKey);
                    break;

                case "Boots":
                    gp.playSE(2);
                    gp.objects[i] = null;
                    speed *= 2;
                    break;
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

        //draw character in middle of screen
        //x should be halfway from left side and half way from down
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
