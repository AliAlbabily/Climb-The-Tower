package model;

import model.levels.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Ali-A, Ardian Glamniki and Mads
 * @version 1.5
 */
public class GameManager implements TimerCallback{

    private Player player;
    private LinkedList<Level> lvls = new LinkedList<>(); // ska fungera enligt (First In First Out)
    private Level currentLevel = null;
    private Monster currentMonster = null;
    private String currentMathQuestion;
    private boolean gameHasEnded = false;
    int streak = 0;
    private int damage = 0;
    private boolean ifCharacterIsDead = false;
    private final String correctAnswerSound = "files/minecraft_hit.wav";
    private final String wrongAnswerSound = "files/runescape-hit.wav";

    public GameManager(Player player) {
        this.player = player;

        // fill levels-list with new levels
      lvls.addFirst(new Level1("Level 1", this));
      lvls.addFirst(new Level2("Level 2", this));
//      lvls.addFirst(new Level3("Level 3", this));
//      lvls.addFirst(new Level4("Level 4", this));
//      lvls.addFirst(new Level5("Level 5", this));
//      lvls.addFirst(new Level6("Level 6", this));
//      lvls.addFirst(new Level7("Level 7", this));
//      lvls.addFirst(new Level8("Level 8", this));
      lvls.addFirst(new Level9("Level 9", this));
//      lvls.addFirst(new Level10("Level 10", this));
    }


    private boolean checkUserAnswer(double correctAnswer, double userAnswer) {
        if(userAnswer == correctAnswer) {
            return true;
        }
        return false;
    }

    /*
     * This method is called when the user input is correct.
     * It increases the streak count and deals damage to the monster by decreasing its hitpoints.
     * If the monster is dead, the game advances to the next level by calling the method progressGame()
     */
    private void handleCorrectAnswer() {
        damage = 30;
        streak++;
        player.increasePoints();

        System.out.println("points"+player.getPoints());
        playSound(correctAnswerSound);
        streakManager(streak);

        currentMonster.takeDamage(damage);
        System.out.println("You dealt " + damage +" damage to the monster!");
        ifCharacterIsDead = currentMonster.checkIfAlive();
        if (ifCharacterIsDead) {
            progressGame(); // advances to the next level
        }
    }

    /*
     * This method is called when the user input is wrong.
     * By calling this method,the streak counter is set to 0, the players hitpoints decreases
     * and the game will eventually end, when the player hitpoints is 0 or below.
     */
    private void handleWrongAnswer() {
        damage = 10;
        streak=0; //Streak reset
        player.takeDamage(damage);
        playSound(wrongAnswerSound);
        System.out.println("Oh no, the monster dealt " + damage +" damage to you!");
        ifCharacterIsDead = player.checkIfAlive();
        if (ifCharacterIsDead) {
            endGame();
        }
    }

    // Sets the flag that ends the game
    private void endGame() {
        System.out.println("You Died!");
        gameHasEnded = true;
    }

    // For every correct answer by the user, the streak increases
    private void streakManager(int streak) {
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
    }

    // Advances to the next level by calling nextLevel() contributing to the game progress.
    private void progressGame() {
        System.out.println("You defeated: " + currentMonster.getName());

        System.out.println("20 bonus points for completing " + currentLevel.getLvlName()); //20 bonus poäng för att klara en level.
        lvls.removeLast();
        nextLevel();

        // when there are no more levels available in the lvls-list
        if(lvls.isEmpty()) {
            System.out.println("\nYou won!");
            System.out.println("A final reward of 100 bonus points for finishing the game. \nWell played warrior.");
            player.increasePoints(100); //100 bonus poäng för att klara spelet.
            gameHasEnded = true;
        }
    }
    public void startAtFirstLevel() {
        nextLevel();
    }

    // Advances to the next level by fetching it from arraylist which contains the levels.
    public void nextLevel() {
        if(!lvls.isEmpty()) { // hämta nästa nivå så länge listan inte är tom
            currentLevel = lvls.getLast();
            currentMonster = currentLevel.getMonster();
            String lvlName = currentLevel.getLvlName();
            String monsterName = currentMonster.getName();
            currentLevel.newLvlMessage(lvlName, monsterName);
        }
    }

    // Fetches a math question from the current level at hand.
    public double getNewMathQuestion() throws FileNotFoundException {
        return currentLevel.generateMathQuestion();
    }

    // Compares the user input with the generated correct answer to determine if the answer is correct or not.
    public boolean handleUserAnswer(double userAnswer, double currentCorrectAnswer) {
        boolean userAnswerIsCorrect = checkUserAnswer(currentCorrectAnswer, userAnswer);

        if(userAnswerIsCorrect) {
            handleCorrectAnswer();
        } else {
            handleWrongAnswer();
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

    public boolean getGameHasEnded() {
        return gameHasEnded;
    }

    /*
     * This callback function is invoked when the countdown timer is finished,
     * which punishes the player by reducing hitpoints.
     */
    @Override
    public void timesUp() {
        player.takeDamage(10);
        playSound(wrongAnswerSound);
        System.out.println("Times up! You need to be faster than that!");
        ifCharacterIsDead = player.checkIfAlive();
        if (ifCharacterIsDead) {
            endGame();
        }
    }

    public static void playSound(String fileName) {
        try {
            File url = new File(fileName);
            Clip clip = AudioSystem.getClip();

            AudioInputStream ais = AudioSystem.
                    getAudioInputStream( url );
            clip.open(ais);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}