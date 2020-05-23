package sample;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Saves {
    private static FileWriter fw = null;
    private static BufferedReader br = null;
    private static FileReader fr = null;
    private static File savesFile = null;
    private static ArrayList<String> savesList = null;

    Saves() {}

    public static void initSaves() throws IOException {
        savesFile = new File("Highscores/highscores.txt");

        if (!savesFile.exists()) {
            savesFile.createNewFile();
        }

        savesList = new ArrayList();
    }

    public static void saveEndedGame(String name, int points, int waves) throws IOException {
        fw = new FileWriter(savesFile, true);

        fw.write(name + " Очки: " + points + " Волн: " + waves + "\n");

        fw.close();
    }

    public static ArrayList<String> readListEndedGames() throws IOException {
        savesList.clear();

        fr = new FileReader(savesFile);
        br = new BufferedReader(fr);
        String temp;

        while((temp = br.readLine()) != null) {
            savesList.add(temp);
        }

        br.close();
        fr .close();

        return savesList;
    }

    public static void saveGame(ArrayList<Tile> walls, ArrayList<EnemyTank> enemyTanks, ArrayList<Bullet> bullets, PlayerTank playerTank, short wave, int points, short difficulty, int enemyTanksLast, double time) throws IOException {
        Date date = new Date();
        LocalDate localDate = LocalDate.now();

        File file = new File("Saves/" + localDate + "_" + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + ".txt");
        file.createNewFile();

        fw = new FileWriter(file , true);

        //Запись в файл
        //walls
        for (Tile wall : walls) {
            //"i j "...
            fw.write(wall.getI() + " " + wall.getJ() + " ");
        }
        fw.write("\n");

        //enemy tanks
        for (EnemyTank tank : enemyTanks) {
            //"x y hp dmg cd currCd isDeath "...
            fw.write(tank.getTranslateX() + " " + tank.getTranslateY() + " " + tank.getHp() + " " + tank.getDamage() + " " + tank.getReloading() + " " + tank.getCurrentReloadTime() + " " + (tank.isDeath() ? 1 : 0) + " ");
        }
        fw.write("\n");

        //bullets
        for (Bullet bullet : bullets) {
            //"x y xdir ydir power fromPlayer "...
            fw.write(bullet.getTranslateX() + " " + bullet.getTranslateY() + " " + bullet.getXDir() + " " + bullet.getYDir() + " " + bullet.getPower() + " " + (bullet.isFromPlayer() ? 1 : 0) + " ");
        }
        fw.write("\n");

        //player tank
        //"x y hp "
        fw.write(Game.getGamePane().getTranslateX() + " " + Game.getGamePane().getTranslateY() + " " + playerTank.getHp() + " ");
        fw.write("\n");

        //"wave points dif etLast time "
        //wave
        fw.write(wave + " ");

        //points
        fw.write(points + " ");

        //difficult
        fw.write(difficulty + " ");

        //enemy tanks last
        fw.write(enemyTanksLast + " ");

        //time
        fw.write(time + " \n");

        fw.close();
    }

    public static void loadGame(String fileName ,ArrayList<Tile> walls, ArrayList<EnemyTank> enemyTanks, ArrayList<Bullet> bullets, PlayerTank playerTank, short wave, int points, short difficulty, double time) {

    }

    public static File[] findSaves() {
        File file = new File("Saves");
        File[] files = file.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        return  files;
    }
}
