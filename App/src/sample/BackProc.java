package sample;

public class BackProc extends Thread {
    public static long fps = 60;
    public static boolean isWork = false;
    @Override
    public void run() {
        while (true) {
            while(!isWork) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Drive
            Game.gamePane.setTranslateY(Game.gamePane.getTranslateY() + (Tank.upDir + Tank.downDir * -1) * Tank.speed);
            Game.gamePane.setTranslateX(Game.gamePane.getTranslateX() + (Tank.leftDir + (Tank.rightDir * -1)) * Tank.speed);

            //Rotate
            if (Tank.upDir == 1 && Tank.downDir == 0 && Tank.leftDir == 0 && Tank.rightDir == 0) Tank.rotate = 0;
            if (Tank.upDir == 1 && Tank.downDir == 0 && Tank.leftDir == 0 && Tank.rightDir == 1) Tank.rotate = 45;
            if (Tank.upDir == 0 && Tank.downDir == 0 && Tank.leftDir == 0 && Tank.rightDir == 1) Tank.rotate = 90;
            if (Tank.upDir == 0 && Tank.downDir == 1 && Tank.leftDir == 0 && Tank.rightDir == 1) Tank.rotate = 135;
            if (Tank.upDir == 0 && Tank.downDir == 1 && Tank.leftDir == 0 && Tank.rightDir == 0) Tank.rotate = 180;
            if (Tank.upDir == 0 && Tank.downDir == 1 && Tank.leftDir == 1 && Tank.rightDir == 0) Tank.rotate = 225;
            if (Tank.upDir == 0 && Tank.downDir == 0 && Tank.leftDir == 1 && Tank.rightDir == 0) Tank.rotate = 270;
            if (Tank.upDir == 1 && Tank.downDir == 0 && Tank.leftDir == 1 && Tank.rightDir == 0) Tank.rotate = 315;
            if (Tank.upDir == 1 && Tank.downDir == 0 && Tank.leftDir == 1 && Tank.rightDir == 1) Tank.rotate = 0;
            if (Tank.upDir == 1 && Tank.downDir == 1 && Tank.leftDir == 0 && Tank.rightDir == 1) Tank.rotate = 90;
            if (Tank.upDir == 0 && Tank.downDir == 1 && Tank.leftDir == 1 && Tank.rightDir == 1) Tank.rotate = 180;
            if (Tank.upDir == 1 && Tank.downDir == 1 && Tank.leftDir == 1 && Tank.rightDir == 0) Tank.rotate = 270;
            Game.myTank.setRotate(Tank.rotate);

            //Check walls
            for(int i = 0; i < Game.walls.size(); i++) {
                Game.walls.get(i).leftBorder = Game.walls.get(i).jX * Tiles.tileWidth + Game.gamePane.getTranslateX();
                Game.walls.get(i).rightBorder = Game.walls.get(i).jX * Tiles.tileWidth + Tiles.tileWidth + Game.gamePane.getTranslateX();
                Game.walls.get(i).topBorder = Game.walls.get(i).iY * Tiles.tileWidth + Game.gamePane.getTranslateY();
                Game.walls.get(i).downBorder = Game.walls.get(i).iY * Tiles.tileWidth + Tiles.tileWidth + Game.gamePane.getTranslateY();
                if((Game.myTank.downBorder >= Game.walls.get(i).topBorder) && (Game.myTank.topBorder <= Game.walls.get(i).downBorder) && (Game.myTank.leftBorder <= Game.walls.get(i).rightBorder) && (Game.myTank.rightBorder >= Game.walls.get(i).leftBorder)) {
                    if(Game.myTank.downBorder == Game.walls.get(i).topBorder) Game.gamePane.setTranslateY(Game.gamePane.getTranslateY() + 2f);
                    if(Game.myTank.topBorder == Game.walls.get(i).downBorder) Game.gamePane.setTranslateY(Game.gamePane.getTranslateY() - 2f);
                    if(Game.myTank.leftBorder == Game.walls.get(i).rightBorder) Game.gamePane.setTranslateX(Game.gamePane.getTranslateX() - 2f);
                    if(Game.myTank.rightBorder == Game.walls.get(i).leftBorder) Game.gamePane.setTranslateX(Game.gamePane.getTranslateX() + 2f);
                    //System.out.println("Столкновение i=" + Game.Walls.get(i).iY + " j=" + Game.Walls.get(i).jX);
                }
            }

            try {
                sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
