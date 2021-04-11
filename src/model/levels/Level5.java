package model.levels;

import model.Level;
import model.Monster;




/**
 * @author Hanis Saley
 * version 1.2
 */

public class Level5 extends Level {


    public Level5(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Valyei",140));
    }


    @Override
    public double generateMathQuestion()  {
        int a,b,c;

        a = (int)(Math.random() * 10 + 1);
        c = (int)(Math.random() * 10 + 1);
        b = a * c;
        System.out.println(b+"/"+a + "= ?");
        return a/b ;
    }

}
