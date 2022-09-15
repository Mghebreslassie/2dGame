package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNums;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[6];
        mapTileNums = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMapData("/map/world01.txt");
    }

    public void getTileImage() {

        try {
            Tile tile = new Tile();
            tile.image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tiles[0] = tile;
            Tile tile2 = new Tile();
            tile2.image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1] = tile2;
            Tile tile3 = new Tile();
            tile3.image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[2] = tile3;
            Tile tile4 = new Tile();
            tile4.image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tiles[3] = tile4;
            Tile tile5 = new Tile();
            tile5.image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4] = tile5;
            Tile tile6 = new Tile();
            tile6.image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            tiles[5] = tile6;

            tile2.collision = true;
            tile3.collision = true;
            tile5.collision = true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMapData(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNums[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNums[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
