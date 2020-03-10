package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
    public static Scene MainMenuScene;
    public static VBox MainMenuBox;
    public static Button StartButton;
    public static Button SettingsButton;
    public static Button ExitButton;
    public static Background ButtonColor;
    public static Background ButtonColorHover;
    public static Border ButtonBorder;
    public static Border ButtonBorderHover;

    public static void InitScene(Stage win, Scene GameScene) throws Exception  {
        MainMenuBox = new VBox(20);
        MainMenuScene = new Scene(MainMenuBox , 200, 250);
        StartButton = new Button("Start");
        SettingsButton = new Button("Settings");
        ExitButton = new Button("Exit");
        ButtonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), CornerRadii.EMPTY, Insets.EMPTY));
        ButtonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), CornerRadii.EMPTY, Insets.EMPTY));
        ButtonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2)));
        ButtonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2)));

        //Start Button Parameters
        StartButton.setMinSize(150, 30);
        StartButton.setBackground(ButtonColor);
        StartButton.setBorder(ButtonBorder);
        StartButton.setOnMouseEntered(e->{
            StartButton.setBorder(ButtonBorderHover);
            StartButton.setBackground(ButtonColorHover);
        });
        StartButton.setOnMouseExited(e->{
            StartButton.setBorder(ButtonBorder);
            StartButton.setBackground(ButtonColor);
        });
        StartButton.setOnAction(e->{
            System.out.println("Pressed start");
            Controller.InitKeyControl();

            //Render
            try {
                Game.RenderScene();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Main.backProc.start();

            win.setScene(GameScene);
            win.setX(550);
            win.setY(200);
        });


        //Settings Button Parameters
        SettingsButton.setMinSize(150, 30);
        SettingsButton.setBackground(ButtonColor);
        SettingsButton.setBorder(ButtonBorder);
        SettingsButton.setOnMouseEntered(e->{
            SettingsButton.setBorder(ButtonBorderHover);
            SettingsButton.setBackground(ButtonColorHover);
        });
        SettingsButton.setOnMouseExited(e->{
            SettingsButton.setBorder(ButtonBorder);
            SettingsButton.setBackground(ButtonColor);
        });
        SettingsButton.setOnAction(e->{
            System.out.println("Pressed settings");
        });


        //Exit Button Parameters
        ExitButton.setMinSize(150, 30);
        ExitButton.setBackground(ButtonColor);
        ExitButton.setBorder(ButtonBorder);
        ExitButton.setOnMouseEntered(e->{
            ExitButton.setBorder(ButtonBorderHover);
            ExitButton.setBackground(ButtonColorHover);
        });
        ExitButton.setOnMouseExited(e->{
            ExitButton.setBorder(ButtonBorder);
            ExitButton.setBackground(ButtonColor);
        });
        ExitButton.setOnAction(e->{
            System.out.println("Pressed exit");
            win.close();
        });

        //Menu
        MainMenuBox.getChildren().addAll(StartButton, SettingsButton, ExitButton);
        MainMenuBox.setAlignment(Pos.CENTER);
        MainMenuBox.setBackground(new Background(new BackgroundFill(Color.rgb(95, 122, 111), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
