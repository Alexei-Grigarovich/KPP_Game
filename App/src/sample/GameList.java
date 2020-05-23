package sample;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameList {
    private static int sceneWide = 330, sceneHeight = 440;
    private static Stage win = null;
    private static Scene gameListScene = null;
    private static Pane gameListPane = null;

    private static Background gameListBackground = null;
    private static Background buttonColor = null;
    private static Background buttonColorHover = null;
    private static Border buttonBorder = null;
    private static Border buttonBorderHover = null;

    static class Element {
        private HBox field = null;
        private Label name = null;
        private Button replayButton = null;
    }

    private static ScrollPane gameListScrollPane = null;
    private static ArrayList<String> names = null;
    private static ArrayList<Element> items = null;
    private static VBox listBox = null;

    GameList() {}

    public static void initGameList(Stage window) throws IOException {
        win = window;
        gameListPane = new Pane();
        gameListScene = new Scene(gameListPane, sceneWide, sceneHeight);
        gameListScene.getStylesheets().add((GameList.class.getResource("Style.css")).toExternalForm());
        gameListScrollPane = new ScrollPane();
        items = new ArrayList();
        listBox = new VBox(5);

        gameListBackground = new Background(new BackgroundFill(Color.rgb(176, 219, 201), CornerRadii.EMPTY, Insets.EMPTY));
        buttonColor = new Background(new BackgroundFill(Color.rgb(81, 189, 143), new CornerRadii(10), Insets.EMPTY));
        buttonColorHover = new Background(new BackgroundFill(Color.rgb(144, 214, 184), new CornerRadii(10), Insets.EMPTY));
        buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2)));
        buttonBorderHover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(2)));

        gameListPane.setBackground(gameListBackground);

        //Scroll Pane
        gameListScrollPane.setVmin(0);
        gameListScrollPane.setPrefViewportHeight(sceneHeight - 20);
        gameListScrollPane.setPrefViewportWidth(sceneWide - 20);
        gameListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        gameListScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                win.setScene(Menu.getMenuScene());
                win.setX(850);
                win.setY(350);
            }
        });
        gameListScrollPane.setContent(listBox);
        gameListPane.getChildren().add(gameListScrollPane);
    }

    public static void goToGameList() throws IOException {
        win.setScene(gameListScene);
        win.setX(800);
        win.setY(300);

        names = Saves.readListEndedGames();
        listBox.getChildren().clear();
        items.clear();

        if (names.size() == 0) {
            Label nothing = new Label("Игр еще нет");
            nothing.getStyleClass().add("label30pxBlack");
            gameListScrollPane.setContent(nothing);
        } else {
            gameListScrollPane.setContent(listBox);
        }

        for (String name : names) {
            Element element = new Element();

            element.field = new HBox(10);
            element.name = new Label(name);
            element.replayButton = new Button("Play");

            //Label
            element.name.getStyleClass().add("label30pxBlack");

            //Replay button parameters
            element.replayButton.setBackground(buttonColor);
            element.replayButton.setBorder(buttonBorder);
            element.replayButton.getStyleClass().add("label15pxBlack");
            element.replayButton.setOnMouseEntered(e -> {
                element.replayButton.setBorder(buttonBorderHover);
                element.replayButton.setBackground(buttonColorHover);
            });
            element.replayButton.setOnMouseExited(e -> {
                element.replayButton.setBorder(buttonBorder);
                element.replayButton.setBackground(buttonColor);
            });
            element.replayButton.setOnAction(e -> {

            });

            element.field.getChildren().addAll(element.name, element.replayButton);
            listBox.getChildren().add(element.field);
        }
    }
}
