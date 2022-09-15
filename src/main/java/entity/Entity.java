package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage[] up = new BufferedImage[4];
    public BufferedImage[] down = new BufferedImage[4];
    public BufferedImage[] left = new BufferedImage[4];
    public BufferedImage[] right = new BufferedImage[4];
    public String direction;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String lastDirection;
    public int frameIndex;
    public int spriteCounter;
}
