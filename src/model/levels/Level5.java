package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Hanis Saley
 * @version 1.3
 * This class generates random math questions that consist of division.
 */

public class Level5 extends Level {


    public Level5(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
        setMonster(new Monster("Valyei",140));
    }


    @Override
    public double generateMathQuestion()  {
        int a,b,c;

        a = (int)(Math.random() * 10 + 1); //generates random number up to 10
        c = (int)(Math.random() * 10 + 1); //generates random number up to 10
        b = a * c;


        String mathQuestionStr = b+"/"+a + "= ?";
        getGameManager().setCurrentMathQuestion(mathQuestionStr); // saves math question in GameManager

        return b/a ;


    }

}
