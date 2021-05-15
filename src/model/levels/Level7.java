package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Mads Nörklit Johansen
 * @version 1.2
 */

public class Level7 extends Level {
    public Level7(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
    }

    @Override
    public double generateMathQuestion() {
        int a;
        a = (int)(Math.random() * 15 + 1); // generera ett tal från 1 - 10
        int squareroot = a*a;

        String mathQuestionStr = "√"+ squareroot + "?";
        getGameManager().setCurrentMathQuestion(mathQuestionStr);
       // System.out.println("What's the square root of "+ squareroot + " ?");
        return a;
    }
}
