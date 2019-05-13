package sample;

import java.util.ArrayList;

public class Word {
    //Initial Field
    String w = "";

    //Private ArrayList called wordEntered
    public ArrayList<Tile> wordEntered;

    /**
     * Constructor for word that takes in an arraylist of tile objects
     * Now sets up the word in the constructor
     * @param tileArrayList an arraylist of tile objects
     */
    public Word(ArrayList<Tile> tileArrayList) {
        this.wordEntered = tileArrayList;
        for (Tile obj : wordEntered) {
            if (obj.getTileLetterQu() == "Qu") {
                this.w += obj.getTileLetterQu();
            } else {
                this.w += obj.getTileLetter();
            }
        }
    }

    /**
     * Override for the toString method that shuffles through the wordEntered arraylist and puts together the word
     *
     * @return Returns the word entered by the user.
     */

    //Getter for the word
    public String getWord() {
        return this.w;
    }

    /**
     * getPoints method that returns the value of the w.length with the corresponding point value.
     *
     * @return
     */
    public int getPoints() {
        int currentPoints = 0;
        if(this.w.length() < 3)
            currentPoints += 0;
        else if (this.w.length() == 3)
            currentPoints += 1;
        else if (this.w.length() == 4)
            currentPoints += 1;
        else if (this.w.length() == 5)
            currentPoints += 2;
        else if (this.w.length() == 6)
            currentPoints += 3;
        else if (this.w.length() == 7)
            currentPoints += 5;
        else
            currentPoints += 11;
        return currentPoints;
    }


}
