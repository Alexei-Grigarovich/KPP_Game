package sample;

import javafx.scene.image.ImageView;

public class Bullet extends ImageView {
    private static double speed = 5.0f;
    private double xDir = 0;
    private double yDir = 0;

    public Bullet(String url, Tank tank) {
        super(url);

        if (tank.getTankRotate() == 0) {
            xDir = 0;
            yDir = -1;
        }
        if (tank.getTankRotate() == 45) {
            xDir = 1;
            yDir = -1;
        }
        if (tank.getTankRotate() == 90) {
            xDir = 1;
            yDir = 0;
        }
        if (tank.getTankRotate() == 135) {
            xDir = 1;
            yDir = 1;
        }
        if (tank.getTankRotate() == 180) {
            xDir = 0;
            yDir = 1;
        }
        if (tank.getTankRotate() == 225) {
            xDir = -1;
            yDir = 1;
        }
        if (tank.getTankRotate() == 270) {
            xDir = -1;
            yDir = 0;
        }
        if (tank.getTankRotate() == 315) {
            xDir = -1;
            yDir = -1;
        }
    }

    public double getXDir() {
        return xDir;
    }

    public double getYDir() {
        return yDir;
    }

    public static double getSpeed() {
        return speed;
    }
}
