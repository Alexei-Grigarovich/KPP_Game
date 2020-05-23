package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
    private double leftBorder = 0;
    private double rightBorder = 0;
    private double topBorder = 0;
    private double downBorder = 0;
    private int i = 0;
    private int j = 0;
    private int type = -1; //0 - grass, 1 - wall, 2 - cantSpawn

    public Tile(String url, int inpType, int i, int j) {
        super(url);
        type = inpType;
        this.i = i;
        this.j = j;

        //Set borders of tile
        leftBorder = j * Tiles.getTileWidth();
        rightBorder = j * Tiles.getTileWidth() + Tiles.getTileWidth();
        topBorder = i * Tiles.getTileWidth();
        downBorder = i * Tiles.getTileWidth() + Tiles.getTileWidth();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public double getTopBorder() {
        return topBorder;
    }

    public double getDownBorder() {
        return downBorder;
    }
}
