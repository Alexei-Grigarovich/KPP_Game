package sample;

import javafx.application.Platform;

public class BackProc extends Thread {
    private final short FPS = 60;
    private boolean isWork = false;

    @Override
    public void run() {
        while (true) {
            if (!isWork) {
                //Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //Bullet fly
                for (Bullet bullet : Game.getBullets()) {
                    bullet.setTranslateY(bullet.getTranslateY() + bullet.getYDir() * bullet.getSpeed());
                    bullet.setTranslateX(bullet.getTranslateX() + bullet.getXDir() * bullet.getSpeed());
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
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(0);;
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(45);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(90);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(135);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(180);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(225);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(270);
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(315);
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 0 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(0);
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 0 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(90);
                if (Game.getPlayerTank().getUpDir() == 0 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 1) Game.getPlayerTank().setTankRotate(180);
                if (Game.getPlayerTank().getUpDir() == 1 && Game.getPlayerTank().getDownDir() == 1 && Game.getPlayerTank().getLeftDir() == 1 && Game.getPlayerTank().getRightDir() == 0) Game.getPlayerTank().setTankRotate(270);
                Game.getPlayerTank().setRotate(Game.getPlayerTank().getTankRotate());


                //Collision with walls
                for (Tile wall : Game.getWalls()) {
                    //Player Tank
                    if ((Game.getPlayerTank().getDownBorder() >= wall.getTopBorder()) && (Game.getPlayerTank().getTopBorder() <= wall.getDownBorder()) && (Game.getPlayerTank().getLeftBorder() <= wall.getRightBorder()) && (Game.getPlayerTank().getRightBorder() >= wall.getLeftBorder())) {
                        if (Game.getPlayerTank().getDownBorder() == wall.getTopBorder())
                            Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() + 2f);
                        if (Game.getPlayerTank().getTopBorder() == wall.getDownBorder())
                            Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() - 2f);
                        if (Game.getPlayerTank().getLeftBorder() == wall.getRightBorder())
                            Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() - 2f);
                        if (Game.getPlayerTank().getRightBorder() == wall.getLeftBorder())
                            Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() + 2f);
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
                    //Enemy tanks drive
                    //Error it's hear
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

                    //Enemy tank shoot
//                    Platform.runLater(() -> {
//                        tank.shoot();
//                    });

                    //Enemy tanks rotate
                    if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 0) tank.setTankRotate(0);;
                    if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 1) tank.setTankRotate(45);
                    if (tank.getUpDir() == 0 && tank.getDownDir() == 0 && tank.getLeftDir() == 0 && tank.getRightDir() == 1) tank.setTankRotate(90);
                    if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 0 && tank.getRightDir() == 1) tank.setTankRotate(135);
                    if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 0 && tank.getRightDir() == 0) tank.setTankRotate(180);
                    if (tank.getUpDir() == 0 && tank.getDownDir() == 1 && tank.getLeftDir() == 1 && tank.getRightDir() == 0) tank.setTankRotate(225);
                    if (tank.getUpDir() == 0 && tank.getDownDir() == 0 && tank.getLeftDir() == 1 && tank.getRightDir() == 0) tank.setTankRotate(270);
                    if (tank.getUpDir() == 1 && tank.getDownDir() == 0 && tank.getLeftDir() == 1 && tank.getRightDir() == 0) tank.setTankRotate(315);
                    tank.setRotate(tank.getTankRotate());

                    //Collision enemy tanks with walls
                    for (Tile wall : Game.getWalls()) {
                        if ((tank.getDownBorder() >= wall.getTopBorder()) && (tank.getTopBorder() <= wall.getDownBorder()) && (tank.getLeftBorder() <= wall.getRightBorder()) && (tank.getRightBorder() >= wall.getLeftBorder())) {
                            if (tank.getDownBorder() == wall.getTopBorder()) {
                                tank.setTranslateY(tank.getTranslateY() - 2f);
                                tank.setTime(20);
                                tank.setCanMoveDown(false);
                                tank.setDownDir(0);
                                tank.goSomewhere();
                            }
                            if (tank.getTopBorder() == wall.getDownBorder()) {
                                tank.setTranslateY(tank.getTranslateY() + 2f);
                                tank.setTime(20);
                                tank.setCanMoveUp(false);
                                tank.setUpDir(0);
                                tank.goSomewhere();
                            }
                            if (tank.getLeftBorder() == wall.getRightBorder()) {
                                tank.setTranslateX(tank.getTranslateX() + 2f);
                                tank.setTime(20);
                                tank.setCanMoveLeft(false);
                                tank.setLeftDir(0);
                                tank.goSomewhere();
                            }
                            if (tank.getRightBorder() == wall.getLeftBorder()) {
                                tank.setTranslateX(tank.getTranslateX() - 2f);
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

                    //Enemy tanks time
                    tank.setTime(tank.getTime() + 1000 / FPS);
                }

                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setWork(boolean isWork) {
        this.isWork = isWork;
    }
}
