package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SaveSystemHelper {
    private final String path = "gameSave.save";
    private List<Hero> heroesList = new ArrayList<>();

    public void loadSave() {
        heroesList = new ArrayList<>();
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e) {
            System.err.println("FILE NOT FOUND");
            return;
        }
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            int id = Integer.parseInt(line.substring(1, line.length()));
            String name = sc.nextLine();
            int level = Integer.parseInt(sc.nextLine());
            int exp = Integer.parseInt(sc.nextLine());
            String hero_class = sc.nextLine();
            Hero hero = new Hero(id, name, exp, hero_class);
            hero.setLevel(level);
            heroesList.add(hero);
        }
    }

    public void addSave(Hero save) {
        int index = heroesList.indexOf(save);
        if (index == -1) {
            heroesList.add(save);
        } else {
            heroesList.set(index, save);
        }

        saveGame();
    }
    public void deleteSave(Hero save) {
        int index = heroesList.indexOf(save);
        if (index != -1) {
            heroesList.remove(save);
            saveGame();
        }
    }

    private void saveGame() {
        FileWriter fw;
        try {
            fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw);
            for (Hero hero : heroesList) {
                pw.println("#" + hero.getId());
                pw.println(hero.getName());
                pw.println(hero.getLevel());
                pw.println(hero.getExp());
                pw.println(hero.getHero_class());
                pw.println();
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("CANNOT WRITE FILE");
        }
    }

    public Hero[] getHeroes() {
        return heroesList.toArray(new Hero[heroesList.size()]);
    }

    public Hero getOpponent(int baseLevel) {
        java.util.Random random = new java.util.Random();
        Hero hero;
        int lv = baseLevel + random.nextInt(27) % 3;
        hero = new Hero(getRandomHeroOpponent(), 0, getRandomOpponentClass());
        hero.setLevel(lv);
        return hero;
    }

    private String getRandomOpponentClass() {
        int r = new Random().nextInt(4);
        String hero_class = "KUKODAM";
        if (r == 1) {
            hero_class = "ROLAND";
        } else if (r == 2) {
            hero_class = "SOMA";
        } else {
            hero_class = "KUKODAM";
        }

        return hero_class;
    }

    private String getRandomHeroOpponent() {
        int line = new java.util.Random().nextInt(237) + 1;
        try {
            FileInputStream fis = new FileInputStream("heroList.save");
            Scanner sc = new Scanner(fis);
            for (int count = 0; count != line; count++) sc.nextLine();
            return sc.nextLine();
        } catch (FileNotFoundException fe) {
            System.err.println("Not found");
        }
        return null;
    }



}
