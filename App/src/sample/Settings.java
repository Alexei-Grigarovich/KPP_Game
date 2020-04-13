package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Settings {
    private static VBox settingsBox = null;
    private static Scene settingsScene = null;
    private static Slider chanceSpawnWall = null;
    private static Label maxChanceText = null;

    public static void initScene(Stage win) {
        settingsBox = new VBox(10);
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(176, 219, 201), CornerRadii.EMPTY, Insets.EMPTY)));

        settingsScene = new Scene(settingsBox, Menu.getSceneWide(), Menu.getSceneHeight());

        settingsScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                win.setScene(Menu.getMenuScene());
            }
        });

        //Wall spawn chance Slider
        chanceSpawnWall = new Slider(15, 45, 30);
        chanceSpawnWall.setMaxWidth(180);
        chanceSpawnWall.setShowTickLabels(true);
        chanceSpawnWall.setShowTickMarks(true);
        chanceSpawnWall.setMajorTickUnit(5);
        chanceSpawnWall.setMinorTickCount(4);
        chanceSpawnWall.setSnapToTicks(false); //Привязка
        chanceSpawnWall.setOnMouseReleased(e -> {
            Game.setMaxChance(chanceSpawnWall.getValue() / 100);
        });
        //
        maxChanceText = new Label("Max chance of spawn walls");

        //Adding components
        settingsBox.getChildren().addAll(maxChanceText, chanceSpawnWall);
    }

    public static Scene getSettingsScene() {
        return settingsScene;
    }
}
