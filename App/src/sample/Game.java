package sample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game {
    private static Stage win = null;
    private static int sceneWidth = 800;
    private static int sceneHeight = 600;
    private static int tilesWidth = 50;
    private static int tilesHeight = 50;
    private static Pane gamePane = new Pane();
    private static Pane mainGamePane = new Pane();
    private static Background mainGameBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY));
    private static Scene gameScene = null;
    private static PlayerTank playerTank = null;
    private static ArrayList<Tile> walls = null;
    private static ArrayList<Bullet> bullets = null;
    private static ArrayList<EnemyTank> enemyTanks = null;

    private static double maxChance = 0.30f;

    private static short wave = 1;
    private static int points = 0;
    private static int countEnemyTanksLast = 0;
    private static int countEnemyTanks = 0;
    private static short difficulty = 1;
    private static int countToAddEnemyTanks = 0;

    //Interface content
    private static Rectangle rectInterface = null;
    private static ProgressBar barPlayerTankHp = null;
    private static Label pointsText = null;
    private static Label enemyTanksLastText = null;
    private static Label wavesText = null;

    //Player dead interface
    private static int diedPaneWidth = 400;
    private static int diedPaneHeight = 300;
    private static Pane diedPane = null;
    private static Background diedPaneBackground = new Background(new BackgroundFill(Color.rgb(0, 204, 102, 0.7f), new CornerRadii(10), Insets.EMPTY));
    private static TextField enterNameField = null;
    private static Label diedReachedText = null;
    private static Label diedPointsText = null;
    private static Label enterNameText = null;
    private static Button exitButton = null;
    private static Background buttonColor = null;
    private static Background buttonColorHover = null;
    private static Border buttonBorder = null;
    private static Border buttonBorderHover = null;

    public static void initScene(Stage window) throws InterruptedException {
        win = window;
        mainGamePane.setBackground(mainGameBackground);
        gameScene = new Scene(mainGamePane, sceneWidth, sceneHeight);
        gameScene.getStylesheets().add((Game.class.getResource("Style.css")).toExternalForm());
        bullets = new ArrayList();
        walls = new ArrayList();
        enemyTanks = new ArrayList();
    }

    public static void initInterface() {
        rectInterface = new Rectangle();
        rectInterface.setTranslateX(0);
        rectInterface.setTranslateY(sceneHeight - 40);
        rectInterface.setHeight(40);
        rectInterface.setWidth(sceneWidth);
        rectInterface.setFill(Paint.valueOf("#164214"));
        mainGamePane.getChildren().add(rectInterface);

        barPlayerTankHp = new ProgressBar();
        barPlayerTankHp.setTranslateX(10);
        barPlayerTankHp.setTranslateY(sceneHeight - 33);
        barPlayerTankHp.setMinSize(200, 26);
        barPlayerTankHp.setProgress(1f);
        barPlayerTankHp.setStyle("-fx-accent: green");
        barPlayerTankHp.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        mainGamePane.getChildren().add(barPlayerTankHp);

        pointsText = new Label("");
        points = 0;
        pointsText.setText("Убийств: " + points);
        pointsText.setTextAlignment(TextAlignment.CENTER);
        pointsText.getStyleClass().add("label30px");
        pointsText.setTranslateX(sceneWidth - 150);
        pointsText.setTranslateY(sceneHeight - 38);
        mainGamePane.getChildren().add(pointsText);

        enemyTanksLastText = new Label("");
        countEnemyTanksLast = 0;
        enemyTanksLastText.setText("Осталось врагов: " + countEnemyTanksLast);
        enemyTanksLastText.setTextAlignment(TextAlignment.CENTER);
        enemyTanksLastText.getStyleClass().add("label30px");
        enemyTanksLastText.setTranslateX(sceneWidth - 500);
        enemyTanksLastText.setTranslateY(sceneHeight - 38);
        mainGamePane.getChildren().add(enemyTanksLastText);

        wavesText = new Label("");
        wavesText.getStyleClass().add("label40px");
        wavesText.setVisible(true);
        wavesText.setTranslateX(5);
        wavesText.setTranslateY(5);
        mainGamePane.getChildren().add(wavesText);
    }

    public static void newWave() throws Exception {
        //Delete enemy tanks
        for (EnemyTank tank : enemyTanks) {
            gamePane.getChildren().remove(tank);
        }
        enemyTanks.clear();

        //Spawn new enemy tanks
        countEnemyTanks += countToAddEnemyTanks * (wave - 1);
        Game.spawnEnemyTanks(countEnemyTanks);
        Game.setCountEnemyTanksLast(countEnemyTanks);


        //Set text
        wavesText.setText("Волна " + wave);
        wave++;
    }

    public static void renderScene() throws Exception {
        mainGamePane.getChildren().add(gamePane);
        wave = 1;
        countToAddEnemyTanks = difficulty + 1;
        countEnemyTanks = (15 + difficulty * 5);

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
        playerTank = new PlayerTank(Tiles.getTileTank()[0].toURI().toURL().toString(), 50, 500f);
        playerTank.spawnTank();
        mainGamePane.getChildren().add(playerTank);

        //Interface
        Game.initInterface();
    }

    public static void renderLoadScene(String fileName) {

    }

    public static void playerDied() {
        //Pane
        diedPane = new Pane();
        diedPane.setTranslateX(sceneWidth / 2 - diedPaneWidth / 2);
        diedPane.setTranslateY(sceneHeight / 2 - diedPaneHeight / 2);
        diedPane.setMinSize(diedPaneWidth, diedPaneHeight);
        diedPane.setBackground(diedPaneBackground);


        //Text
        diedReachedText = new Label("Ты умер на " + (wave - 1)  +" волне");
        diedReachedText.getStyleClass().add("label30px");
        diedReachedText.setTranslateX(100);
        diedReachedText.setTranslateY(60);
        //
        diedPointsText = new Label("Очков: " + points);
        diedPointsText.getStyleClass().add("label30px");
        diedPointsText.setTranslateX(160);
        diedPointsText.setTranslateY(10);
        //
        enterNameText = new Label("Введите ваше имя ");
        enterNameText.getStyleClass().add("label25px");
        enterNameText.setTranslateX(10);
        enterNameText.setTranslateY(150);


        //Text field
        enterNameField = new TextField();
        enterNameField.setPromptText("(3-7) символов");
        enterNameField.setTranslateX(170);
        enterNameField.setTranslateY(150);
        enterNameField.setMaxSize(130, 30);


        //Button
        buttonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), new CornerRadii(10), Insets.EMPTY));
        buttonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), new CornerRadii(10), Insets.EMPTY));
        buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2)));
        buttonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(2)));
        //
        exitButton = new Button("Выйти");
        exitButton.getStyleClass().add("label20px");
        exitButton.setTranslateX(diedPaneWidth - 170);
        exitButton.setTranslateY(diedPaneHeight - 50);
        exitButton.setMinSize(150, 30);
        exitButton.setBackground(buttonColor);
        exitButton.setBorder(buttonBorder);
        exitButton.setOnMouseEntered(e -> {
            exitButton.setBorder(buttonBorderHover);
            exitButton.setBackground(buttonColorHover);
        });
        exitButton.setOnMouseExited(e -> {
            exitButton.setBorder(buttonBorder);
            exitButton.setBackground(buttonColor);
        });
        exitButton.setOnAction(e -> {
            if(enterNameField.getText().length() < 3 || enterNameField.getText().length() > 7 || enterNameField.getText().contains(" ")) {
                enterNameField.clear();
            } else {
                try {
                    Saves.saveEndedGame(enterNameField.getText(), points, wave - 1);
                    backToMenu(win, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        //Adding components
        diedPane.getChildren().addAll(diedPointsText, diedReachedText, enterNameText, enterNameField, exitButton);
        //
        mainGamePane.getChildren().add(diedPane);
    }

    public static void spawnBullet(Tank tank, int power, boolean fromPlayer) throws Exception {
        Bullet blt = new Bullet(Tiles.getTileBullet().toURI().toURL().toString(), tank, power, fromPlayer);
        blt.setTranslateX((tank.getRightBorder() + tank.getLeftBorder()) / 2 - blt.getImage().getWidth() / 2);
        blt.setTranslateY((tank.getDownBorder() + tank.getTopBorder()) / 2 - blt.getImage().getHeight() / 2);
        gamePane.getChildren().add(blt);
        bullets.add(blt);
    }

    public static void spawnEnemyTanks(int count) throws Exception {
        //random spawn enemy tank
        for (int num = 0; num < count; num++) {
            EnemyTank enemyTank = new EnemyTank(Tiles.getTileEnemyTank()[0].toURI().toURL().toString(), (int)((15f + 5f * (double)difficulty) * (0.75f + (1.26f - 0.75f) * Math.random())), (1000f - (double)difficulty * 100f) * (0.90f + (1.10f - 0.90f) * Math.random()));
//            System.out.println(enemyTank.getDamage() + " " + enemyTank.getReloading());
            enemyTank.spawnTank();

            gamePane.getChildren().add(enemyTank);
            enemyTanks.add(enemyTank);
        }
    }

    public static void backToMenu(Stage win, boolean saveGame) throws IOException {
        win.setScene(Menu.getMenuScene());
        win.setX(850);
        win.setY(350);

        if (saveGame) {
            Saves.saveGame(walls, enemyTanks, bullets, playerTank, (short) (wave - 1), points, difficulty, countEnemyTanksLast,Main.getBackProc().getTime());
            System.out.println("Game saved");
        }

        Main.getBackProc().setTime(0);

        playerTank.setUpDir(0);
        playerTank.setDownDir(0);
        playerTank.setLeftDir(0);
        playerTank.setRightDir(0);
        playerTank.setTankRotate(0);

        //Stop back thread
        Main.getBackProc().setWork(false);

        mainGamePane.getChildren().clear();
        gamePane.getChildren().clear();
        walls.clear();
        bullets.clear();
        enemyTanks.clear();

        System.gc();
    }

    public static int getCountEnemyTanksLast() {
        return countEnemyTanksLast;
    }

    public static void setCountEnemyTanksLast(int tanksLast) {
        countEnemyTanksLast = tanksLast;
        enemyTanksLastText.setText("Осталось врагов: " + countEnemyTanksLast);
    }

    public static void setBarPlayerTankHp(int hp) {
        barPlayerTankHp.setProgress((double)hp / 100);
    }

    public static void setDifficulty(short dif) {
        difficulty = dif;
    }

    public static void setPoints(int setPoints) {
        points = setPoints;
        pointsText.setText("Убийств: " + points);
    }

    public static int getPoints() {
        return points;
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
