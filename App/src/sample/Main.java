package sample;

import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    public static BackProc backProc = new BackProc();

    @Override
    public void start(Stage win) throws Exception {
        //Initialization
        Tiles.initFiles();
        Game.initScene();
        Menu.initScene(win);
        Settings.initScene(win);
        Main.backProc.start();

        win.show();
        win.setX(850);
        win.setY(350);
    }

    @Override
    public void stop() throws Exception {
        Main.backProc.stop();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
