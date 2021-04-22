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
    //en instans av controller
    private Controller controller;

    //instansiering av java swing variabler
    private JTextField userTextField = new JTextField();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton playBtn = new JButton();
    private JButton infoBtn = new JButton();
    private String  playerName;


   // private Image pic;
//    private Image img = Toolkit.getDefaultToolkit().getImage("files/towers.png");

    public StartMenuGUI(Controller controller)
    {
        this.controller = controller;

        createMenuBox();
        //ImageIcon obj = new ImageIcon("files/towers.png");
        //pic = obj.getImage();

    }
//    //TODO fixa backgrund bild
//    @Override
//    protected void paintComponent(Graphics g)
//    {
//
//        super.paintComponent(g);
//        g.drawImage(pic, 0, 0, null);
//    }


    public void createMenuBox()
    {
        frame = new JFrame("Climb The Tower");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 350));
        mainPanel.setLayout(null);


        panel.add(mainPanel);

        userTextField = new JTextField("");
        userTextField.setPreferredSize(new Dimension(200,30));
        userTextField.setBounds(50,175, 200, 30);
        userTextField.setBorder(blackline);

        playBtn = new JButton("PLAY");
        playBtn.setBounds(75, 225, 150, 50);

        infoBtn = new JButton("?");
        infoBtn.setBounds(15, 2, 40,40);
        infoBtn.setFont(new Font("", Font.BOLD, 10));
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane
                        ("You versus the monsters! You play the game by typing the the right mathematical answers for each question. \n"+
                                "An amount of correct answers are required to defeat the monsters. \n"+
                                "The further you progress the harder the questions become. \n" +
                                "You can also get bonus hits and points if you answer quickly before the timer" +
                                " runs out.\n" + "Good luck warrior!",JOptionPane.OK_OPTION);
                JDialog dialog = optionPane.createDialog("Dialog");
                dialog.setVisible(true);
            }
        });

        mainPanel.add(infoBtn);
        mainPanel.add(userTextField);
        mainPanel.add(playBtn);

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

