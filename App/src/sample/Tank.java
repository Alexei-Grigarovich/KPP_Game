package sample;

import javafx.scene.image.ImageView;

abstract class Tank extends ImageView {
    private double leftBorder = 0;
    private double rightBorder = 0;
    private double topBorder = 0;
    private double downBorder = 0;

    private double currentReloadTime = 0f;
    private double tankRotate = 0f;

    private double speed = 2.0f;
    private double reloading = 500f; //ms

    private double upDir = 0;
    private double downDir = 0;
    private double leftDir = 0;
    private double rightDir = 0;

    Tank(String url) {
        super(url);
    }

    public void shoot() {
        if (this.getCurrentReloadTime() == this.getReloading()) {
            this.setCurrentReloadTime(0);
            try {
                Game.spawnBullet(this); //Spawn bullet
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public double getSpeed() {
        return speed;
    }

    public double getReloading() {
        return reloading;
    }

    public void setUpDir(double upDir) {
        this.upDir = upDir;
    }

    public double getUpDir() {
        return upDir;
    }

    public void setDownDir(double downDir) {
        this.downDir = downDir;
    }

    public double getDownDir() {
        return downDir;
    }

    public void setLeftDir(double leftDir) {
        this.leftDir = leftDir;
    }

    public double getLeftDir() {
        return leftDir;
    }

    public void setRightDir(double rightDir) {
        this.rightDir = rightDir;
    }

    public double getRightDir() {
        return rightDir;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setTopBorder(double topBorder) {
        this.topBorder = topBorder;
    }

    public double getTopBorder() {
        return topBorder;
    }

    public void setDownBorder(double downBorder) {
        this.downBorder = downBorder;
    }

    public double getDownBorder() {
        return downBorder;
    }

    public double getTankRotate() {
        return tankRotate;
    }

    public void setTankRotate(double tankRotate) {
        this.tankRotate = tankRotate;
    }

    public void setCurrentReloadTime(double currentReloadTime) {
        this.currentReloadTime = currentReloadTime;
    }
    
    public double getCurrentReloadTime() {
        return currentReloadTime;
    }
}
