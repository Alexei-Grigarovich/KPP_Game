package sample;

import javafx.scene.image.ImageView;

import java.io.File;

public class Tiles {
    public static int TileWidth = 64;
    public static File TileGrass;
    public static File TileTank;
    public static File TileWall;

    public static void InitFiles() {
        TileGrass = new File("Tiles/Tile_Grass.png");
        TileTank = new File("Tiles/Tile_Tank.png");
        TileWall = new File("Tiles/Tile_Wall.png");
    }
}
