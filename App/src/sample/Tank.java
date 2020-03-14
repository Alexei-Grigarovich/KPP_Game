package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;

public class Tank extends ImageView {
    public static double leftBorder = 0, rightBorder = 0, topBorder = 0, downBorder = 0;
    public static double speed = 2.0f;
    public static double rotate = 0f;
    public static double upDir = 0, downDir = 0, leftDir = 0, rightDir = 0;

    public Tank(String url) {
        super(url);
    }

    public void SpawnTank() {
        //set tank on i = 0 and j = 0
        this.setTranslateX(Game.gameSceneWidth/2 - Game.myTank.getImage().getWidth()/2);
        this.setTranslateY(Game.gameSceneHeight/2 - Game.myTank.getImage().getHeight()/2);
        Game.gamePane.setTranslateY(this.getTranslateY());
        Game.gamePane.setTranslateX(this.getTranslateX());

        //random spawn tank
        int j, i;
        boolean flag = true;
        do {
            j = (int)(Math.random() * (Game.tilesWide - 1));
            i = (int)(Math.random() * (Game.tilesHeight - 1));
            for(int k = 0; k < Game.walls.size(); k++) {
                if(!(Game.walls.get(k).jX == (int)j && Game.walls.get(k).iY == (int)i)) {
                    flag = false;
                } else {
                    flag = true;
                    break;
                }
            }
        } while(flag);
        Game.gamePane.setTranslateY(Game.gamePane.getTranslateY() - (int)i * Tiles.tileWidth);
        Game.gamePane.setTranslateX(Game.gamePane.getTranslateX() - (int)j * Tiles.tileWidth);
        System.out.println("Вы заспавнены на координатах " + i + " " + j);

        //set tank borders
        leftBorder = this.getTranslateX() + 10;
        rightBorder = this.getTranslateX() + 54;
        topBorder = this.getTranslateY() + 10;
        downBorder = this.getTranslateY() + 54;
    }
}
