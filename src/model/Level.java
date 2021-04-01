package model;

import javax.swing.*;

/**
 * @author Ali A
 * @version 1.0
 */
public abstract class Level {

    private ImageIcon image;
    private String lvlName;
    private Monster monster;

    public Level(String lvlName) {
        this.lvlName = lvlName;
    }

    public abstract int generateMathQuestion();

    public void newLvlMessage(String lvlName, String monsterName) {
        String mess = String.format("\n********** You entered %s, %s approaches! **********", lvlName, monsterName);
        System.out.println(mess);
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getLvlName() {
        return lvlName;
    }
}