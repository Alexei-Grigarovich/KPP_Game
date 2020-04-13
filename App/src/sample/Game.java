package sample;

import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
    private static int sceneWidth = 800;
    private static int sceneHeight = 600;
    private static int tilesWidth = 50;
    private static int tilesHeight = 50;
    private static double maxChance = 0.30f;
    private static int countEnemyTanks = 20;
    private static Pane gamePane = new Pane();
    private static Pane mainGamePane = new Pane();
    private static Background mainGameBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY));
    private static Scene gameScene = null;
    private static PlayerTank playerTank = null;
    private static ArrayList<Tile> walls = null;
    private static ArrayList<Bullet> bullets = null;
    private static ArrayList<EnemyTank> enemyTanks = null;

    public static void initScene() {
        mainGamePane.setBackground(mainGameBackground);
        mainGamePane.getChildren().add(gamePane);
        gameScene = new Scene(mainGamePane, sceneWidth, sceneHeight);
        bullets = new ArrayList();
        walls = new ArrayList();
        enemyTanks = new ArrayList();
    }

    public static void renderScene() throws Exception {
        //Map
        for (int i = 0; i < tilesHeight; i++) {
            for (int j = 0; j < tilesWidth; j++) {

                double x = Math.sqrt(i * j), half = Math.sqrt(tilesWidth * tilesHeight) / 2;
                double chance = -(Math.pow((x - half), 2)) / 400 + 0.3f;

                if (chance < maxChance / 3) {
                    chance = maxChance / 3;
                }

                if (Math.random() > chance) {
                    Tile grassCell = new Tile(Tiles.getTileGrass().toURI().toURL().toString(), 0, i, j);
                    grassCell.setTranslateX(j * Tiles.getTileWidth());
                    grassCell.setTranslateY(i * Tiles.getTileWidth());
                    gamePane.getChildren().add(grassCell);
                } else {
                    Tile wallCell = new Tile(Tiles.getTileWall().toURI().toURL().toString(), 1, i, j);
                    wallCell.setTranslateX(j * Tiles.getTileWidth());
                    wallCell.setTranslateY(i * Tiles.getTileWidth());
                    walls.add(wallCell);
                    gamePane.getChildren().add(wallCell);
                }
            }
        }
        System.out.println("Количество стен = " + walls.size());

        //Boundary barriers
        for(int j = -1;j < tilesWidth + 1; j++) {
            int i = -1;
            Tile wallCell = new Tile(Tiles.getTileWall().toURI().toURL().toString(), 1, i, j);
            wallCell.setTranslateX(j * Tiles.getTileWidth());
            wallCell.setTranslateY(i * Tiles.getTileWidth());
            walls.add(wallCell);
            gamePane.getChildren().add(wallCell);

            i = tilesHeight;
            Tile wallCell1 = new Tile(Tiles.getTileWall().toURI().toURL().toString(), 1, i, j);
            wallCell1.setTranslateX(j * Tiles.getTileWidth());
            wallCell1.setTranslateY(i * Tiles.getTileWidth());
            walls.add(wallCell1);
            gamePane.getChildren().add(wallCell1);
        }
        for (int i = 0;i < tilesHeight; i++) {
            int j = -1;
            Tile wallCell = new Tile(Tiles.getTileWall().toURI().toURL().toString(), 1, i, j);
            wallCell.setTranslateX(j * Tiles.getTileWidth());
            wallCell.setTranslateY(i * Tiles.getTileWidth());
            walls.add(wallCell);
            gamePane.getChildren().add(wallCell);

            j = tilesWidth;
            Tile wallCell1 = new Tile(Tiles.getTileWall().toURI().toURL().toString(), 1, i, j);
            wallCell1.setTranslateX(j * Tiles.getTileWidth());
            wallCell1.setTranslateY(i * Tiles.getTileWidth());
            walls.add(wallCell1);
            gamePane.getChildren().add(wallCell1);
        }

        //Player tank
        playerTank = new PlayerTank(Tiles.getTileTank().toURI().toURL().toString());
        playerTank.spawnTank();
        mainGamePane.getChildren().add(playerTank);

        //Enemy tanks
        Game.spawnEnemyTanks(countEnemyTanks);
    }

    public static void spawnBullet(Tank tank) throws Exception {
        Bullet blt = new Bullet(Tiles.getTileBullet().toURI().toURL().toString(), tank);
        blt.setTranslateX((tank.getRightBorder() + tank.getLeftBorder()) / 2 - blt.getImage().getWidth() / 2);
        blt.setTranslateY((tank.getDownBorder() + tank.getTopBorder()) / 2 - blt.getImage().getHeight() / 2);
        gamePane.getChildren().add(blt);
        bullets.add(blt);
    }

    public static void spawnEnemyTanks(int count) throws Exception {
        //random spawn enemy tank
        for (int num = 0; num < count; num++) {
            EnemyTank enemyTank = new EnemyTank(Tiles.getTileEnemyTank().toURI().toURL().toString());

            enemyTank.spawnTank();

            gamePane.getChildren().add(enemyTank);
            enemyTanks.add(enemyTank);
        }
    }

    public static void backToMenu(Stage win) {
        win.setScene(Menu.getMenuScene());
        win.setX(850);
        win.setY(350);

        playerTank.setUpDir(0);
        playerTank.setDownDir(0);
        playerTank.setLeftDir(0);
        playerTank.setRightDir(0);
        playerTank.setTankRotate(0);

        //Stop back thread
        Main.getBackProc().setWork(false);

        mainGamePane.getChildren().remove(playerTank);
        gamePane.getChildren().clear();
        walls.clear();
        bullets.clear();
        enemyTanks.clear();

        System.gc();
    }

    public static void setMaxChance(double typedMaxChance) {
        maxChance = typedMaxChance;
    }

    public static void setCountEnemyTanks(int cet) {
        countEnemyTanks = cet;
    }

    public static int getCountEnemyTanks() {
        return countEnemyTanks;
    }

    public static int getTilesWidth() {
        return tilesWidth;
    }

    public static int getTilesHeight() {
        return tilesHeight;
    }

    public static int getSceneWidth() {
        return sceneWidth;
    }

    public static int getSceneHeight() {
        return sceneHeight;
    }

    public static Scene getGameScene() {
        return gameScene;
    }

    public static ArrayList<Tile> getWalls() {
        return  walls;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static PlayerTank getPlayerTank() {
        return playerTank;
    }

    public static Pane getGamePane() {
        return gamePane;
    }
}
