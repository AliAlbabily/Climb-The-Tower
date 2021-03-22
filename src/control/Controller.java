package control;

import model.GameManager;
import view.MainFrame;
//Jagtej test comment.
    public class Controller {

    private MainFrame view;
    private GameManager model;

    public Controller() {

        init(); // kan bytas mot startNewGame()
    }

    private void init() {
        view = new MainFrame();
        model = new GameManager();

        startGame();
    }
//testKommentar från hanis
    private void startGame() {
        int choice = view.showMenu();

        switch(choice) {
            case 1:
                model.startLevels();
                break;
            case 2:
                System.out.println("Don't be a noob!");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
