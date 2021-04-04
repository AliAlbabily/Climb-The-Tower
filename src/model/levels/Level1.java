package model.levels;

import model.Level;
import model.Monster;

/**
 * @author Ali A
 * @version 1.0
 */
public class Level1 extends Level {

    public Level1(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Chimera",80)); // instansiera ett monster i denna nivå
    }

    @Override
    public double generateMathQuestion() {
        int a, b = 0;
        a = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        b = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        System.out.println(a + " + " + b + " = ?"); // skriv ut frågan
        return a + b;
    }
}