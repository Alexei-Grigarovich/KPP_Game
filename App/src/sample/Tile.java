package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
    double leftBorder = 0, rightBorder = 0, topBorder = 0, downBorder = 0;
    int iY = 0, jX = 0;
    int type = -1; //0 - grass, 1 - wall, 2 - cantSpawn

    public Tile(String url, int inType, int i, int j) {
        super(url);
        type = inType;
        iY = i;
        jX = j;
    }
}
