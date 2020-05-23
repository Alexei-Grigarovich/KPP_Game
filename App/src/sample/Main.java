package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static BackProc backProc = null;

    @Override
    public void start(Stage win) throws Exception {
        //Initialization
        Tiles.initFiles();
        Game.initScene(win);
        Menu.initScene(win);
        Settings.initScene(win);
        Saves.initSaves();
        GameList.initGameList(win);
        LoadGame.initLoadGame(win);

        backProc = new BackProc();
        backProc.start();

        //Window
        win.show();
        win.setResizable(false);
        win.setX(850);
        win.setY(350);
    }

    @Override
    public void stop() throws Exception {
        backProc.stop();
        super.stop();
    }

    public static BackProc getBackProc() {
        return backProc;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
