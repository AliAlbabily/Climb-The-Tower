package model.levels;

import model.Level;
import model.Monster;

/**
 * @author Hanis Saley
 * version 1.0
 */
public class Level4 extends Level {


    public Level4(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Wyvern",120));
    }

    @Override
    public double generateMathQuestion() {
        int a, b;
        a = (int)(Math.random() * 10 + 1);
        b = (int)(Math.random() * 10 + 1);
        System.out.println(a + " * " + b + " = ?");
        return a * b;
    }

}
