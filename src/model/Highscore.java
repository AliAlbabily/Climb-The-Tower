package model;

import java.io.Serializable;

/**
 * @author Ardian
 * @version 1.0
 *
 * This class represents a players highscore.
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
    public int compareTo(Highscore o) {
        return Integer.compare(getPoints(), o.getPoints());
    }
    public String toString() {
        return String.format("Player's name: %s  Score:  %d", getName(), getPoints());
    }
}
