package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
    private static int sceneWide = 200, sceneHeight = 250;
    private static Scene menuScene = null;
    private static VBox mainMenuBox = null;
    private static Button startButton = null;
    private static Button settingsButton = null;
    private static Button exitButton = null;
    private static Background buttonColor = null;
    private static Background buttonColorHover = null;
    private static Border buttonBorder = null;
    private static Border buttonBorderHover = null;

    public static void initScene(Stage win) {
        mainMenuBox = new VBox(20);
        menuScene = new Scene(mainMenuBox, sceneWide, sceneHeight);
        startButton = new Button("Start");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");
        buttonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), new CornerRadii(10), Insets.EMPTY));
        buttonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), new CornerRadii(10), Insets.EMPTY));
        buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2)));
        buttonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(2)));

        win.setScene(menuScene);

        //Start Button Parameters
        startButton.setMinSize(150, 30);
        startButton.setBackground(buttonColor);
        startButton.setBorder(buttonBorder);
        startButton.setOnMouseEntered(e -> {
            startButton.setBorder(buttonBorderHover);
            startButton.setBackground(buttonColorHover);
        });
        startButton.setOnMouseExited(e -> {
            startButton.setBorder(buttonBorder);
            startButton.setBackground(buttonColor);
        });
        startButton.setOnAction(e -> {
            System.out.println("Pressed start");

            //Init game controller
            Controller.initKeyControl(win);

            //Render game scene
            try {
                Game.renderScene();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //Back thread start work
            Main.getBackProc().setWork(true);

            //Set window pos
            win.setScene(Game.getGameScene());
            win.setX(550);
            win.setY(200);
        });


        //Settings Button Parameters
        settingsButton.setMinSize(150, 30);
        settingsButton.setBackground(buttonColor);
        settingsButton.setBorder(buttonBorder);
        settingsButton.setOnMouseEntered(e -> {
            settingsButton.setBorder(buttonBorderHover);
            settingsButton.setBackground(buttonColorHover);
        });
        settingsButton.setOnMouseExited(e -> {
            settingsButton.setBorder(buttonBorder);
            settingsButton.setBackground(buttonColor);
        });
        settingsButton.setOnAction(e -> {
            System.out.println("Pressed settings");

            win.setScene(Settings.getSettingsScene());
        });


        //Exit Button Parameters
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
            System.out.println("Pressed exit");
            win.close();
        });


        //Adding components
        mainMenuBox.getChildren().addAll(startButton, settingsButton, exitButton);
        mainMenuBox.setAlignment(Pos.CENTER);
        mainMenuBox.setBackground(new Background(new BackgroundFill(Color.rgb(176, 219, 201), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static int getSceneWide() {
        return sceneWide;
    }

    public static int getSceneHeight() {
        return sceneHeight;
    }

    public static Scene getMenuScene() {
        return menuScene;
    }
}
