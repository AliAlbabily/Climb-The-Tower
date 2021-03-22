package model;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameManager {

    private Player hero = new Player(100);
    private Monster Chimera = new Monster(80);

    private Level lvl1 = new Level();

    public void startLevels() {
        System.out.println("\nYou entered lvl 1, an enemy approaches!");
        int answer = 0;
        int damage = 0;
        boolean ifCharacterIsDead = false;

        while (ifCharacterIsDead != true) {
            System.out.println("\nhero's hp: " + hero.getHitPoints());
            System.out.println("Chimera's hp: " + Chimera.getHitPoints() +"\n");

            try {
                answer = lvl1.generateMathQuestion();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("\nAttack the enemy by entering the right number: ");
            int yourAnswer = getInteger(-1000, 1000);

            if(yourAnswer == answer) {
                damage = 30;
                Chimera.takeDamage(damage);
                System.out.println("You dealt " + damage +" damage to Chimera!");
                ifCharacterIsDead = Chimera.checkIfAlive();
                if (ifCharacterIsDead) {
                    System.out.println("Chimera's hp: "+Chimera.getHitPoints());
                    System.out.println("You Won!");
                    break;
                }
                System.out.println("Chimera's hp: "+Chimera.getHitPoints());
            } else {
                damage = 10;
                hero.takeDamage(damage);
                System.out.println("Oh no, the Chimera dealt " + damage +" damage to you!");
                ifCharacterIsDead = hero.checkIfAlive();
                if (ifCharacterIsDead) {
                    System.out.println("hero's hp: "+ hero.getHitPoints());
                    System.out.println("You Died!");
                    break;
                }
                System.out.println("hero's hp: "+ hero.getHitPoints());
            }
        }
        System.out.println("\nYou can now proceed to the next level");
//        hero.getHitPoints() >= 0 || Chimera.getHitPoints() >= 0
    }

    private int getInteger(int lowLimit, int upperLimit) {
        int number = 0;
        boolean goodNumber = false;

        Scanner reader = new Scanner(System.in);
        do {
            number = reader.nextInt();

            goodNumber = (number >= lowLimit) && (number <= upperLimit);
            if(!goodNumber)
                System.out.println("Invalid Number! Try again.");

        } while(!goodNumber);

        return number;
    }
}
