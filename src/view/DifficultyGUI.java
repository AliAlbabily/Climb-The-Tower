package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyGUI extends JFrame {
    private Controller controller;
    private final int width = 300;
    private final int height = 300;
    private JButton btnEasy;
    private JButton btnMedium;
    private JButton btnHard;

    public DifficultyGUI(Controller controller) {
        this.controller = controller;
        setTitle("Choose difficulty");
        setLayout(new GridLayout(3,1,1,1));
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        initializeComponents();
        addBtnListeners();
    }

    private void initializeComponents() {
        Font font = new Font("Rockwell Extra Bold", 0, 14);
        btnEasy = new JButton("Easy");
        btnEasy.setFont(font);
        btnEasy.setForeground(Color.WHITE);
 //       btnEasy.setBackground(Color.GREEN);
        btnEasy.setBackground(new Color(153, 255, 51));
        btnMedium = new JButton("Medium");
        btnMedium.setFont(font);
        btnMedium.setForeground(Color.WHITE);
//        btnMedium.setBackground(Color.ORANGE);
        btnMedium.setBackground(new Color(255, 204, 51));
        btnHard = new JButton("Hard");
        btnHard.setFont(font);
        btnHard.setForeground(Color.WHITE);
 //       btnHard.setBackground(Color.RED);
        btnHard.setBackground(new Color(255,51,51));
        add(btnEasy);
        add(btnMedium);
        add(btnHard);
    }

    private void addBtnListeners() {
        ActionListener l = new ButtonListeners();
        btnEasy.addActionListener(l);
        btnMedium.addActionListener(l);
        btnHard.addActionListener(l);
    }

    public void closeFrame() {
        setVisible(false);
        dispose();
    }

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnEasy) {
                controller.startGamePlay(DifficultyLevel.Easy);
            } else if (e.getSource() == btnMedium) {
                controller.startGamePlay(DifficultyLevel.Medium);
            } else if (e.getSource() == btnHard) {
                controller.startGamePlay(DifficultyLevel.Hard);
            }
        }
    }
}
