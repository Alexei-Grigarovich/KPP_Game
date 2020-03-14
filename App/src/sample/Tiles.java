package sample;

import javafx.scene.image.ImageView;

import java.io.File;

public class Tiles {
    public static int tileWidth = 64;
    public static File tileGrass = null;
    public static File tileTank = null;
    public static File tileWall = null;

    public static void initFiles() {
        tileGrass = new File("Tiles/Tile_Grass.png");
        tileTank = new File("Tiles/Tile_Tank.png");
        tileWall = new File("Tiles/Tile_Wall.png");
    }
}
