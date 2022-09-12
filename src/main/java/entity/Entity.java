package entity;

import java.awt.image.BufferedImage;

public class Entity {

    int x, y;
    int speed;

    public BufferedImage[] up = new BufferedImage[4];
    public BufferedImage[] down = new BufferedImage[4];
    public BufferedImage[] left = new BufferedImage[4];
    public BufferedImage[] right = new BufferedImage[4];
    public String direction;

    public String lastDirection;
    public int frameIndex;
    public int spriteCounter;
}
