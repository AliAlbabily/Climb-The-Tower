package model;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * @author Ali A
 * @version 1.1
 */
public abstract class Level {

    private ImageIcon image;
    private String lvlName;
    private Monster monster;
    private GameManager gameManager;

    public Level(String lvlName, GameManager gameManager) {
        this.lvlName = lvlName;
        this.gameManager = gameManager;
    }

    public abstract double generateMathQuestion() throws FileNotFoundException;

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

    public GameManager getGameManager() {
        return gameManager;
    }
}