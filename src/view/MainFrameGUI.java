package view;


import javax.swing.*;
import java.awt.*;

public class MainFrameGUI
{
    private JFrame frame;
    private JPanel pnlNorth;
    private JPanel pnlSouth;



    public MainFrameGUI()
    {
        InitializePanels();
    }

    public void InitializePanels()
    {
        createMainFrame();
        NorthPanel();
        SouthPanel();
    }

    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void NorthPanel()
    {
        pnlNorth = new JPanel();
        pnlNorth.setPreferredSize(new Dimension(1000,400));
        pnlNorth.setBackground(Color.gray);
        pnlNorth.setVisible(true);
        pnlNorth.setLayout(null);

        JLabel lblQuestion = new JLabel("35 + 11 = ?");
        lblQuestion.setForeground(Color.white);
        lblQuestion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,30));
        lblQuestion.setBounds(100, 125, 200, 50);

        JTextField answerTextField = new JTextField();
        answerTextField.setBounds(75,175,200, 25);

        pnlNorth.add(lblQuestion);
        pnlNorth.add(answerTextField);

        frame.add(pnlNorth, BorderLayout.NORTH);
        frame.pack();
    }

    public void SouthPanel()
    {
        pnlNorth = new JPanel();
        pnlNorth.setPreferredSize(new Dimension(1000,200));
        pnlNorth.setBackground(Color.BLACK);
        pnlNorth.setVisible(true);
        pnlNorth.setLayout(null);

        frame.add(pnlSouth, BorderLayout.SOUTH);
        frame.pack();
    }

    public static void main(String[] args)
    {
        MainFrameGUI mainFrameGUI = new MainFrameGUI();
    }
}

