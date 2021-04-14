package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

public class Level7 extends Level {
    public Level7(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
        setMonster(new Monster("Berry The Cherry", 140));
    }

    @Override
    public double generateMathQuestion() {
        int a;
        a = (int)(Math.random() * 15 + 1); // generera ett tal från 1 - 10
        int squareroot = a*a;
        System.out.println("What's the square root of "+ squareroot + " ?");
        return a;
    }
}
