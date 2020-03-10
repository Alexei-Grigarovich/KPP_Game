package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
    double LeftBorder = 0, RightBorder = 0, TopBorder = 0, DownBorder = 0;
    int iY = 0, jX = 0;
    int Type = -1; //0 - grass, 1 - wall, 2 - cantSpawn

    public Tile(String url, int type, int i, int j) {
        super(url);
        Type = type;
        iY = i;
        jX = j;
    }
}
