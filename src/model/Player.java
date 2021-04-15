package model;

public class Player extends Character {

    private int points;

    public Player(String name, int hitPoints, int points) {
        super(name, hitPoints);
        this.points = points;
    }

    public void increasePoints(){
        points +=10;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("Player's name: %s  Score:  %d",getName(),points);
    }
}