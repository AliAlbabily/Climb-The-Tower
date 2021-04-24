package model;

import model.levels.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Ali-A, Ardian Glamniki
 * @version 1.3
 */
public class GameManager implements TimerCallback{

    private Player player;
    private LinkedList<Level> lvls = new LinkedList<>(); // ska fungera enligt (First In First Out)
    private Level currentLevel = null;
    private Monster currentMonster = null;
    private String currentMathQuestion;
    private boolean gameHasEnded = false;
    int streak = 0;

    public GameManager(Player player) {
        this.player = player;

        // fill levels-list with new levels
        lvls.addFirst(new Level1("Level 1", this));
//        lvls.addFirst(new Level2("Level 2", this));
 //       lvls.addFirst(new Level3("Level 3", this));
//        lvls.addFirst(new Level4("Level 4", this));
 //       lvls.addFirst(new Level5("Level 5", this));
 //       lvls.addFirst(new Level6("Level 6", this));
//        lvls.addFirst(new Level7("Level 7", this));
//        lvls.addFirst(new Level8("Level 8", this));
//        lvls.addFirst(new Level9("Level 9", this));
//        lvls.addFirst(new Level10("Level 10", this));
    }

    private boolean checkUserAnswer(double correctAnswer, double userAnswer) {
        if(userAnswer == correctAnswer) {
            return true;
        }
        return false;
    }

    public void startAtFirstLevel() {
        nextLevel();
    }

    public void nextLevel() {
        if(!lvls.isEmpty()) { // hämta nästa nivå så länge listan inte är tom
            currentLevel = lvls.getLast();
            currentMonster = currentLevel.getMonster();
            String lvlName = currentLevel.getLvlName();
            String monsterName = currentMonster.getName();
            currentLevel.newLvlMessage(lvlName, monsterName);
        }
    }

    public double getNewMathQuestion() throws FileNotFoundException {
        return currentLevel.generateMathQuestion();
    }


    public boolean handleUserAnswer(double userAnswer, double currentCorrectAnswer) {
        int damage = 0;
        boolean ifCharacterIsDead = false;

        boolean userAnswerIsCorrect = checkUserAnswer(currentCorrectAnswer, userAnswer);

        if(userAnswerIsCorrect) {
            damage = 30;
            streak++;
            player.increasePoints();
          
            System.out.println("points"+player.getPoints());
            try
            {
                playSound("files/minecraft_hit.wav");
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
          
            System.out.println("Amount of points: "+player.getPoints());
            if (streak==5) //Hade kunnat ändra till streak > 2 eller något för att alltid ge bonus poäng.
            {
                player.increasePoints();
                System.out.println("You're on a 5x streak! Keep going! \n10 bonus points added!");

            } else if (streak == 10)
            {
                player.increasePoints(20);
                System.out.println("You're on a 10x streak! \n20 bonus points added!");
            }
            else if (streak ==15)
            {
                player.increasePoints(30);
                System.out.println("Are you cheating? On a 15x stream! \n30 bonus points awarded!");
            }
            System.out.println("Current points: "+player.getPoints());
            System.out.println("Amount of correct in a row: " + streak);
          
            currentMonster.takeDamage(damage);
            System.out.println("You dealt " + damage +" damage to the monster!");
            ifCharacterIsDead = currentMonster.checkIfAlive();
            if (ifCharacterIsDead) {
                System.out.println("You defeated: " + currentMonster.getName());

                System.out.println("20 bonus points for completing " + currentLevel.getLvlName()); //20 bonus poäng för att klara en level.
                lvls.removeLast();
                nextLevel();

                // when there are no more levels available in the lvls-list
                if(lvls.isEmpty()) {
                    System.out.println("\nYou won!");
                    System.out.println("A final reward of 100 bonus points for finishing the game. \nWell played gamer.");
                    player.increasePoints(100); //100 bonus poäng för att klara spelet.
                    gameHasEnded = true;
                    return gameHasEnded;
                }
            }
        } else {
            damage = 10;
            streak=0; //Streak reset
            player.takeDamage(damage);
            try
            {
                playSound("files/runescape-hit.wav");
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("Oh no, the monster dealt " + damage +" damage to you!");
            ifCharacterIsDead = player.checkIfAlive();
            if (ifCharacterIsDead) {
                System.out.println("You Died!");
                gameHasEnded = true;
                return gameHasEnded;
            }
        }

        return gameHasEnded;
    }


    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public String getCurrentMathQuestion() {
        return currentMathQuestion;
    }

    public void setCurrentMathQuestion(String currentMathQuestion) {
        this.currentMathQuestion = currentMathQuestion;
    }

    public int getPoints(){
        return player.getPoints();
    }

    @Override
    /*
     * This callback function is invoked when the countdown timer is finished,
     * which punishes the player by reducing hitpoints.
     */
    public void timesUp() {
        player.takeDamage(10);
        try
        {
            playSound("files/runescape-hit.wav");
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Times up! You need to be faster than that!");
        try {
            getNewMathQuestion();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File url = new File(fileName);
        Clip clip = AudioSystem.getClip();

        AudioInputStream ais = AudioSystem.
                getAudioInputStream( url );
        clip.open(ais);
        clip.start();
    }
}