package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoadGame {
    private static int sceneWide = 340, sceneHeight = 450;
    private static Stage win = null;
    private static Scene loadGameScene = null;
    private static Pane loadGamePane = null;

    private static Background loadGameBackground = null;

    private static Background buttonColor = null;
    private static Background buttonColorHover = null;
    private static Border buttonBorder = null;
    private static Border buttonBorderHover = null;

    static class Element {
        private HBox field = null;
        private Label name = null;
        private Button loadGameButton = null;
    }

    private static ScrollPane loadGameScrollPane = null;
    private static File[] files = null;
    private static ArrayList<LoadGame.Element> items = null;
    private static VBox listBox = null;

    public static void initLoadGame(Stage window) throws IOException {
        win = window;
        loadGamePane = new Pane();
        loadGameScene = new Scene(loadGamePane, sceneWide, sceneHeight);
        loadGameScene.getStylesheets().add((GameList.class.getResource("Style.css")).toExternalForm());
        loadGameScrollPane = new ScrollPane();
        items = new ArrayList();
        listBox = new VBox(5);

        loadGameBackground = new Background(new BackgroundFill(Color.rgb(176, 219, 201), CornerRadii.EMPTY, Insets.EMPTY));
        buttonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), new CornerRadii(10), Insets.EMPTY));
        buttonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), new CornerRadii(10), Insets.EMPTY));
        buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2)));
        buttonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(2)));

        loadGamePane.setBackground(loadGameBackground);

        //Scroll Pane
        loadGameScrollPane.setVmin(0);
        loadGameScrollPane.setPrefViewportHeight(sceneHeight - 20);
        loadGameScrollPane.setPrefViewportWidth(sceneWide - 20);
        loadGameScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        loadGameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                win.setScene(Menu.getMenuScene());
                win.setX(850);
                win.setY(350);
            }
        });
        loadGameScrollPane.setContent(listBox);
        loadGamePane.getChildren().add(loadGameScrollPane);
    }

    public static void goToLoadGame() throws IOException {
        win.setScene(loadGameScene);
        win.setX(800);
        win.setY(300);

        listBox.getChildren().clear();
        items.clear();

        files = Saves.findSaves();

        if (files.length == 0) {
            Label nothing = new Label("Сохранений нет");
            nothing.getStyleClass().add("label30pxBlack");
            loadGameScrollPane.setContent(nothing);
        } else {
            loadGameScrollPane.setContent(listBox);
        }

        for (File file : files) {
            LoadGame.Element element = new LoadGame.Element();

            element.field = new HBox(10);
            element.name = new Label(file.getName());
            element.loadGameButton = new Button("Load");

            //Label
            element.name.getStyleClass().add("label30pxBlack");

            //Load button parameters
            element.loadGameButton.setBackground(buttonColor);
            element.loadGameButton.setBorder(buttonBorder);
            element.loadGameButton.getStyleClass().add("label15pxBlack");
            element.loadGameButton.setOnMouseEntered(e -> {
                element.loadGameButton.setBorder(buttonBorderHover);
                element.loadGameButton.setBackground(buttonColorHover);
            });
            element.loadGameButton.setOnMouseExited(e -> {
                element.loadGameButton.setBorder(buttonBorder);
                element.loadGameButton.setBackground(buttonColor);
            });
            element.loadGameButton.setOnAction(e -> {
                Game.renderLoadScene(element.name.getText());
            });

            element.field.getChildren().addAll(element.name, element.loadGameButton);
            listBox.getChildren().add(element.field);
        }
    }
}
