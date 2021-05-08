package model;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Ardian
 * @Version 1.1
 *
 * This class handles the highscores in a file.
 */
public class HighscoreList {
    private LinkedList<Highscore> highscoreList;
    private final static String highscoreFile = "files/highscores.dat";

    public HighscoreList() {
        highscoreList = new LinkedList<>();
    }

    // Adds a highscore to the file
    public void saveNewHighscore(Highscore highscore) {
        LinkedList<Highscore> highscores = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(highscoreFile)))) {
            highscores = (LinkedList<Highscore>) in.readObject();
        }  catch (Exception e) {
            // Ignore and create a list
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

    // Retrieves the highscores from the highscore file and returns a linked-list.
    private LinkedList<Highscore> fetchHighscores() {
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

    // Returns the top ten highscores from the list of highscores.
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
}
