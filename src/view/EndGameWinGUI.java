package view;

import control.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * @author Jagtej Sidhu, Hanis Saley
 * @version 1.1
 */
public class EndGameWinGUI extends JPanel
{
    private JFrame frame;
    private JPanel mainPnl;
    private BufferedImage image;
    private EndGameWinGUI endWinGUi;
    private Controller controller;
    private HighscoreGUI highscoreGUI;

    private JButton btnPlayAgain;
    private JButton btnHighscore;
    private JButton btnQuit;




    public EndGameWinGUI(Controller controller)
    {
        this.controller = controller;
        initializePanels();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void initializePanels()
    {
        createMainFrame();
        createEndPanel();


    }


    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(400, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void createEndPanel() {
        Border blackline = BorderFactory.createLineBorder(Color.black);

        mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(400, 450));
        mainPnl.setLayout(null);
        mainPnl.setBackground(Color.lightGray);

        JLabel gameOverlbl = new JLabel("GAME OVER");
        gameOverlbl.setForeground(Color.black);
        gameOverlbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        gameOverlbl.setBounds(90, 30, 200, 100);

        JLabel youWonlbl = new JLabel("You defeated all the monsters");
        youWonlbl.setForeground(Color.black);
        youWonlbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        youWonlbl.setBounds(70, 100, 300, 100);

        JLabel greatJoblbl = new JLabel("Great work!");
        greatJoblbl.setForeground(Color.black);
        greatJoblbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        greatJoblbl.setBounds(140, 140, 200, 100);

//        TODO måste fixa så att bilden visas
//        try{
//            image = ImageIO.read(new File("files/trophy.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JLabel piclbl = new JLabel(new ImageIcon(image));

        JButton btnPlayAgain = new JButton("Play again");
        btnPlayAgain.setBounds(12,345,100, 50);
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO koppla knapparna här inne
            }
        });

        JButton btnHighscore = new JButton("Highscore");
        btnHighscore.setBounds(140,345,100, 50);
        btnHighscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.buttonPressed(ButtonType.Highscore);

                //TODO koppla knapparna här inne
            }
        });

        JButton btnQuit = new JButton("Quit");
        btnQuit.setBounds(272,345,100, 50);
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonPressed(ButtonType.Quit);
            }
        });

        mainPnl.add(btnQuit);
        mainPnl.add(btnHighscore);
        mainPnl.add(btnPlayAgain);
//        mainPnl.add(piclbl);
        mainPnl.add(gameOverlbl);
        mainPnl.add(youWonlbl);
        mainPnl.add(greatJoblbl);

        frame.add(mainPnl);
        frame.pack();
    }

    public JButton getBtnHighscore(){
        return btnHighscore;
    }


}
