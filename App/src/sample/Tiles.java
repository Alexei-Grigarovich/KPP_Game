package sample;

import java.io.File;

public class Tiles {
    private static int tileWidth = 64;
    private static File tileGrass = null;
    private static File tileTank = null;
    private static File tileEnemyTank = null;
    private static File tileWall = null;
    private static File tileBullet = null;

    public static void initFiles() {
        tileGrass = new File("Tiles/Tile_Grass.png");
        tileTank = new File("Tiles/Tile_Tank.png");
        tileWall = new File("Tiles/Tile_Wall.png");
        tileBullet = new File("Tiles/Bullet.png");
        tileEnemyTank = new File("Tiles/Tile_EnemyTank.png");
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static File getTileGrass() {
        return tileGrass;
    }

    public static File getTileTank() {
        return tileTank;
    }

    public static File getTileEnemyTank() {
        return tileEnemyTank;
    }

    public static File getTileWall() {
        return  tileWall;
    }

    public static File getTileBullet() {
        return tileBullet;
    }

}
