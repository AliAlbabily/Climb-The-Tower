package control;

import model.GameManager;
import model.GameTimer;
import model.Player;
import model.TimerCallback;
import view.ButtonType;
import view.GameGUI;
import view.StartMenuGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * @author
 * @version 1.2
 */
public class Controller implements TimerCallback {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;
    private GameTimer timer;

    private double currentCorrectAnswer = 0;
    private boolean gameHasEnded = false;

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        startMenuGUI = new StartMenuGUI(this);
    }

    // update gameplay info on both GUI & console
    public void updateGamePlayInformation() {
        String  currentLevelName;
        String currentMonsterName;
        int playerHP = model.getPlayer().getHitPoints();
        int monsterHP = model.getCurrentMonster().getHitPoints();
        String mathQuestionStr;

        // update level name on GUI
        currentLevelName = model.getCurrentLevel().getLvlName();
        gameGUI.updateLevelNameGUI(currentLevelName);

        // update monster name on GUI
        currentMonsterName = model.getCurrentMonster().getName();
        gameGUI.updateMonsterNameGUI(currentMonsterName);

        // update characters HP on console
        System.out.println("\n" + model.getPlayer().getName() + "'s hp: " + playerHP);
        System.out.println(model.getCurrentMonster().getName() + "'s hp: " + monsterHP +"\n");
        // update characters HP on GUI
        gameGUI.updateCharactersHPGUI(playerHP, monsterHP);

        // generate a math question
        try {
            currentCorrectAnswer = model.getNewMathQuestion();
            timer = new GameTimer();
            timer.addListener(model);
            timer.addListener(this);
            setTimer(model.getCurrentLevel().getLvlName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // update math question on console
        mathQuestionStr = model.getCurrentMathQuestion();
        System.out.println(mathQuestionStr);
        // update math question on GUI
        gameGUI.updateMathQuestionGUI(mathQuestionStr);

        System.out.println("\nAttack the enemy by entering the right number: ");
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Play:
                String playerName = startMenuGUI.getPlayerName();
                Player player = new Player(playerName, 100,0);
                model = new GameManager(player);
                timer = new GameTimer();
                timer.addListener(model);
                timer.addListener(this);

                model.startAtFirstLevel();
                gameGUI = new GameGUI(this); // start the gameplay with a GUI
                updateGamePlayInformation();

                startMenuGUI.closeStartMenuGUIWindow();
                break;
            case SubmitAnswer:
                double userAnswer = gameGUI.getUserAnswer();
                gameHasEnded = model.handleUserAnswer(userAnswer, currentCorrectAnswer);
                timer.stopTimer();

                if(gameHasEnded) {
                    System.out.println("\nGame Over!");
                    // FIXME : ska ändras mot "öppna highscore-view"
                    System.exit(0); // terminate program
                }

                updateGamePlayInformation();
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }

    // Initializes the timer by setting the contents and starting the countdown thread.
    private void setTimer(String lvl) {
        timer.setTimeLeftLbl(gameGUI.getTimer());
        switch (lvl) {
            case "Level 1":
            case "Level 2":
            case "Level 3":
            case "Level 4":
                timer.setSeconds(10);
                break;
            case "Level 5":
            case "Level 6":
            case "Level 7":
                timer.setSeconds(20);
                break;
            default:
                timer.setSeconds(60);

        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        });
    }

    @Override
    // Callback function that is invoked when the countdown timer is finished.
    public void timesUp() {
        updateGamePlayInformation();
    }

//    private void checkIfGameHasEnded(){
//        boolean gameHasEnded =
//    }

}