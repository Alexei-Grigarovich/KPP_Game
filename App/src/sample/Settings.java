package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings {
    public static VBox settingsBox = null;
    public static Scene settingsScene = null;
    public static Slider slider1 = null;
    public static Label text1 = null;

    public static void initScene(Stage win) {
        settingsBox = new VBox(10);
        settingsBox.setAlignment(Pos.CENTER);

        settingsScene = new Scene(settingsBox, Menu.sceneWide, Menu.sceneHeight);

        settingsScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE) win.setScene(Menu.mainMenuScene);
        });

        slider1 = new Slider(15, 45, 30);
        slider1.setMaxWidth(180);
        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setMajorTickUnit(5);
        slider1.setMinorTickCount(4);
        slider1.setSnapToTicks(false); //Привязка
        slider1.setOnMouseReleased(e -> {
            Game.maxChance = slider1.getValue()/100;
        });

        text1 = new Label("Max chance of spawn walls");

        settingsBox.getChildren().addAll(text1, slider1);
    }
}
