package view;

import control.Controller;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Jagtej Sidhu
 * @version 1.2
 */
public class EndGameWinGUI extends JPanel
{
    //java swing variables
    private JFrame frame;
    private JPanel mainPnl;
    private Controller controller;

    private JButton btnHighscore;
    private JButton btnPlayAgain;

    private ImageIcon trophyIcon = new ImageIcon("files/newTrophy.png");
    private JLabel myLabel;

    /**
     * Constructor
     * @param controller
     */
    public EndGameWinGUI(Controller controller)
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
        JLabel gameOverlbl = new JLabel("GAME OVER");
        gameOverlbl.setForeground(Color.black);
        gameOverlbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        gameOverlbl.setBounds(90, 30, 200, 100);
        mainPnl.add(gameOverlbl);

        //You won text
        JLabel youWonlbl = new JLabel("You defeated all the monsters");
        youWonlbl.setForeground(Color.black);
        youWonlbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        youWonlbl.setBounds(70, 100, 300, 100);
        mainPnl.add(youWonlbl);

        //Great work text
        JLabel greatJoblbl = new JLabel("Great work!");
        greatJoblbl.setForeground(Color.black);
        greatJoblbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        greatJoblbl.setBounds(140, 140, 200, 100);
        mainPnl.add(greatJoblbl);

        //Trophy picture
        myLabel = new JLabel(trophyIcon);
        myLabel.setBounds(140, 220, 100, 100);
        mainPnl.add(myLabel);

        //play again button on screen
        btnPlayAgain = new JButton("Play again");
        btnPlayAgain.setBounds(12,345,100, 50);
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO koppla knapparna här inne
            }
        });
        mainPnl.add(btnPlayAgain);

        //highscore button on screen
        btnHighscore = new JButton("Highscore");
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
}
