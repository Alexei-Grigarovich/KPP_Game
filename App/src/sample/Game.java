package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Game {
    public static int gameSceneWidth = 800, gameSceneHeight = 600;
    public static int tilesWide = 50, tilesHeight = 50;
    public static double maxChance = 0.30f;
    public static Pane gamePane = new Pane();
    public static Pane mainGamePane = new Pane();
    public static Background mainGameBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene gameScene = null;
    public static Tank myTank = null;
    public static ArrayList<Tile> walls = null;

    public static void initScene() throws MalformedURLException {
        mainGamePane.setBackground(mainGameBackground);
        mainGamePane.getChildren().add(gamePane);
        gameScene = new Scene(mainGamePane, gameSceneWidth, gameSceneHeight);
        walls = new ArrayList();
    }

    public static void renderScene() throws Exception {
        walls.clear();

        //Map
        for(int i = 0; i < tilesHeight; i++) {
            for(int j = 0; j < tilesWide; j++) {

                double x = Math.sqrt(i * j) , half = Math.sqrt(tilesWide * tilesHeight) / 2;
                double chance = -(Math.pow((x-half), 2))/400+0.3f;

                if(chance < maxChance/3) chance = maxChance/3;

                if(Math.random() > chance) {
                    Tile GrassCell = new Tile(Tiles.tileGrass.toURI().toURL().toString(), 0, i, j);
                    GrassCell.setTranslateX(j * Tiles.tileWidth);
                    GrassCell.setTranslateY(i * Tiles.tileWidth);
                    gamePane.getChildren().add(GrassCell);
                } else {
                    Tile WallCell = new Tile(Tiles.tileWall.toURI().toURL().toString(), 1, i, j);
                    WallCell.setTranslateX(j * Tiles.tileWidth);
                    WallCell.setTranslateY(i * Tiles.tileWidth);
                    walls.add(WallCell);
                    gamePane.getChildren().add(WallCell);
                }
            }
        }
        System.out.println("Количество стен = " + walls.size());

        //Boundary barrier
        for(int j = -1;j < tilesWide + 1; j++) {
            int i = -1;
            Tile WallCell = new Tile(Tiles.tileWall.toURI().toURL().toString(), 1, i, j);
            WallCell.setTranslateX(j * Tiles.tileWidth);
            WallCell.setTranslateY(i * Tiles.tileWidth);
            walls.add(WallCell);
            gamePane.getChildren().add(WallCell);
            i = tilesHeight;
            Tile WallCell1 = new Tile(Tiles.tileWall.toURI().toURL().toString(), 1, i, j);
            WallCell1.setTranslateX(j * Tiles.tileWidth);
            WallCell1.setTranslateY(i * Tiles.tileWidth);
            walls.add(WallCell1);
            gamePane.getChildren().add(WallCell1);
        }
        for(int i = 0;i < tilesHeight; i++) {
            int j = -1;
            Tile WallCell = new Tile(Tiles.tileWall.toURI().toURL().toString(), 1, i, j);
            WallCell.setTranslateX(j * Tiles.tileWidth);
            WallCell.setTranslateY(i * Tiles.tileWidth);
            walls.add(WallCell);
            gamePane.getChildren().add(WallCell);
            j = tilesWide;
            Tile WallCell1 = new Tile(Tiles.tileWall.toURI().toURL().toString(), 1, i, j);
            WallCell1.setTranslateX(j * Tiles.tileWidth);
            WallCell1.setTranslateY(i * Tiles.tileWidth);
            walls.add(WallCell1);
            gamePane.getChildren().add(WallCell1);
        }

        //Tanks
        myTank = new Tank(Tiles.tileTank.toURI().toURL().toString());
        myTank.SpawnTank();
        mainGamePane.getChildren().add(myTank);

    }

    public static void backToMenu(Stage win) {
        win.setScene(Menu.mainMenuScene);
        Main.backProc.isWork = false;
        mainGamePane.getChildren().remove(myTank);
        gamePane.getChildren().clear();
        win.setX(850);
        win.setY(350);
    }
}
