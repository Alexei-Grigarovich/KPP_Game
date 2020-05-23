package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Settings {
    private static VBox settingsBox = null;
    private static Scene settingsScene = null;

    private static Slider chanceSpawnWallSlider = null;
    private static Label maxChanceText = null;

    private static Label difficultyText = null;
    private static ToggleGroup radioButtonsGroup = null;
    private static RadioButton easyRadioButton = null;
    private static RadioButton normalRadioButton = null;
    private static RadioButton hardRadioButton = null;

    public static void initScene(Stage win) {
        settingsBox = new VBox(10);
        settingsBox.setPadding(new Insets(20, 0, 0, 10));
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(176, 219, 201), CornerRadii.EMPTY, Insets.EMPTY)));

        settingsScene = new Scene(settingsBox, Menu.getSceneWide(), Menu.getSceneHeight());
        settingsScene.getStylesheets().add((Settings.class.getResource("Style.css")).toExternalForm());

        settingsScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                win.setScene(Menu.getMenuScene());
            }
        });

        //Wall spawn chance Slider
        chanceSpawnWallSlider = new Slider(15, 45, 30);
        chanceSpawnWallSlider.setMaxWidth(180);
        chanceSpawnWallSlider.setShowTickLabels(true);
        chanceSpawnWallSlider.setShowTickMarks(true);
        chanceSpawnWallSlider.setMajorTickUnit(5);
        chanceSpawnWallSlider.setMinorTickCount(4);
        chanceSpawnWallSlider.setSnapToTicks(false); //Привязка
        chanceSpawnWallSlider.setOnMouseReleased(e -> {
            Game.setMaxChance(chanceSpawnWallSlider.getValue() / 100);
        });
        //
        maxChanceText = new Label("Макс. шанс спавна стены");
        maxChanceText.setPadding(new Insets(0, 0, 0, 20));
        maxChanceText.getStyleClass().add("label15pxBlack");

        //Difficulty
        difficultyText = new Label("Сложность");
        difficultyText.setPadding(new Insets(0, 0, 0, 60));
        difficultyText.getStyleClass().add("label15pxBlack");
        //
        radioButtonsGroup = new ToggleGroup();
        //
        easyRadioButton = new RadioButton("Легкая");
        easyRadioButton.getStyleClass().add("label15pxBlack");
        easyRadioButton.setToggleGroup(radioButtonsGroup);
        //
        normalRadioButton = new RadioButton("Средняя");
        normalRadioButton.getStyleClass().add("label15pxBlack");
        normalRadioButton.setToggleGroup(radioButtonsGroup);
        //
        hardRadioButton = new RadioButton("Тяжелая");
        hardRadioButton.getStyleClass().add("label15pxBlack");
        hardRadioButton.setToggleGroup(radioButtonsGroup);
        //
        easyRadioButton.setOnAction(e -> {
            Game.setDifficulty((short) 0);
        });
        normalRadioButton.setOnAction(e -> {
            Game.setDifficulty((short) 1);
        });
        hardRadioButton.setOnAction(e -> {
            Game.setDifficulty((short) 2);
        });
        //
        normalRadioButton.setSelected(true);

        //Adding components
        settingsBox.getChildren().addAll(maxChanceText, chanceSpawnWallSlider,difficultyText, easyRadioButton, normalRadioButton, hardRadioButton);
    }

    public static Scene getSettingsScene() {
        return settingsScene;
    }
}
