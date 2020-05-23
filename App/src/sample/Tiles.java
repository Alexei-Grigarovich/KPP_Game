package sample;

import java.io.File;
import java.util.ArrayList;

public class Tiles {
    private static int tileWidth = 64;
    private static File tileGrass = null;
    private static File tileTank[] = new File[3];
    private static File tileEnemyTank[] = new File[3];
    private static File tileDeadEnemyTank[] = new File[3];
    private static File tileWall = null;
    private static File tileBullet = null;

    public static void initFiles() {
        tileGrass = new File("Tiles/Tile_Grass.png");
        tileWall = new File("Tiles/Tile_Wall.png");
        tileBullet = new File("Tiles/Bullet.png");
        for (int i = 0; i < 3; i++) {
            tileTank[i] = new File("Tiles/playerTank/Tile_Tank_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            tileEnemyTank[i] = new File("Tiles/enemyTank/Tile_EnemyTank_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            tileDeadEnemyTank[i] = new File("Tiles/deadEnemyTank/Tile_DeadEnemyTank_" + i + ".png");
        }
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static File getTileGrass() {
        return tileGrass;
    }

    public static File[] getTileTank() {
        return tileTank;
    }

    public static File[] getTileEnemyTank() {
        return tileEnemyTank;
    }

    public static File[] getTileDeadEnemyTank() {
        return tileDeadEnemyTank;
    }

    public static File getTileWall() {
        return  tileWall;
    }

    public static File getTileBullet() {
        return tileBullet;
    }

}
