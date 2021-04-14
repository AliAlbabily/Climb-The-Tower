package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Ali A
 * @version 1.1
 */
public class Level1 extends Level {

    public Level1(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
        setMonster(new Monster("Chimera",80)); // instansiera ett monster i denna nivå
    }

    @Override
    public double generateMathQuestion() {
        int a, b = 0;
        a = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        b = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        String mathQuestionStr = a + " + " + b + " = ?";
        getGameManager().setCurrentMathQuestion(mathQuestionStr); // save math question in GameManager
        return a + b;
    }
}