package sample;

import javafx.scene.image.Image;

import java.net.MalformedURLException;

public class PlayerTank extends Tank {
    private int frame = 0;

    PlayerTank(String url, int damage, double cdMs) {
        super(url, damage, cdMs);
    }

    public void spawnTank() {
        //set tank on i = 0 and j = 0
        this.setTranslateX(Game.getSceneWidth() / 2 - Tiles.getTileWidth() / 2);
        this.setTranslateY(Game.getSceneHeight() / 2 - Tiles.getTileWidth() / 2);
        Game.getGamePane().setTranslateY(this.getTranslateY());
        Game.getGamePane().setTranslateX(this.getTranslateX());

        //random spawn tank
        int j;
        int i;
        boolean flag = true;
        do {
            j = (int)(Math.random() * (Game.getTilesWidth() - 1));
            i = (int)(Math.random() * (Game.getTilesHeight() - 1));

            for (int k = 0; k < Game.getWalls().size(); k++) {
                if (!(Game.getWalls().get(k).getJ() == j && Game.getWalls().get(k).getI() == i)) {
                    flag = false;
                } else {
                    flag = true;
                    break;
                }
            }
        } while (flag);
        Game.getGamePane().setTranslateY(Game.getGamePane().getTranslateY() - i * Tiles.getTileWidth());
        Game.getGamePane().setTranslateX(Game.getGamePane().getTranslateX() - j * Tiles.getTileWidth());
        System.out.println("Вы заспавнены на координатах " + i + " " + j);
    }

    public void setNextFrame() throws MalformedURLException {
        this.setImage(new Image(Tiles.getTileTank()[frame].toURI().toURL().toString()));

        frame++;
        if (frame >= 3) frame = 0;
    }
}
