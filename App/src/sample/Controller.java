package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public static void initKeyControl(Stage win) {
        Game.getGameScene().setOnKeyPressed(e -> { //Нажатие
            switch (e.getCode()) {
                case UP:
                    //System.out.println("up");
                    Game.getPlayerTank().setUpDir(1);
                    break;
                case DOWN:
                    //System.out.println("down");
                    Game.getPlayerTank().setDownDir(1);
                    break;
                case LEFT:
                    //System.out.println("left");
                    Game.getPlayerTank().setLeftDir(1);
                    break;
                case RIGHT:
                    //System.out.println("right");
                    Game.getPlayerTank().setRightDir(1);
                    break;
                case ESCAPE:
                    System.out.println("exit");
                    try {
                        Game.backToMenu(win, true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case SPACE:
                    //System.out.println("Pow!");
                    Game.getPlayerTank().shoot(Game.getPlayerTank().getDamage(), true);
                    break;
            }
        });

        Game.getGameScene().setOnKeyReleased(e -> { //Отжатие
            switch (e.getCode()) {
                case UP:
                    Game.getPlayerTank().setUpDir(0);
                    break;
                case DOWN:
                    Game.getPlayerTank().setDownDir(0);
                    break;
                case LEFT:
                    Game.getPlayerTank().setLeftDir(0);
                    break;
                case RIGHT:
                    Game.getPlayerTank().setRightDir(0);
                    break;
            }
        });
    }
}
