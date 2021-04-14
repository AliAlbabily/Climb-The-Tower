package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EndGameGUI extends JFrame
{
    private JFrame frame;
    private JPanel mainPnl;

    public EndGameGUI()
    {
        initializePanels();
    }

    public void initializePanels()
    {
        createMainFrame();
        createEndPanel();
    }


    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void createEndPanel()
    {
        Border blackline = BorderFactory.createLineBorder(Color.black);

        mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(400, 500));
        mainPnl.setLayout(null);
        mainPnl.setBackground(Color.lightGray);

        JLabel gameOverlbl = new JLabel("GAME OVER");
        gameOverlbl.setForeground(Color.black);


        frame.add(mainPnl);
        frame.pack();


    }

    public static void main(String[] args)
    {
        new EndGameGUI();
    }
}
