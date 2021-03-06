package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Mads Nörklit Johansen
 * @version 1.0
 */

public class Level3 extends Level {
    public Level3(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
    }

    @Override
    public double generateMathQuestion() {
        int a, b, c, firstOperator, secondOperator;
        char char_firstOperator, char_secondOperator;
        a = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        b = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        c = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10

        System.out.println(a + "" + b+ "" + c + "");
        firstOperator = (int)(Math.random() * 2 + 1); //Genererar ett tal från 1-2 för att slumpmässigt bestämma tecken
        secondOperator = (int)(Math.random() * 2 + 1); //Genererar ett tal från 1-2 för att slumpmässigt bestämma tecken

        System.out.println("First operator: " + firstOperator + "\n"
        + "Second operator: " + secondOperator);
        if (firstOperator == 1) //Bestämmer vilket tecken som ska sättas i mattefrågan utifrån vår slumpmässiga siffra.
        {
            char_firstOperator = '+';
        }
        else
        {
            char_firstOperator = '-';
        }


        if (secondOperator == 1) //Bestämmer vilket tecken som ska sättas i mattefrågan utifrån vår slumpmässiga siffra.
        {
            char_secondOperator = '+';
        }
        else
        {
            char_secondOperator = '-';
        }

        System.out.println(a + " " + char_firstOperator + " "+ b + " " + char_secondOperator + " " + c + " " + " = ?"); // skriv ut frågan

        if (firstOperator == 1 && secondOperator == 1) //Retunerar rätt svar på uträkningen baserad på vilka operatorer som har valts ut.
        {
            getGameManager().setCurrentMathQuestion(a + " + " + b + " + " + c +" = ?");
            return a + b + c;
        } else if (firstOperator == 1 && secondOperator == 2)
        {
            getGameManager().setCurrentMathQuestion(a + " + " + b + " - " + c +" = ?");
            return a + b - c;
        }
       else if (firstOperator == 2 && secondOperator == 1)
        {
            getGameManager().setCurrentMathQuestion(a + " - " + b + " + " + c +" = ?");
            return a - b + c;

        }
         else if (firstOperator == 2 && secondOperator == 2)
        {
            getGameManager().setCurrentMathQuestion(a + " - " + b + " - "  + c +" = ?");
            return a - b - c;
        }
        System.out.println("Något gick fel...");
         return 0;
    }
}
