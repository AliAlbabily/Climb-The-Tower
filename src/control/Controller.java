package control;

import model.GameManager;
import model.Player;
import view.ButtonType;
import view.GameGUI;
import view.StartMenuGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * @author
 * @version 1.2
 */
public class Controller {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;

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

                model.startAtFirstLevel();
                gameGUI = new GameGUI(this); // start the gameplay with a GUI
                updateGamePlayInformation();

                startMenuGUI.closeStartMenuGUIWindow();
                break;
            case SubmitAnswer:
                double userAnswer = gameGUI.getUserAnswer();
                gameHasEnded = model.handleUserAnswer(userAnswer, currentCorrectAnswer);

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

//    private void checkIfGameHasEnded(){
//        boolean gameHasEnded =
//    }

}