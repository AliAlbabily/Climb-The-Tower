package view;

import control.Controller;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;

/**
 * @author Jagtej Sidhu, Hanis Saley
 * @version 1.1
 */
public class HighscoreGUI extends JFrame
{

    private JList list = new JList<String[]>();
    private JButton closeHS;
    private JFrame frame;

    private Controller controller;

    public HighscoreGUI(Controller controller)
    {
        this.controller = controller;
        initializePanels();
    }

    public void initializePanels()
    {
        createHsFrame();
    }

    public void createHsFrame()
    {
        frame = new JFrame("Highscore");
        frame.setPreferredSize(new Dimension(350, 550));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void closeHsFrame()
    {
        BorderLayout layout = new BorderLayout();
        Border b2 = BorderFactory.createTitledBorder("Highscore");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 550));
        panel.setLayout(layout);
        panel.setBorder(b2);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    closeHsWindow();
                    controller.buttonPressed(ButtonType.Back);
            }
        });

        panel.add(list, BorderLayout.CENTER);
        panel.add(btnBack, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
    }




    public void closeHsWindow()
        setVisible(false);
        dispose();
    }

    public void createHighscore()
    {
        BorderLayout layout = new BorderLayout();
        Border b2 = BorderFactory.createTitledBorder("Highscore");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 550));
        panel.setLayout(layout);
        panel.setBorder(b2);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    closeHsWindow();
                    controller.buttonPressed(ButtonType.Back);
            }
        });

        panel.add(list, BorderLayout.CENTER);
        panel.add(btnBack, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
    }

    }
}
