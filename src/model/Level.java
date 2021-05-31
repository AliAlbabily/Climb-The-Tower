package model;

import java.io.FileNotFoundException;

/**
 * @author Ali A
 * @version 1.1
 */
public abstract class Level {

    private String lvlName;
    private Monster monster;
    private GameManager gameManager;

    public Level(String lvlName, GameManager gameManager) {
        this.lvlName = lvlName;
        this.gameManager = gameManager;
    }

    /**
     * Generates a new math question depending on the level.
     */
    public abstract double generateMathQuestion() throws FileNotFoundException;

    /**
     * Prints a message on the console indicating that the player has just entered a new level.
     * @param lvlName the name of the entered level.
     * @param monsterName the name of the monster to be seen at the entered level.
     */
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