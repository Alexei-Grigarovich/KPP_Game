package sample;

public class BackProc extends Thread {
    public static long fps = 60;
    @Override
    public void run() {
        System.out.println(Game.Walls.size());
        while (true) {
            //Drive
            Game.GamePane.setTranslateY(Game.GamePane.getTranslateY() + (Tank.UPdir + Tank.DOWNdir * -1) * Tank.speed);
            Game.GamePane.setTranslateX(Game.GamePane.getTranslateX() + (Tank.LEFTdir + (Tank.RIGHTdir * -1)) * Tank.speed);

            //Rotate
            if (Tank.UPdir == 1 && Tank.DOWNdir == 0 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 0) Tank.rotate = 0;
            if (Tank.UPdir == 1 && Tank.DOWNdir == 0 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 1) Tank.rotate = 45;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 0 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 1) Tank.rotate = 90;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 1 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 1) Tank.rotate = 135;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 1 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 0) Tank.rotate = 180;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 1 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 0) Tank.rotate = 225;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 0 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 0) Tank.rotate = 270;
            if (Tank.UPdir == 1 && Tank.DOWNdir == 0 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 0) Tank.rotate = 315;
            if (Tank.UPdir == 1 && Tank.DOWNdir == 0 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 1) Tank.rotate = 0;
            if (Tank.UPdir == 1 && Tank.DOWNdir == 1 && Tank.LEFTdir == 0 && Tank.RIGHTdir == 1) Tank.rotate = 90;
            if (Tank.UPdir == 0 && Tank.DOWNdir == 1 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 1) Tank.rotate = 180;
            if (Tank.UPdir == 1 && Tank.DOWNdir == 1 && Tank.LEFTdir == 1 && Tank.RIGHTdir == 0) Tank.rotate = 270;
            Game.myTank.setRotate(Tank.rotate);

            //Check walls
            for(int i = 0; i < Game.Walls.size(); i++) {
                Game.Walls.get(i).LeftBorder = Game.Walls.get(i).jX * Tiles.TileWidth + Game.GamePane.getTranslateX();
                Game.Walls.get(i).RightBorder = Game.Walls.get(i).jX * Tiles.TileWidth + Tiles.TileWidth + Game.GamePane.getTranslateX();
                Game.Walls.get(i).TopBorder = Game.Walls.get(i).iY * Tiles.TileWidth + Game.GamePane.getTranslateY();
                Game.Walls.get(i).DownBorder = Game.Walls.get(i).iY * Tiles.TileWidth + Tiles.TileWidth + Game.GamePane.getTranslateY();
                if((Game.myTank.DownBorder >= Game.Walls.get(i).TopBorder) && (Game.myTank.TopBorder <= Game.Walls.get(i).DownBorder) && (Game.myTank.LeftBorder <= Game.Walls.get(i).RightBorder) && (Game.myTank.RightBorder >= Game.Walls.get(i).LeftBorder)) {
                    if(Game.myTank.DownBorder == Game.Walls.get(i).TopBorder) Game.GamePane.setTranslateY(Game.GamePane.getTranslateY() + 2f);
                    if(Game.myTank.TopBorder == Game.Walls.get(i).DownBorder) Game.GamePane.setTranslateY(Game.GamePane.getTranslateY() - 2f);
                    if(Game.myTank.LeftBorder == Game.Walls.get(i).RightBorder) Game.GamePane.setTranslateX(Game.GamePane.getTranslateX() - 2f);
                    if(Game.myTank.RightBorder == Game.Walls.get(i).LeftBorder) Game.GamePane.setTranslateX(Game.GamePane.getTranslateX() + 2f);
                    System.out.println("Столкновение i=" + Game.Walls.get(i).iY + " j=" + Game.Walls.get(i).jX);
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
