package model;

/**
 * @author Hanis Saley
 * @version 1.0
 */
public class PlayersList {

    /**
     * Player with points to fill the highscore.
     */
    private Player[] highScoreList = {
            new Player ("Lasse", 100, 100),
            new Player ("Kalle", 100, 80),
            new Player ("Thomas", 100, 70),
            new Player ("Felicia", 100, 60),
            new Player ("Ahmed", 100, 50),
            new Player ("Johan", 100, 40),
            new Player ("Younes", 100, 35),
            new Player ("Johanna", 100, 30),
            new Player ("Melisa", 100, 25),
            new Player ("Ivan", 100, 21)

    };

    /**
     * @return Returns highscoreList
     */
    public Player[] getHighScoreList(){
        return highScoreList;
    }

    /**
     * @param highScoreList Setter for highscoreList.
     */
    public void setHighScoreList(Player[] highScoreList){
        this.highScoreList = highScoreList;
    }

    /**
     * @param listOfObjects Converts object to string.
     * @return Returns string version of the object.
     */
    public String[] convertObjListToStringList(Player[] listOfObjects) {
        String[] ItemToString = new String[10];

        for (int i = 0; i < listOfObjects.length; i++) {
            if (listOfObjects[i] != null) {
                ItemToString[i] = listOfObjects[i].toString();
            } else {
                break;
            }
        }

        return ItemToString;
    }

    /**
     * @param stringList Prints out the strings in the String[] stringList
     */
    public void printStringList(String[] stringList){
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println();
    }

 }