package sample;

public class EnemyTank extends Tank {
    private int time = 0;

    private int spawnI;
    private int spawnJ;

    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;

    EnemyTank(String url) {
        super(url);
    }

    public void spawnTank() {
        //Random spawn tank
        int i;
        int j;
        boolean flag = true;
        do {
            j = (int)(Math.random() * (Game.getTilesWidth() - 1));
            i = (int)(Math.random() * (Game.getTilesHeight() - 1));

            for (int k = 0; k < Game.getWalls().size(); k++) {
                if (!(Game.getWalls().get(k).getJ() == j && Game.getWalls().get(k).getI() == i)) {
                    if(!Game.getEnemyTanks().isEmpty()) {
                        for (int l = 0; l < Game.getEnemyTanks().size(); l++) {
                            if (Game.getEnemyTanks().get(l).getSpawnI() == i && Game.getEnemyTanks().get(l).getSpawnJ() == j) {
                                flag = true;
                                break;
                            } else {
                                flag = false;
                            }
                        }
                    } else {
                        flag = false;
                    }
                } else {
                    flag = true;
                    break;
                }
            }
        } while (flag);

        spawnI = i;
        spawnJ = j;

        //placing enemy tank
        this.setTranslateX(j * Tiles.getTileWidth());
        this.setTranslateY(i * Tiles.getTileWidth());
    }

    public void goSomewhere() {
        //0 - up, 1 - right, 2 - down, 3 - left
        int randomDir = (int) (Math.random() * 4);

        switch (randomDir) {
            case 0:
                if (this.isCanMoveUp()) {
                    this.setUpDir(1);
                    this.setDownDir(0);

                    if(Math.random() > 0.5f) {
                        this.setRightDir(0);
                        this.setLeftDir(0);
                    }
                }
                break;
            case 1:
                if (this.isCanMoveRight()) {
                    this.setRightDir(1);
                    this.setLeftDir(0);

                    if(Math.random() > 0.5f) {
                        this.setUpDir(0);
                        this.setDownDir(0);
                    }
                }
                break;
            case 2:
                if (this.isCanMoveDown()) {
                    this.setDownDir(1);
                    this.setUpDir(0);

                    if(Math.random() > 0.5f) {
                        this.setRightDir(0);
                        this.setLeftDir(0);
                    }
                }
                break;
            case 3:
                if (this.isCanMoveLeft()) {
                    this.setLeftDir(1);
                    this.setRightDir(0);

                    if(Math.random() > 0.5f) {
                        this.setUpDir(0);
                        this.setDownDir(0);
                    }
                }
                break;
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setCanMoveLeft(boolean canMoveLeft) {
        this.canMoveLeft = canMoveLeft;
    }

    public boolean isCanMoveLeft() {
        return canMoveLeft;
    }

    public void setCanMoveRight(boolean canMoveRight) {
        this.canMoveRight = canMoveRight;
    }

    public boolean isCanMoveRight() {
        return canMoveRight;
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public boolean isCanMoveUp() {
        return canMoveUp;
    }

    public void setCanMoveDown(boolean canMoveDown) {
        this.canMoveDown = canMoveDown;
    }

    public  boolean isCanMoveDown() {
        return canMoveDown;
    }

    public int getSpawnI() {
        return spawnI;
    }

    public int getSpawnJ() {
        return spawnJ;
    }
}
