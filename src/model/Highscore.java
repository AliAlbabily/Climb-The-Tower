package model;

import java.io.Serializable;

/**
 * @author Ardian Glamniki
 * @version 1.0
 *
 * This class represents a players highscore.
 * A highscore consists of the players name, and the score belonging to the player.
 */

public class Highscore implements Serializable, Comparable<Highscore> {
    private String name;
    private int points;
    private static final long serialVersionUID = -8277311563837525971L;

    public Highscore(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    @Override
    // Sorts by the players scores, for the purpose of displaying the top ten players
    public int compareTo(Highscore o) {
        return Integer.compare(getPoints(), o.getPoints());
    }

    public String toString() {
        return String.format("Player: %s  Score:  %d", getName(), getPoints());
    }
}
