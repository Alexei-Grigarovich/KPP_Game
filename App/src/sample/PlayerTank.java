package sample;

public class PlayerTank extends Tank {
    PlayerTank(String url) {
        super(url);
    }

    public void spawnTank() {
        //set tank on i = 0 and j = 0
        this.setTranslateX(Game.getSceneWidth() / 2 - this.getImage().getWidth() / 2);
        this.setTranslateY(Game.getSceneHeight() / 2 - this.getImage().getHeight() / 2);
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
}
