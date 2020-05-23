package sample;

import javafx.application.Platform;

import java.net.MalformedURLException;

public class BackProc extends Thread {
    private final short FPS = 60;
    private boolean isWork = false;
    private short currentFrame = 0;
    private double time = 0; //In sec
    private final double TIME_NEW_WAVE = 180;

    @Override
    public void run() {
        while (true) {
            if (!isWork) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Platform.runLater(() -> {
                    //Game events
                    if((time > 6 && time % TIME_NEW_WAVE > 0.001f && time % TIME_NEW_WAVE < 0.017f) || (time > 5.001f && time < 5.017f)) {
                        try {
                            Game.newWave();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (Game.getPlayerTank().getHp() <= 0) {
                        Game.getPlayerTank().setHp(0);
                        Game.setBarPlayerTankHp(Game.getPlayerTank().getHp());
                        Game.getPlayerTank().setDeath(true);

                        setWork(false);

                        Game.playerDied();
                    }

                    //Bullet
                    for (int i = 0; i < Game.getBullets().size(); i++) {
                        //Bullets fly
                        Game.getBullets().get(i).setTranslateY(Game.getBullets().get(i).getTranslateY() + Game.getBullets().get(i).getYDir() * Game.getBullets().get(i).getSpeed());
                        Game.getBullets().get(i).setTranslateX(Game.getBullets().get(i).getTranslateX() + Game.getBullets().get(i).getXDir() * Game.getBullets().get(i).getSpeed());

                        //Player tank collision with bullets
                        if (!Game.getBullets().get(i).isFromPlayer() && Game.getBullets().get(i).getTranslateX() > Game.getPlayerTank().getLeftBorder() && Game.getBullets().get(i).getTranslateX() < Game.getPlayerTank().getRightBorder() && Game.getBullets().get(i).getTranslateY() > Game.getPlayerTank().getTopBorder() && Game.getBullets().get(i).getTranslateY() < Game.getPlayerTank().getDownBorder()) {
                            Game.getPlayerTank().setHp(Game.getPlayerTank().getHp() - Game.getBullets().get(i).getPower());
                            Game.setBarPlayerTankHp(Game.getPlayerTank().getHp());
                            Game.getGamePane().getChildren().remove(Game.getBullets().get(i));
                            Game.getBullets().remove(i);
                        }
                    }


                    //Player tank animation
                    if ((Game.getPlayerTank().getLeftDir() == 1 || Game.getPlayerTank().getRightDir() == 1 || Game.getPlayerTank().getUpDir() == 1 || Game.getPlayerTank().getDownDir() == 1) && currentFrame % 20 == 0) {
                        try {
                            Game.getPlayerTank().setNextFrame();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }


                    //Player tank drive
                    Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() + (Game.getPlayerTank().getUpDir() - Game.getPlayerTank().getDownDir()) * Game.getPlayerTank().getSpeed());
                    Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() + (Game.getPlayerTank().getLeftDir() - Game.getPlayerTank().getRightDir()) * Game.getPlayerTank().getSpeed());

                    //Player tank borders
                    Game.getPlayerTank().setLeftBorder(Game.getSceneWidth() / 2 - Game.getGamePane().getTranslateX() - 20);
                    Game.getPlayerTank().setRightBorder(Game.getSceneWidth() / 2 - Game.getGamePane().getTranslateX() + 20);
                    Game.getPlayerTank().setTopBorder(Game.getSceneHeight() / 2 - Game.getGamePane().getTranslateY() - 20);
                    Game.getPlayerTank().setDownBorder(Game.getSceneHeight() / 2 - Game.getGamePane().getTranslateY() + 20);

                    //Player tank reload
                    if (Game.getPlayerTank().getCurrentReloadTime() < Game.getPlayerTank().getReloading()) {
                        Game.getPlayerTank().setCurrentReloadTime(Game.getPlayerTank().getCurrentReloadTime() + 1000 / FPS);
                        if (Game.getPlayerTank().getCurrentReloadTime() > Game.getPlayerTank().getReloading()) {
                            Game.getPlayerTank().setCurrentReloadTime(Game.getPlayerTank().getReloading());
                        }
                    }

                    //Player tank rotate
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(0);
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(45);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(90);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(135);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(180);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(225);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(270);
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(315);
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(0);
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(90);
                    if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 1)
                        Game.getPlayerTank().setTankRotate(180);
                    if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0)
                        Game.getPlayerTank().setTankRotate(270);
                    Game.getPlayerTank().setRotate(Game.getPlayerTank().getTankRotate());


                    //Collision with walls
                    for (Tile wall : Game.getWalls()) {
                        //Player Tank
                        if ((Game.getPlayerTank().getDownBorder() >= wall.getTopBorder()) && (Game.getPlayerTank().getTopBorder() <= wall.getDownBorder()) && (Game.getPlayerTank().getLeftBorder() <= wall.getRightBorder()) && (Game.getPlayerTank().getRightBorder() >= wall.getLeftBorder())) {
                            if (Game.getPlayerTank().getDownBorder() == wall.getTopBorder())
                                Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() + Game.getPlayerTank().getSpeed());
                            if (Game.getPlayerTank().getTopBorder() == wall.getDownBorder())
                                Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() - Game.getPlayerTank().getSpeed());
                            if (Game.getPlayerTank().getLeftBorder() == wall.getRightBorder())
                                Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() - Game.getPlayerTank().getSpeed());
                            if (Game.getPlayerTank().getRightBorder() == wall.getLeftBorder())
                                Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() + Game.getPlayerTank().getSpeed());
                            //System.out.println("Столкновение i=" + Game.Walls.get(i).iY + " j=" + Game.Walls.get(i).jX);
                        }

                        //Bullets
                        for (int j = 0; j < Game.getBullets().size(); j++) {
                            if ((Game.getBullets().get(j).getTranslateY() + Game.getBullets().get(j).getImage().getHeight() / 2 >= wall.getTopBorder()) && (Game.getBullets().get(j).getTranslateY() + Game.getBullets().get(j).getImage().getHeight() / 2 <= wall.getDownBorder()) && (Game.getBullets().get(j).getTranslateX() + Game.getBullets().get(j).getImage().getWidth() / 2 <= wall.getRightBorder()) && (Game.getBullets().get(j).getTranslateX() + Game.getBullets().get(j).getImage().getWidth() / 2 >= wall.getLeftBorder())) {
                                Game.getBullets().remove(j);
                            }
                        }
                    }

                    //Enemy tanks
                    for (EnemyTank tank : Game.getEnemyTanks()) {
                        if (!tank.isDeath()) {
                            //Enemy tanks drive
                            tank.setTranslateY(tank.getTranslateY() + (tank.getDownDir() - tank.getUpDir()) * tank.getSpeed());
                            tank.setTranslateX(tank.getTranslateX() + (tank.getRightDir() - tank.getLeftDir()) * tank.getSpeed());

                            //Enemy tanks borders
                            tank.setLeftBorder(tank.getTranslateX() + Tiles.getTileWidth() / 2 - 20);
                            tank.setRightBorder(tank.getTranslateX() + Tiles.getTileWidth() / 2 + 20);
                            tank.setTopBorder(tank.getTranslateY() + Tiles.getTileWidth() / 2 - 20);
                            tank.setDownBorder(tank.getTranslateY() + Tiles.getTileWidth() / 2 + 20);

                            //Enemy tank reload
                            if (tank.getCurrentReloadTime() < tank.getReloading()) {
                                tank.setCurrentReloadTime(tank.getCurrentReloadTime() + 1000 / FPS);
                                if (tank.getCurrentReloadTime() > tank.getReloading()) {
                                    tank.setCurrentReloadTime(tank.getReloading());
                                }
                            }

                            //Enemy tanks rotate
                            if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 0)
                                tank.setTankRotate(0);
                            if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 1)
                                tank.setTankRotate(45);
                            if (tank.getUpDir() == 0 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 1)
                                tank.setTankRotate(90);
                            if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 0 && tank.getRightDir() == 1)
                                tank.setTankRotate(135);
                            if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 0 && tank.getRightDir() == 0)
                                tank.setTankRotate(180);
                            if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 1 && tank.getRightDir() == 0)
                                tank.setTankRotate(225);
                            if (tank.getUpDir() == 0 && tank.getDownDir() == 0 && tank.getLeftDir() == 1 && tank.getRightDir() == 0)
                                tank.setTankRotate(270);
                            if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 1 && tank.getRightDir() == 0)
                                tank.setTankRotate(315);
                            tank.setRotate(tank.getTankRotate());

                            //Collision enemy tanks with walls
                            for (Tile wall : Game.getWalls()) {
                                if ((tank.getDownBorder() >= wall.getTopBorder()) && (tank.getTopBorder() <= wall.getDownBorder()) && (tank.getLeftBorder() <= wall.getRightBorder()) && (tank.getRightBorder() >= wall.getLeftBorder())) {
                                    if (tank.getDownBorder() == wall.getTopBorder()) {
                                        tank.setTranslateY(tank.getTranslateY() - tank.getSpeed());
                                        tank.setTime(20);
                                        tank.setCanMoveDown(false);
                                        tank.setDownDir(0);
                                        tank.goSomewhere();
                                    }
                                    if (tank.getTopBorder() == wall.getDownBorder()) {
                                        tank.setTranslateY(tank.getTranslateY() + tank.getSpeed());
                                        tank.setTime(20);
                                        tank.setCanMoveUp(false);
                                        tank.setUpDir(0);
                                        tank.goSomewhere();
                                    }
                                    if (tank.getLeftBorder() == wall.getRightBorder()) {
                                        tank.setTranslateX(tank.getTranslateX() + tank.getSpeed());
                                        tank.setTime(20);
                                        tank.setCanMoveLeft(false);
                                        tank.setLeftDir(0);
                                        tank.goSomewhere();
                                    }
                                    if (tank.getRightBorder() == wall.getLeftBorder()) {
                                        tank.setTranslateX(tank.getTranslateX() - tank.getSpeed());
                                        tank.setTime(20);
                                        tank.setCanMoveRight(false);
                                        tank.setRightDir(0);
                                        tank.goSomewhere();
                                    }
                                }
                            }

                            //logic
                            if (tank.getTime() % 2000 < 20 && tank.getTime() % 2000 >= 0) {
                                tank.setCanMoveDown(true);
                                tank.setCanMoveUp(true);
                                tank.setCanMoveLeft(true);
                                tank.setCanMoveRight(true);
                                tank.goSomewhere();
                            }
                            if (!(tank.isCanMoveDown() && tank.isCanMoveUp() && tank.isCanMoveRight() && tank.isCanMoveLeft())) {
                                tank.setCanMoveDown(true);
                                tank.setCanMoveUp(true);
                                tank.setCanMoveLeft(true);
                                tank.setCanMoveRight(true);
                                tank.goSomewhere();
                            }

                            //Enemy tank shoot
                            tank.shoot(tank.getDamage(), false);

                            //Enemy tanks animations
                            if ((tank.getLeftDir() == 1 || tank.getRightDir() == 1 || tank.getUpDir() == 1 || tank.getDownDir() == 1) && currentFrame % 20 == 0) {
                                try {
                                    tank.setNextFrame();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }

                            //Enemy tanks time
                            tank.setTime(tank.getTime() + 1000 / FPS);

                            //Enemy tanks collision with bullets
                            for (int i = 0; i < Game.getBullets().size(); i++) {
                                if (Game.getBullets().get(i).isFromPlayer() && Game.getBullets().get(i).getTranslateX() > tank.getLeftBorder() && Game.getBullets().get(i).getTranslateX() < tank.getRightBorder() && Game.getBullets().get(i).getTranslateY() > tank.getTopBorder() && Game.getBullets().get(i).getTranslateY() < tank.getDownBorder()) {
                                    tank.setHp(tank.getHp() - Game.getBullets().get(i).getPower());
                                    Game.getGamePane().getChildren().remove(Game.getBullets().get(i));
                                    Game.getBullets().remove(i);

                                    //Enemy tanks die
                                    if (tank.getHp() <= 0) {
                                        tank.setDeath(true);
                                        Game.setPoints(Game.getPoints() + 1);
                                        Game.setCountEnemyTanksLast(Game.getCountEnemyTanksLast() - 1);
                                    }
                                }
                            }
                        } else {
                            if (currentFrame % 20 == 0) {
                                try {
                                    tank.setNextFrame();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

                currentFrame++;
                if (currentFrame >= FPS)
                    currentFrame = 0;
                time += 1 / (double)FPS;

                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setWork(boolean isWork) {
        this.isWork = isWork;
    }
}
