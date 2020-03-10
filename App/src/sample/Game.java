package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Game {
    public static int GameSceneWidth = 800, GameSceneHeight = 600;
    public static int TilesWide = 50, TilesHeight = 50;
    public static Pane GamePane = null;
    public static Pane MainGamePane = null;
    public static Background MainGameBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene GameScene = null;
    public static Tank myTank = null;
    public static ArrayList<Tile> Walls = null;

    public static void InitScene() throws MalformedURLException {
        MainGamePane = new Pane();
        MainGamePane.setBackground(MainGameBackground);
        GamePane = new Pane();
        MainGamePane.getChildren().add(GamePane);
        GameScene = new Scene(MainGamePane, GameSceneWidth, GameSceneHeight);
        Walls = new ArrayList();
    }

    public static void RenderScene() throws Exception {
        //Map
        for(int i = 0; i < TilesHeight; i++) {
            for(int j = 0; j < TilesWide; j++) {
                double x = Math.sqrt(i * j) , half = Math.sqrt(TilesWide * TilesHeight) / 2;
                double chance = -(Math.pow((x-half), 2))/400+0.3f;
                if(chance < 0.1f) chance = 0.1f;
                if(Math.random() > chance) {
                    Tile GrassCell = new Tile(Tiles.TileGrass.toURI().toURL().toString(), 0, i, j);
                    GrassCell.setTranslateX(j * Tiles.TileWidth);
                    GrassCell.setTranslateY(i * Tiles.TileWidth);
                    GamePane.getChildren().add(GrassCell);
                } else {
                    Tile WallCell = new Tile(Tiles.TileWall.toURI().toURL().toString(), 1, i, j);
                    WallCell.setTranslateX(j * Tiles.TileWidth);
                    WallCell.setTranslateY(i * Tiles.TileWidth);
                    Walls.add(WallCell);
                    GamePane.getChildren().add(WallCell);
                }
            }
        }

        //Boundary barrier
        for(int j = -1;j < TilesWide + 1; j++) {
            int i = -1;
            Tile WallCell = new Tile(Tiles.TileWall.toURI().toURL().toString(), 1, i, j);
            WallCell.setTranslateX(j * Tiles.TileWidth);
            WallCell.setTranslateY(i * Tiles.TileWidth);
            Walls.add(WallCell);
            GamePane.getChildren().add(WallCell);
            i = TilesHeight;
            Tile WallCell1 = new Tile(Tiles.TileWall.toURI().toURL().toString(), 1, i, j);
            WallCell1.setTranslateX(j * Tiles.TileWidth);
            WallCell1.setTranslateY(i * Tiles.TileWidth);
            Walls.add(WallCell1);
            GamePane.getChildren().add(WallCell1);
        }
        for(int i = 0;i < TilesHeight; i++) {
            int j = -1;
            Tile WallCell = new Tile(Tiles.TileWall.toURI().toURL().toString(), 1, i, j);
            WallCell.setTranslateX(j * Tiles.TileWidth);
            WallCell.setTranslateY(i * Tiles.TileWidth);
            Walls.add(WallCell);
            GamePane.getChildren().add(WallCell);
            j = TilesWide;
            Tile WallCell1 = new Tile(Tiles.TileWall.toURI().toURL().toString(), 1, i, j);
            WallCell1.setTranslateX(j * Tiles.TileWidth);
            WallCell1.setTranslateY(i * Tiles.TileWidth);
            Walls.add(WallCell1);
            GamePane.getChildren().add(WallCell1);
        }

        //Tanks
        myTank = new Tank(Tiles.TileTank.toURI().toURL().toString());
        myTank.SetOnCenter();
        MainGamePane.getChildren().add(myTank);


    }
}
