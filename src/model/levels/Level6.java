package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Hanis Saley
 * @version 1.2
 * This class generates random math questions that consist of multiplication and division combined.
 */
public class Level6 extends Level {


    public Level6(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
    }

    @Override
    public double generateMathQuestion() {
        int a, b, c, d;
        a = (int) (Math.random() * 5 + 1); //generates random number up to 5
        c = (int) (Math.random() * 9 + 1); //generates random number up to 9
        d = (int) (Math.random() * 10 + 1); // generates random number up to 10
        b = a * c * d;


            String mathQuestionStr = a + " * " + b + "/" + c+ "= ?";
            getGameManager().setCurrentMathQuestion(mathQuestionStr); // saves math question in GameManager
            return a * b / c;

    }
}
