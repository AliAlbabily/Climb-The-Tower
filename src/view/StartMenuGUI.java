package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu
 * @version 1.1
 */
public class StartMenuGUI extends JPanel
{
    //object of controller class
    private Controller controller;

    //Java swing variables
    private JTextField userTextField = new JTextField();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton playBtn = new JButton();
    private JButton infoBtn = new JButton();
    private String  playerName;

    //Image variables
    private ImageIcon towerPic = new ImageIcon("files/towers.png");
    private JLabel myLabel;

    /**
     * Constructor
     * @param controller Controller class
     */
    public StartMenuGUI(Controller controller)
    {
        this.controller = controller;
        createMenuBox();

    }

    public void createMenuBox()
    {
        /**
         * Inizialize of frame
         */
        frame = new JFrame("Climb The Tower");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        /**
         * Initializing of jpanel being used
         */
        JPanel mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(300, 400));
        mainPnl.setLayout(null);
        panel.add(mainPnl);

        /**
         * Text field where you type in your name
         */
        userTextField = new JTextField("");
        userTextField.setPreferredSize(new Dimension(200,30));
        userTextField.setBounds(50,175, 200, 30);
        userTextField.setBorder(blackline);
        mainPnl.add(userTextField);

        /**
         * Background picture for the frame
         */
        myLabel = new JLabel(towerPic);
        myLabel.setSize(300,400);
        mainPnl.add(myLabel);

        /**
         * Play button on screen
         */
        playBtn = new JButton("PLAY");
        playBtn.setBounds(75, 225, 150, 50);
        mainPnl.add(playBtn);

        /**
         * Info button on screen
         */
        infoBtn = new JButton("?");
        infoBtn.setBounds(15, 8, 40,40);
        infoBtn.setFont(new Font("", Font.BOLD, 10));
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"You versus the monsters!\n You play the game by typing the the right mathematical answers for each question. \n"+
                                "An amount of correct answers are required to defeat the monsters. \n"+
                                "The further you progress the harder the questions become. \n" +
                                "You can also get bonus hits and points if you answer quickly before the timer" +
                                " runs out.\n" + "Good luck warrior!");
            }
        });
        mainPnl.add(infoBtn);

        frame.setVisible(true);
        addListeners();
    }

    private void addListeners()
    {
        ActionListener listener = new ButtonActionListeners();

        playBtn.addActionListener(listener);
    }

    class ButtonActionListeners implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playBtn)
            {
                controller.buttonPressed(ButtonType.Play);
            }
        }
    }

    public void closeStartMenuGUIWindow() {
        frame.setVisible(false); // hide window
        frame.dispose(); // Destroy the JFrame object, (close window)
    }

    // get player name from user input (userTextField)
    public String getPlayerName() {
        playerName = userTextField.getText();
        return playerName;
    }
}

