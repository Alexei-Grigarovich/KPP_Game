package sample;

import javafx.scene.image.ImageView;

public class Bullet extends ImageView {
    private static double speed = 8.0f;
    private int xDir = 0;
    private int yDir = 0;

    private boolean fromPlayer = true;
    private int power = 0;

    public Bullet(String url, Tank tank, int power, boolean fromPlayer) {
        super(url);

        this.power = power;
        this.fromPlayer = fromPlayer;

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

    public boolean isFromPlayer() {
        return fromPlayer;
    }

    public int getPower() {
        return power;
    }

    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public static double getSpeed() {
        return speed;
    }
}
