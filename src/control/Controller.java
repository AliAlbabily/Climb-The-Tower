package control;

import model.*;
import view.ButtonType;
import view.EndGameWinGUI;
import view.GameGUI;
import view.HighscoreGUI;
import view.StartMenuGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * @author Ali, Hanis and Ardian
 * @version 1.3
 */
public class Controller implements TimerCallback {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;
    private GameTimer timer;
    private HighscoreGUI highscoreGUI;
    private EndGameWinGUI endGameWinGui;

    private final PlayersList playersList = new PlayersList();

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
        String currentLevelName;
        String currentMonsterName;
        String currentPlayerName;

        int playerHP = model.getPlayer().getHitPoints();
        int monsterHP = model.getCurrentMonster().getHitPoints();
        String mathQuestionStr;

        // update level name on GUI
        currentLevelName = model.getCurrentLevel().getLvlName();
        gameGUI.updateLevelNameGUI(currentLevelName);

        // update monster name on GUI
        currentMonsterName = model.getCurrentMonster().getName();
        gameGUI.updateMonsterNameGUI(currentMonsterName);

        //update player name on GUI
        currentPlayerName = model.getPlayer().getName();
//        gameGUI.setName(currentPlayerName);

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
                   // System.exit(0); // terminate program  TODO kommenterade bort detta tillfälligt

                 //   endGame();
                    timer.stopTimer();
                    gameGUI.closeGameGUI();
                    setupEndGameWindow();

                }

                updateGamePlayInformation();
                break;

            case Highscore:

                Player[] tempList = playersList.getHighScoreList();
                int points = model.getPoints();
                int worstResult = tempList[9].getPoints();
                tempList = checkIfPointsQualified(tempList, points, worstResult);
                playersList.setHighScoreList(tempList);
                updateHighscoreListGUI(tempList);
                endGameWinGui.getBtnHighscore().setEnabled(false);
                break;

            case Back:
                endGameWinGui.getBtnHighscore().setEnabled(true);
                break;

            case Quit:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }

    private void endGame() {
        System.out.println("\nGame Over!");

//        Player[] tempList = playersList.getHighScoreList();
//        int points = model.getPoints();
//        int worstResult = tempList[9].getPoints();
//
//        tempList = checkIfPointsQualified(tempList, points, worstResult);
//        playersList.setHighScoreList(tempList);
//        updateHighscoreListGUI(tempList);

        // FIXME : ska ändras mot "öppna highscore-view"
       // System.exit(0); // terminate program
    }
    // Initializes the timer by setting the contents and starting the countdown thread.
    private void setTimer(String lvl) {
        timer.setTimeLeftLbl(gameGUI.getTimer());
        switch (lvl) {
            case "Level 1":
            case "Level 2":
            case "Level 3":
            case "Level 4":
                timer.setSeconds(11);
                break;
            case "Level 5":
            case "Level 6":
            case "Level 7":
                timer.setSeconds(21);
                break;
            default:
                timer.setSeconds(61);

        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        });
    }

    //Updates highscore taking the values from PlayersList class in model package and
    //transfering it to HighscoreGUi in view
    public void updateHighscoreListGUI(Player [] highscoreList){
            HighscoreGUI highscoreGUI = new HighscoreGUI(this);
            String[] list = playersList.convertObjListToStringList(highscoreList);
            playersList.printStringList(list);
            highscoreGUI.updateHighscoreGUI(list);

    }


    public Player[] checkIfPointsQualified(Player[] listOfPlayers, int points, int worstResult){

        if(points > worstResult){
            for(int i = 0; i < listOfPlayers.length; i++){
                int playerPoints = listOfPlayers[i].getPoints();

                if(points > playerPoints){
                    moveElementsToRight(i,listOfPlayers);

                    String playerName = startMenuGUI.getPlayerName();
                    listOfPlayers[i] = new Player(playerName, 100, points);
                    System.out.println();
                    break;
                }
            }
        }
        return listOfPlayers;
    }

    private void moveElementsToRight(int index, Player[] listOfObjects){
        for(int i = listOfObjects.length-2; i >=index; i--){
            listOfObjects[i+1] = listOfObjects[i];
        }
    }

    public void setupEndGameWindow(){
        endGameWinGui = new EndGameWinGUI(this);
    }

    @Override
    // Callback function that is invoked when the countdown timer is finished.
    public void timesUp() {
        updateGamePlayInformation();
        if(model.getGameHasEnded()) {
            endGame();
        }
    }


}