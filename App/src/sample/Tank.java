package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;

public class Tank extends ImageView {
    public static double LeftBorder = 0, RightBorder = 0, TopBorder = 0, DownBorder = 0;
    public static double speed = 2.0f;
    public static double rotate = 0f;
    public static int UPdir = 0, DOWNdir = 0, LEFTdir = 0, RIGHTdir = 0;

    public Tank(String url) {
        super(url);
    }

    public void SetOnCenter() {
        this.setTranslateX(Game.GameSceneWidth/2 - Game.myTank.getImage().getWidth()/2);
        this.setTranslateY(Game.GameSceneHeight/2 - Game.myTank.getImage().getHeight()/2);
        LeftBorder = this.getTranslateX() + 10;
        RightBorder = this.getTranslateX() + 54;
        TopBorder = this.getTranslateY() + 10;
        DownBorder = this.getTranslateY() + 54;
    }
}
