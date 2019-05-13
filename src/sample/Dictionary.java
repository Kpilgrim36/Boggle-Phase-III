package sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {

    //Initial Fields

    private String w;
    //Private Arraylist Dictionary
    private ArrayList<String> dictionary = new ArrayList<>();

    /**
     * This is the constructor to get the words from the dictionary.
     *
     * @param dict is the string that holds the data from the infile
     * @throws IOException
     */
    public Dictionary(String dict) throws IOException {

        File inFile = new File(dict);
        Scanner wordsFile = new Scanner(inFile);

        while (wordsFile.hasNext()) {
            String word = wordsFile.next();
            dictionary.add(word);
        }
        wordsFile.close();
    }

    /**
     * This method checks if the word given is valid against the dictionary
     *
     * @param tileObjectArray is an array of tile objects
     * @return returns a boolean value
     */
    public boolean isValidWord(ArrayList<Tile> tileObjectArray) {
        String word = "";
        for (Tile obj : tileObjectArray) {
            if (obj.getTileLetterQu() == "Qu") {
                word += obj.getTileLetterQu();
            } else {
                word += obj.getTileLetter();
            }
            this.w = word.toLowerCase();
        }
        for (String obj2 : dictionary) {
            if (w.equals(obj2)) {
                return true;
            }
        }
        return false;
    }
}
