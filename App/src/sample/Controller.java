package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    public static void initKeyControl(Stage win) {
        Game.gameScene.setOnKeyPressed(e -> { //Нажатие
            switch (e.getCode()) {
                case UP:
                    //System.out.println("UP");
                    Tank.upDir = 1;
                    break;
                case DOWN:
                    //System.out.println("DOWN");
                    Tank.downDir = 1;
                    break;
                case LEFT:
                    //System.out.println("LEFT");
                    Tank.leftDir = 1;
                    break;
                case RIGHT:
                    //System.out.println("RIGHT");
                    Tank.rightDir = 1;
                    break;
                case ESCAPE:
                    System.out.println("EXIT");
                    Game.backToMenu(win);
                    break;
                case SPACE:
                    System.out.println("Pow");
                    break;
            }
        });

        Game.gameScene.setOnKeyReleased(e -> { //Отжатие
            switch (e.getCode()) {
                case UP:
                    Tank.upDir = 0;
                    break;
                case DOWN:
                    Tank.downDir = 0;
                    break;
                case LEFT:
                    Tank.leftDir = 0;
                    break;
                case RIGHT:
                    Tank.rightDir = 0;
                    break;
            }
        });
    }
}
