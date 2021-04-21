package model;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameTimer extends Thread {
    private LinkedList<TimerCallback> callbackList = new LinkedList<>();
    private JLabel timeLeftLbl;
    private int seconds;

    public GameTimer() {
    }

    public void addListener(TimerCallback callback) {
        if(callback != null) {
            callbackList.add(callback);
        }
    }
    @Override
    public void run() {

        while (seconds >= 0) {
            seconds--;
            SwingUtilities.invokeLater(new Write(seconds));
            if (seconds == 0) {
                for (TimerCallback cb: callbackList) {
                    cb.timesUp();
                }
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    public void stopTimer() {
        this.interrupt();
    }

    public void setTimeLeftLbl(JLabel timeLeftLbl)
    {
        this.timeLeftLbl = timeLeftLbl;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private class Write implements Runnable {
        private int timeLeft;

        public Write(int timeLeft) {
            this.timeLeft = timeLeft;
        }

        @Override
        public void run() {
            timeLeftLbl.setText(String.valueOf(timeLeft));
            if (timeLeft <= 7)
            {
                timeLeftLbl.setForeground(Color.orange);
            }

            if (timeLeft <= 4)
            {
                timeLeftLbl.setForeground(Color.red);
            }

            if (timeLeft >= 8)
            {
                timeLeftLbl.setForeground(Color.green);
            }
        }
    }
}
