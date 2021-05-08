package model;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author Ardian
 * @Version 1.0
 */
public class HighscoreList {
    private LinkedList<Highscore> highscoreList;
    private final static String highscoreFile = "files/highscores.dat";

    public HighscoreList() {
        highscoreList = new LinkedList<>();
    }

    public void saveNewHighscore(Highscore highscore) {
        LinkedList<Highscore> highscores = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(highscoreFile)))) {
            highscores = (LinkedList<Highscore>) in.readObject();
        }  catch (Exception e) {
            // Ignorera och skapa en ny fil
        }

        if (highscores == null) {
            highscores = new LinkedList<>();
        }

        highscores.add(highscore);
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(highscoreFile)))) {
            out.writeObject(highscores);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Highscore> fetchHighscores() {
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(highscoreFile)))) {
                highscoreList = (LinkedList<Highscore>) in.readObject();
            }  catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return highscoreList;
    }

    public LinkedList<Highscore> topTenHighscores() {
        LinkedList<Highscore> highscoreList = fetchHighscores();
        LinkedList<Highscore> topTenList = new LinkedList<>();
        Collections.sort(highscoreList, Collections.reverseOrder());
        for (int i = 0; i < highscoreList.size(); i++) {
            if (i == 10) {
                break;
            }
            topTenList.add(highscoreList.get(i));
        }
        return topTenList;
    }

    public static void main(String[] args) {
        HighscoreList list = new HighscoreList();
        LinkedList<Highscore> hs = list.topTenHighscores();

        for (Highscore h : hs) {
            System.out.println(h.getName() + " " + h.getPoints());
        }
    }
}
