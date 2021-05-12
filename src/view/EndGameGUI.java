package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu, Hanis Saley
 * @version 1.4
 */
public class EndGameGUI extends JPanel
{
    //java swing variables
    private JFrame frame;
    private JPanel mainPnl;
    private Controller controller;

    private JButton btnHighscore;
    private JButton btnPlayAgain;

    private ImageIcon trophyIcon = new ImageIcon("files/newTrophy.png");
    private ImageIcon graveIcon = new ImageIcon("files/rip.png");
    private JLabel trophyLabel;
    private JLabel graveLabel;
    private JLabel gameOverlbl;
    private JLabel monstersDefeated;
    private JLabel performenceLbl;
    /**
     * Constructor
     * @param controller
     */
    public EndGameGUI(Controller controller)
    {
        this.controller = controller;
        initializePanels();
    }

    /**
     * Method that initializes the frame and jpanel windows
     */
    public void initializePanels()
    {
        createMainFrame();
        createEndPanel();
    }

    /**
     * Creates the frame window
     */
    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(400, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        centreWindow(frame);
    }

    /**
     * Created the jpanel for the frame window
     */
    public void createEndPanel() {

        //initializing of jpanel
        mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(400, 450));
        mainPnl.setLayout(null);
        mainPnl.setBackground(Color.lightGray);

        //game over text
        gameOverlbl = new JLabel("GAME OVER");
        gameOverlbl.setForeground(Color.black);
        gameOverlbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        gameOverlbl.setBounds(95, 30, 200, 100);
        mainPnl.add(gameOverlbl);

        //You won text
        monstersDefeated = new JLabel();
        monstersDefeated.setForeground(Color.black);
        monstersDefeated.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        mainPnl.add(monstersDefeated);

        //Great work text
        performenceLbl = new JLabel();
        performenceLbl.setForeground(Color.black);
        performenceLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        mainPnl.add(performenceLbl);

        //Trophy picture
        trophyLabel = new JLabel(trophyIcon);
        trophyLabel.setBounds(140, 220, 100, 100);

        //Grave picture
        graveLabel = new JLabel(graveIcon);
        graveLabel.setBounds(140, 220, 100, 100);



        //play again button on screen
        btnPlayAgain = new JButton("Play again");
        btnPlayAgain.setBounds(12,345,100, 50);
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO koppla knapparna här inne
                controller.buttonPressed(ButtonType.PlayAgain);
            }
        });
        mainPnl.add(btnPlayAgain);

        //highscore button on screen
        btnHighscore = new JButton("Highscore");
        btnHighscore.setEnabled(true);
        btnHighscore.setBounds(140,345,100, 50);
        btnHighscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonPressed(ButtonType.Highscore);
            }
        });
        mainPnl.add(btnHighscore);

        //Quit button on screen
        JButton btnQuit = new JButton("Quit");
        btnQuit.setBounds(272,345,100, 50);
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonPressed(ButtonType.Quit);
            }
        });
        mainPnl.add(btnQuit);

        frame.add(mainPnl);
        frame.pack();
    }

    /**
     *
     * @return highscore button 
     */
    public JButton getBtnHighscore(){
        return btnHighscore;
    }

    /**
     * Centers the frame according to the user's window size
     * @param frame the JFrame that needs to be centered
     */
    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Closes the frame on command
     */
    public void closeEndGameGUI(){
        frame.setVisible(false);
        frame.dispose();
    }

    public void displayYouWinMessage() {
        monstersDefeated.setText("You defeated all the monsters!");
        performenceLbl.setText("Great work!");
        performenceLbl.setBounds(135, 140, 200, 100);
        monstersDefeated.setBounds(65, 100, 300, 100);
        mainPnl.add(trophyLabel);
    }

    public void displayYouLostMessage() {
        monstersDefeated.setText("You lost against the monsters!");
        performenceLbl.setText("Better luck next time!");
        performenceLbl.setBounds(110, 140, 200, 100);
        monstersDefeated.setBounds(70, 100, 300, 100);
        mainPnl.add(graveLabel);
    }
}
