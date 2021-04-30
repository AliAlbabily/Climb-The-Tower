package model;

/**
 * @author Hanis, Mads and Ali
 * @version 1.1
 */
public class Player extends Character {

    private int points;

    public Player(String name, int hitPoints, int points) {
        super(name, hitPoints);
        this.points = points;
    }

    public void increasePoints(){
        points +=10;
    }

    public void increasePoints(int bonuspoints){
        points += bonuspoints; //Bonuspoäng som läggs till för x antal korrekta svar i rad.
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("Player's name: %s  Score:  %d",getName(),points);
    }
}