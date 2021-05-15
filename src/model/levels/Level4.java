package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Hanis Saley
 * @version 1.1
 * This class generates random math questions that consist of multiplication.
 */
public class Level4 extends Level {


    public Level4(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
    }

    @Override
    public double generateMathQuestion() {
        int a, b;
        a = (int)(Math.random() * 10 + 1); //generates a random number up to 10
        b = (int)(Math.random() * 10 + 1); //generates a random number up to 10
        System.out.println(a + " * " + b + " = ?");
        String mathQuestionStr = a + " * " + b + " = ?";
        getGameManager().setCurrentMathQuestion(mathQuestionStr); // saves math question in GameManager
        return a * b;

    }

}
