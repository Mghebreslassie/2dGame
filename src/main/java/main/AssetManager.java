package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;

    }

    public void setObjects() {
        OBJ_Key key = new OBJ_Key();
        key.worldX = 23 * gp.tileSize;
        key.worldY = 7 * gp.tileSize;
        gp.objects[0] = key;

        OBJ_Key key2 = new OBJ_Key();
        key2.worldX = 23 * gp.tileSize;
        key2.worldY = 40 * gp.tileSize;
        gp.objects[1] = key2;

        OBJ_Key key3 = new OBJ_Key();
        key3.worldX = 38 * gp.tileSize;
        key3.worldY = 8 * gp.tileSize;
        gp.objects[2] = key3;

        OBJ_Door door = new OBJ_Door();
        door.worldX = 10 * gp.tileSize;
        door.worldY = 11 * gp.tileSize;
        gp.objects[3] = door;

        OBJ_Door door2 = new OBJ_Door();
        door2.worldX = 8 * gp.tileSize;
        door2.worldY = 28 * gp.tileSize;
        gp.objects[4] = door2;

        OBJ_Door door3 = new OBJ_Door();
        door3.worldX = 12 * gp.tileSize;
        door3.worldY = 22 * gp.tileSize;
        gp.objects[5] = door3;

        OBJ_Chest chest = new OBJ_Chest();
        chest.worldX = 10 * gp.tileSize;
        chest.worldY = 7 * gp.tileSize;
        gp.objects[6] = chest;

        OBJ_Boots boots = new OBJ_Boots();
        boots.worldX = 37 * gp.tileSize;
        boots.worldY = 42 * gp.tileSize;
        gp.objects[7] = boots;
    }
}
