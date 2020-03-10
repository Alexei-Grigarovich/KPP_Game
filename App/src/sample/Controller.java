package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Controller {
    public static void InitKeyControl() {
        Game.GameScene.setOnKeyPressed(e -> { //Нажатие
            switch (e.getCode()) {
                case UP:
                    //System.out.println("UP");
                    Tank.UPdir = 1;
                    break;
                case DOWN:
                    //System.out.println("DOWN");
                    Tank.DOWNdir = 1;
                    break;
                case LEFT:
                    //System.out.println("LEFT");
                    Tank.LEFTdir = 1;
                    break;
                case RIGHT:
                    //System.out.println("RIGHT");
                    Tank.RIGHTdir = 1;
                    break;
                case ESCAPE:
                    //System.out.println("EXIT");
                    break;
            }
        });

        Game.GameScene.setOnKeyReleased(e -> { //Отжатие
            switch (e.getCode()) {
                case UP:
                    Tank.UPdir = 0;
                    break;
                case DOWN:
                    Tank.DOWNdir = 0;
                    break;
                case LEFT:
                    Tank.LEFTdir = 0;
                    break;
                case RIGHT:
                    Tank.RIGHTdir = 0;
                    break;
            }
        });
    }


}
