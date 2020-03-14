package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
    public static int sceneWide = 200, sceneHeight = 250;
    public static Scene mainMenuScene = null;
    public static VBox mainMenuBox = null;
    public static Button startButton = null;
    public static Button settingsButton = null;
    public static Button exitButton = null;
    public static Background buttonColor = null;
    public static Background buttonColorHover = null;
    public static Border buttonBorder = null;
    public static Border buttonBorderHover = null;

    public static void initScene(Stage win) throws Exception  {
        mainMenuBox = new VBox(20);
        mainMenuScene = new Scene(mainMenuBox , sceneWide, sceneHeight);
        startButton = new Button("Start");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");
        buttonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), CornerRadii.EMPTY, Insets.EMPTY));
        buttonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), CornerRadii.EMPTY, Insets.EMPTY));
        buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2)));
        buttonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2)));

        win.setScene(mainMenuScene);

        //Start Button Parameters
        startButton.setMinSize(150, 30);
        startButton.setBackground(buttonColor);
        startButton.setBorder(buttonBorder);
        startButton.setOnMouseEntered(e->{
            startButton.setBorder(buttonBorderHover);
            startButton.setBackground(buttonColorHover);
        });
        startButton.setOnMouseExited(e->{
            startButton.setBorder(buttonBorder);
            startButton.setBackground(buttonColor);
        });
        startButton.setOnAction(e->{
            System.out.println("Pressed start");

            Controller.initKeyControl(win);

            //Render
            try {
                Game.renderScene();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Main.backProc.isWork = true;

            win.setScene(Game.gameScene);
            win.setX(550);
            win.setY(200);
        });


        //Settings Button Parameters
        settingsButton.setMinSize(150, 30);
        settingsButton.setBackground(buttonColor);
        settingsButton.setBorder(buttonBorder);
        settingsButton.setOnMouseEntered(e->{
            settingsButton.setBorder(buttonBorderHover);
            settingsButton.setBackground(buttonColorHover);
        });
        settingsButton.setOnMouseExited(e->{
            settingsButton.setBorder(buttonBorder);
            settingsButton.setBackground(buttonColor);
        });
        settingsButton.setOnAction(e->{
            System.out.println("Pressed settings");

            win.setScene(Settings.settingsScene);
        });


        //Exit Button Parameters
        exitButton.setMinSize(150, 30);
        exitButton.setBackground(buttonColor);
        exitButton.setBorder(buttonBorder);
        exitButton.setOnMouseEntered(e->{
            exitButton.setBorder(buttonBorderHover);
            exitButton.setBackground(buttonColorHover);
        });
        exitButton.setOnMouseExited(e->{
            exitButton.setBorder(buttonBorder);
            exitButton.setBackground(buttonColor);
        });
        exitButton.setOnAction(e->{
            System.out.println("Pressed exit");
            win.close();
        });

        //Menu
        mainMenuBox.getChildren().addAll(startButton, settingsButton, exitButton);
        mainMenuBox.setAlignment(Pos.CENTER);
        mainMenuBox.setBackground(new Background(new BackgroundFill(Color.rgb(95, 122, 111), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
